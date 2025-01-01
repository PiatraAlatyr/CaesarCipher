package cipher;

import java.util.Arrays;
import java.util.List;

import static cipher.Alphabet.*;

public class Cipher {

    public static String encrypt(String text, int key) {
        return shiftAllText(text, key);
    }

    public static String decrypt(String encryptedText, int key) {
        return shiftAllText(encryptedText, -key);
    }

    public static String brutForce(String encryptedText, String keyText) {
        StringBuilder result = new StringBuilder();

        if (keyText != null) {
            cleverBrutForce(encryptedText, keyText, result);
        } else {
            bluntBrutForce(encryptedText, result);
        }

        return result.toString();
    }

    private static void bluntBrutForce(String encryptedText, StringBuilder result) {
        for (int i = 0; i < ALPHABET.size(); i++) {
            String decryptedText = shiftAllText(encryptedText, i);

            if (decryptedText.contains(", ") || decryptedText.endsWith(".") || decryptedText.endsWith("!") || decryptedText.endsWith("?")) {
                result.append(constructResultDecryptedText(decryptedText, i));
            }
        }
    }

    private static void cleverBrutForce(String encryptedText, String keyText, StringBuilder result) {
        String[] dictionary = cleanDictionary(keyText);

        for (int i = 0; i < ALPHABET.size(); i++) {
            String decryptedText = shiftAllText(encryptedText, i);
            List<String> arrayOfDecryptedText = Arrays.asList(decryptedText.split(" "));

            for (String keyWord : dictionary) {
                if (arrayOfDecryptedText.contains(keyWord)) {
                    result.append(constructResultDecryptedText(decryptedText, i));
                    break;
                }
            }
        }
    }

    private static String[] cleanDictionary(String keyText) {
        return keyText.replaceAll("\\d","").replace("\\r\\n", " ").split(" ");
    }

    private static String constructResultDecryptedText(String text, int key) {
        return "Key: " + (ALPHABET.size() - key) + "\n" +
                text +
                "\n======================\n";
    }

    private static String shiftAllText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        int resolvedShift = resolveShift(shift);

        for (char character : text.toCharArray()) {
            result.append(shiftChar(character, resolvedShift));
        }

        return result.toString();
    }

    private static char shiftChar(char character, int shift) {
        if (!isCharacterInALPHABET(character)) {
            return character;
        }

        return resolveShiftedSymbol(character, shift);
    }

    private static char resolveShiftedSymbol(char character, int shift) {
        int shiftedIndex = ALPHABET.indexOf(character) + shift;

        if (shiftedIndex < 0) {
            return ALPHABET.get(shiftedIndex + ALPHABET.size());
        } else if (shiftedIndex >= ALPHABET.size()) {
            return ALPHABET.get(shiftedIndex - ALPHABET.size());
        } else {
            return ALPHABET.get(shiftedIndex);
        }
    }

    private static int resolveShift(int shift) {
        return shift % ALPHABET.size();
    }
}