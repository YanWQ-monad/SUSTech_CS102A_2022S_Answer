import java.util.Arrays;

public class BigBinary2 {
    int[] bits;
    boolean positive;

    public BigBinary2(int[] bits, boolean positive) {
        this.bits = new int[bits.length];
        for (int i = 0; i < bits.length; i++)
            this.bits[i] = bits[bits.length - i - 1];
        this.bits = shrink(this.bits);
        this.positive = positive;
    }

    private BigBinary2(int[] bits, boolean positive, int _internal) {
        this.bits = bits;
        this.positive = positive;
    }

    private BigBinary2 copyFrom(BigBinary2 other) {
        this.bits = other.bits;
        this.positive = other.positive;
        return this;
    }

    public BigBinary2 add(BigBinary2 other) {
        return copyFrom(BigBinary2.add(this, other));
    }

    public BigBinary2 minus(BigBinary2 other) {
        return copyFrom(BigBinary2.minus(this, other));
    }

    public BigBinary2 multiply(BigBinary2 other) {
        return copyFrom(BigBinary2.multiply(this, other));
    }

    public static BigBinary2 add(BigBinary2 b1, BigBinary2 b2) {
        int length = Math.max(b1.bits.length, b2.bits.length) + 2;
        int[] lhs = b1.toTwosComplement(length);
        int[] rhs = b2.toTwosComplement(length);
        return complementAdd(lhs, rhs);
    }

    public static BigBinary2 minus(BigBinary2 b1, BigBinary2 b2) {
        int length = Math.max(b1.bits.length, b2.bits.length) + 2;
        int[] lhs = b1.toTwosComplement(length);

//        int[] rhs = toTwosComplement(b2.bits, !b2.positive, length);
        int[] rhs = toTwosComplement(b2.toTwosComplement(length), false, length);

        return complementAdd(lhs, rhs);
    }

    private static BigBinary2 complementAdd(int[] lhs, int[] rhs) {
//        assert lhs.length == rhs.length;
        int[] res = new int[lhs.length];
        for (int i = 0, j = 0; i < lhs.length; i++) {
            j = lhs[i] + rhs[i] + j;
            res[i] = j % 2;
            j /= 2;
        }
        return fromTwosComplement(res);
    }

    // convert a BigBinary to its complement
    private int[] toTwosComplement(int length) {
        return toTwosComplement(this.bits, this.positive, length);
    }

    // convert a bits array to its complement
    private static int[] toTwosComplement(int[] bits, boolean positive, int length) {
        int[] res = Arrays.copyOf(bits, length);
        if (!positive) {
            int i = 0;
            while (i < res.length && res[i] == 0)
                i++;
            for (i++; i < res.length; i++)
                res[i] = 1 - res[i];
        }
        return res;
    }

    // create BigBinary from complement
    private static BigBinary2 fromTwosComplement(int[] bits) {
        if (bits[bits.length - 1] != 1)
            return new BigBinary2(shrink(bits), true, 0);
        else {
            int[] tmp = toTwosComplement(bits, false, bits.length);
            return new BigBinary2(shrink(tmp), false, 0);
        }
    }

    public static BigBinary2 multiply(BigBinary2 b1, BigBinary2 b2) {
        int k = 0;
        while ((1 << k) < b1.bits.length + b2.bits.length)
            k++;
        int n = 1 << k;

        Complex[] omega = new Complex[n];
        Complex[] omegaInv = new Complex[n];
        for (int i = 0; i < n; i++) {
            omega[i] = Complex.omega(n, i);
            omegaInv[i] = omega[i].conj();
        }

        Complex[] c1 = new Complex[n];
        Complex[] c2 = new Complex[n];
        for (int i = 0; i < n; i++)
            c1[i] = (i < b1.bits.length && b1.bits[i] == 1) ? Complex.REAL_ONE : Complex.ZERO;
        for (int i = 0; i < n; i++)
            c2[i] = (i < b2.bits.length && b2.bits[i] == 1) ? Complex.REAL_ONE : Complex.ZERO;

        fftTransform(c1, n, k, omega);
        fftTransform(c2, n, k, omega);

        for (int i = 0; i < n; i++)
            c1[i] = c1[i].multiply(c2[i]);

        fftTransform(c1, n, k, omegaInv);

        int[] res = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            res[i] = (int)Math.floor(c1[i].real / n + 0.5) + j;
            j = res[i] / 2;
            res[i] %= 2;
        }

        return new BigBinary2(shrink(res), b1.positive == b2.positive, 0);
    }

    public static void fftTransform(Complex[] a, int n, int k, Complex[] omega) {
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = 0; j < k; j++)
                if ((i & (1 << j)) > 0)
                    t |= (1 << (k - j - 1));
            if (i < t) {
                Complex tmp = a[i];
                a[i] = a[t];
                a[t] = tmp;
            }
        }

        for (int j = 2; j <= n; j *= 2) {
            int m = j / 2;
            for (int p = 0; p < n; p += j) {
                for (int i = 0; i < m; i++) {
                    Complex t = omega[n / j * i].multiply(a[p + m + i]);
                    a[p + m + i] = a[p + i].minus(t);
                    a[p + i] = a[p + i].add(t);
                }
            }
        }
    }

    private static int[] shrink(int[] arr) {
        int j = arr.length;
        while (j > 0 && arr[j - 1] == 0)
            j--;
        return j == arr.length ? arr : Arrays.copyOf(arr, j);
    }

    @Override
    public String toString() {
        if (this.bits.length == 0)
            return "0";

        StringBuilder builder = new StringBuilder();
        for (int bit : this.bits)
            builder.append((char)(bit + '0'));
        if (!this.positive)
            builder.append('-');
        return builder.reverse().toString();
    }
}

class Complex {
    public static final Complex REAL_ONE = new Complex(1, 0);
    public static final Complex ZERO = new Complex(0, 0);

    double real, imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public static Complex omega(int n, int i) {
        return new Complex(Math.cos(2 * Math.PI / n * i), Math.sin(2 * Math.PI / n * i));
    }

    public Complex conj() {
        return new Complex(this.real, -this.imag);
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imag + other.imag);
    }

    public Complex minus(Complex other) {
        return new Complex(this.real - other.real, this.imag - other.imag);
    }

    public Complex multiply(Complex other) {
        return new Complex(
                this.real * other.real - this.imag * other.imag,
                this.real * other.imag + this.imag * other.real
        );
    }
}
