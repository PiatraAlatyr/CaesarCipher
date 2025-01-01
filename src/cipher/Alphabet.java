package cipher;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {

    public static final List<Character> ALPHABET = new ArrayList<>();

    public static boolean isCharacterInALPHABET(char character) {
        return ALPHABET.contains(character);
    }

    static {
        addingEnglishLetters();
        addingRussianLetters();
        addingSpecialSymbols();
    }

    private static void addingEnglishLetters() {
        for (char c = 'A'; c <= 'Z'; ++c) {
            ALPHABET.add(c);
        }

        for (char c = 'a'; c <= 'z'; ++c) {
            ALPHABET.add(c);
        }
    }

    private static void addingRussianLetters() {
        for (char c = 'А'; c <= 'я'; ++c) {
            ALPHABET.add(c);
        }
    }

    private static void addingSpecialSymbols() {
        for (char c = 32; c <= 64; ++c) {
            ALPHABET.add(c);
        }

        for (char c = 91; c <= 96; ++c) {
            ALPHABET.add(c);
        }

        for (char c = 123; c <= 126; ++c) {
            ALPHABET.add(c);
        }
    }
}
