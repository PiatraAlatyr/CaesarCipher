package cipher;

import static java.lang.Character.*;

public class Cipher {

    private Cipher() {
        throw new IllegalStateException("Utility class");
    }

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ',
            'ы', 'ь', 'э', 'я', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '«', '»',
            '"', '\'', ':', '!', '?', ' ', '+', '-'};

    private static String cipher(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char character : text.toCharArray()) {
            int index = getIndex(toLowerCase(character));
            if (index == -1) {
                //Неизвестный символ
                encrypted.append(character);
                continue;
            }
            index += shift % ALPHABET.length;
            if (index >= ALPHABET.length) index -= ALPHABET.length;
            else if (index < 0) index += ALPHABET.length;
            if (isUpperCase(character)) {
                encrypted.append(toUpperCase(ALPHABET[index]));
            } else encrypted.append(ALPHABET[index]);
        }
        return encrypted.toString();
    }

    public static String encrypt(String decryptedText, int key) {
        return cipher(decryptedText, key);
    }

    public static String decrypt(String encryptedText, int key) {
        return cipher(encryptedText, -key);
    }

    public static String brutForce(String encryptedText) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ALPHABET.length; i++) {
            String text = cipher(encryptedText, i);
            builder.append("Key: " + (ALPHABET.length - i) + "\n");
            builder.append(text);
            builder.append("\n ====================== \n");
        }
        return builder.toString();
    }

    private static int getIndex(char character) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == character) return i;
        }
        return -1;
    }
}