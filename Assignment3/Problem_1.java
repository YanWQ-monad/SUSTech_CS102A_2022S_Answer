import java.util.Scanner;

public class Problem_1 {
    public static String toUppercaseString(String str) {
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                builder.append(Character.toUpperCase(ch));
            }
            else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static boolean isUppercase(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }

    public static String decryptFence(String cipher) {
        int size = cipher.charAt(cipher.length() - 1) - '0';
        StringBuilder builder = new StringBuilder(cipher.length() - 1);
        for (int i = 0; i < size; i++)
            for (int j = i; j < cipher.length() - 1; j += size)
                builder.append(cipher.charAt(j));

        return builder.toString();
    }

    public static String decryptCaesar(String cipher, int n) {
        StringBuilder builder = new StringBuilder(cipher.length());
        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (isUppercase(ch)) {
                int idx = ch - 'A';
                char target = (char) ((idx + n) % 26 + 'A');
                builder.append(target);
            }
            else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static String decryptVigenere(String cipher, String key) {
        int j = 0;
        StringBuilder builder = new StringBuilder(cipher.length());
        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (isUppercase(ch)) {
                int lhs = ch - 'A';
                int rhs = key.charAt(j) - 'A';
                char target = (char) ((lhs + rhs) % 26 + 'A');
                builder.append(target);
                j = (j + 1) % key.length();
            }
            else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fenceCipher = toUppercaseString(scanner.nextLine());
        String caesarCipher = toUppercaseString(scanner.nextLine());
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        String vigenereCipher = decryptCaesar(caesarCipher, n);
        String fenceText = decryptFence(fenceCipher);
        String vigenereKey = fenceText.substring(0, m);
        String text = decryptVigenere(vigenereCipher, vigenereKey);
        System.out.println(text);

        System.out.println(fenceText);
    }
}
