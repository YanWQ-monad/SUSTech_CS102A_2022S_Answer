import java.util.Arrays;

public class BigBinary {
    private static final int MAG_LENGTH = 31;
    private static final int MAG_MASK = (int) ((1L << MAG_LENGTH) - 1);
    private static final long MAG_RANGE = 1L << MAG_LENGTH;

    int[] mag;
    boolean positive;

    public BigBinary(int[] bits, boolean positive) {
        int length = (bits.length - 1) / MAG_LENGTH + 1;
        int[] mag = new int[length];

        for (int i = 0; i < bits.length; i++) {
            int j = i / MAG_LENGTH;
            int k = i - j * MAG_LENGTH;
            mag[j] |= bits[bits.length - i - 1] << k;
        }

        this.mag = shrink(mag);
        this.positive = positive;
    }

    // internal constructor
    private BigBinary(int[] mag, boolean positive, int _internal) {
        this.mag = mag;
        this.positive = positive;
    }

    // copy the data from another BigBinary
    private BigBinary copyFrom(BigBinary other) {
        this.mag = other.mag;
        this.positive = other.positive;
        return this;
    }

    public BigBinary add(BigBinary other) {
        // use `copyFrom` to update the data of "this" BigBinary
        return copyFrom(BigBinary.add(this, other));
    }

    public BigBinary minus(BigBinary other) {
        return copyFrom(BigBinary.minus(this, other));
    }

    public BigBinary multiply(BigBinary other) {
        return copyFrom(BigBinary.multiply(this, other));
    }

    public static BigBinary add(BigBinary b1, BigBinary b2) {
        if (b1.positive == b2.positive)
            return new BigBinary(magnitudeAdd(b1.mag, b2.mag), b1.positive, 0);

        if (isLargerThan(b1.mag, b2.mag))
            return new BigBinary(magnitudeMinus(b1.mag, b2.mag), b1.positive, 0);
        else
            return new BigBinary(magnitudeMinus(b2.mag, b1.mag), b2.positive, 0);
    }

    public static BigBinary minus(BigBinary b1, BigBinary b2) {
        if (b1.positive != b2.positive)
            return new BigBinary(magnitudeAdd(b1.mag, b2.mag), b1.positive, 0);

        if (isLargerThan(b1.mag, b2.mag))
            return new BigBinary(magnitudeMinus(b1.mag, b2.mag), b1.positive, 0);
        else
            return new BigBinary(magnitudeMinus(b2.mag, b1.mag), !b1.positive, 0);
    }

    public static BigBinary multiply(BigBinary b1, BigBinary b2) {
        return new BigBinary(magnitudeMultiply(b1.mag, b2.mag), b1.positive == b2.positive, 0);
    }

    private static int[] magnitudeAdd(int[] lhs, int[] rhs) {
        // make sure lhs > rhs
        if (rhs.length > lhs.length)
            return magnitudeAdd(rhs, lhs);

        int[] res = new int[lhs.length + 1];
        int carry = 0;
        for (int i = 0; i < lhs.length; i++) {
            int b = i < rhs.length ? rhs[i] : 0;
            long r = (long) lhs[i] + b + carry;
            res[i] = (int) (r & MAG_MASK);
            carry = (int) (r >>> MAG_LENGTH);
        }
        res[lhs.length] = carry;
        return shrink(res);
    }

    private static int[] magnitudeMinus(int[] lhs, int[] rhs) {
        // assume lhs > rhs
        int[] res = new int[lhs.length];
        int carry = 0;
        for (int i = 0; i < lhs.length; i++) {
            int b = i < rhs.length ? rhs[i] : 0;
            long r = (long) lhs[i] - b - carry;
            carry = 0;
            if (r < 0) {
                r += MAG_RANGE;
                carry = 1;
            }
            res[i] = (int) r;
        }
        return shrink(res);
    }

    public static int[] magnitudeMultiply(int[] lhs, int[] rhs) {
        int[] res = new int[lhs.length + rhs.length + 1];
        for (int i = 0; i < lhs.length; i++) {
            int carry = 0;
            for (int j = 0; j < rhs.length; j++) {
                long val = (long) lhs[i] * rhs[j] + res[i + j] + carry;
                res[i + j] = (int) (val & MAG_MASK);
                carry = (int) (val >>> MAG_LENGTH);
            }
            res[i + rhs.length] += carry;
        }
        return shrink(res);
    }

    private static boolean isLargerThan(int[] lhs, int[] rhs) {
        if (lhs.length != rhs.length)
            return lhs.length > rhs.length;
        for (int i = lhs.length - 1; i >= 0; i--)
            if (lhs[i] != rhs[i])
                return lhs[i] > rhs[i];
        return false;
    }

    // shrink leading zeros
    private static int[] shrink(int[] arr) {
        int j = arr.length;
        while (j > 0 && arr[j - 1] == 0)
            j--;
        return j == arr.length ? arr : Arrays.copyOf(arr, j);
    }

    @Override
    public String toString() {
        if (this.mag.length == 0)
            return "0";

        StringBuilder builder = new StringBuilder();
        if (!this.positive)
            builder.append('-');

        builder.append(Integer.toBinaryString(mag[mag.length - 1]));
        for (int i = mag.length - 2; i >= 0; i--) {
            String s = Integer.toBinaryString(mag[i]);
            builder.append("0".repeat(MAG_LENGTH - s.length()));
            builder.append(s);
        }
        return builder.toString();
    }
}
