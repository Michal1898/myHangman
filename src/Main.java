//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static String string;

    public static void main(String[] args) {
        final String Words[] = {"skillmea", "Java", "Python", "Stress", "skull", "Anaconda",
                "paradies", "hell", "devil", "angel", "holiday", "hardware", "office"};
        boolean newGame = false;
        final int MAX_INCORRECT_GUESSES = 8;
        String guessedWord = "";
        String secretWord = "";
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        do {
            //init game
            guessedWord = "";

            secretWord = Words[random.nextInt(Words.length)];
            secretWord = secretWord.toUpperCase();
            LinkedList<Character> badLetters = new LinkedList<Character>();
            for (int indexOfLetter = 0; indexOfLetter < secretWord.length(); indexOfLetter++) {
                guessedWord += "-";
            }
            Integer badGuesses = 0;

            System.out.println(ANSI_BLUE + "Hangman" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "vytvoril Michal Volf" + ANSI_RESET);

            // guess, until you guess the word
            // or you use all attempts
            do {
                drawHangman(badGuesses);
                printGuessedWord(guessedWord);
                printBadLetters(badLetters);
                printMistakes(badGuesses, MAX_INCORRECT_GUESSES);

                Character guessedLetter = inputLetter();
                if (secretWord.contains(guessedLetter.toString())) {
                    if (guessedWord.contains(guessedLetter.toString())) {
                        System.out.println(ANSI_RED + "Tohle pismeno uz hadane slovo obsahuje!" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_GREEN + "Bravo! Uhadl jsi" + ANSI_RESET);
                        guessedWord = updateGuessedWord(guessedWord, secretWord, guessedLetter);
                    }
                    guessedWord = updateGuessedWord(guessedWord, secretWord, guessedLetter);
                } else if (badLetters.contains(guessedLetter)) {
                    System.out.println(ANSI_RED + "Tohle pismeno jsi uz zkousel!" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Netrefil jsi se!" + ANSI_RED);
                    badLetters.add(guessedLetter);
                    badGuesses++;
                }
            } while (guessedWord.contains("-") && badGuesses <= MAX_INCORRECT_GUESSES);
            //game over
            System.out.println(ANSI_RED + "Konec hry:" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "Zaverecna zprava:" + ANSI_RESET);
            drawHangman(badGuesses);
            printSecretWord(secretWord);
            printGuessedWord(guessedWord);
            printBadLetters(badLetters);
            if (guessedWord.contains("-")) {

                System.out.println(ANSI_RED + "Prohral jsi. Ha ha ha !!" + ANSI_RESET);
            } else {
                System.out.println(ANSI_BLUE + "Vyhral jsi. Gratuluji !!" + ANSI_RESET);
            }
            // Do you wish new game (ano / ne)
            newGame = anotherGame();
        } while (newGame);
        System.out.println(ANSI_RED + "Konec programu" + ANSI_RESET);
    }

    public static Boolean anotherGame() {
        Scanner again = new Scanner(System.in);
        String yesNo;
        while (true) {
            try {
                System.out.println("Nova hra?");
                yesNo = again.nextLine().toLowerCase();
                if ((yesNo.equals("ano")) || (yesNo.equals("ne"))) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "Odpovez ano, nebo ne!" + ANSI_RESET);
                    again.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                System.out.println("Odpovez ano, nebo ne!");
            }

        }
        if (yesNo.equals("ano")) {
            return true;
        } else {
            return false;
        }
    }

    public static Character inputLetter() {
        Scanner quessChar = new Scanner(System.in);
        String inputChar;
        Character guessedLetter;

        while (true) {
            try {
                System.out.println("Zadej znak ve slove: ");
                inputChar = quessChar.nextLine();
                if (inputChar.length() != 1) {
                    throw new Exception(ANSI_RED + "Zadej prave 1 znak!" + ANSI_RESET);
                } else if (!Character.isLetter(inputChar.charAt(0))) {
                    throw new Exception(ANSI_RED + "Znak musi byt A-Z!" + ANSI_RESET);
                }
                break;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Invalid input!" + e.getMessage() + ANSI_RESET);
            }

        }
        return Character.toUpperCase(inputChar.charAt(0));

    }

    public static String updateGuessedWord(String guessedWord, String hiddenWord, Character insertChar) {
        String updatedWord = "";
        for (int indexOfLetter = 0; indexOfLetter < hiddenWord.length(); indexOfLetter++) {
            if (hiddenWord.charAt(indexOfLetter) == insertChar) {
                updatedWord += insertChar.toString();
            } else {
                updatedWord += guessedWord.charAt(indexOfLetter);
            }
        }
        return updatedWord;
    }

    public static Integer drawHangman(Integer Mistakes) {
        String hangmanColor;
        if (Mistakes < 3) {
            hangmanColor = ANSI_GREEN;
        } else if (Mistakes < 6) {
            hangmanColor = ANSI_YELLOW;
        } else {
            hangmanColor = ANSI_RED;
        }

        System.out.println(hangmanColor + "HangMan");
        switch (Mistakes) {
            case 0:
                System.out.println("            ");
                System.out.println("            ");
                System.out.println("            ");
                System.out.println("            ");
                System.out.println("            ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 1:
                System.out.println("            ");
                System.out.println("   |         ");
                System.out.println("   |         ");
                System.out.println("   |         ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 2:
                System.out.println("   _______   ");
                System.out.println("   |         ");
                System.out.println("   |         ");
                System.out.println("   |         ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;

            case 3:
                System.out.println("   _______   ");
                System.out.println("   |     |   ");
                System.out.println("   |         ");
                System.out.println("   |         ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 4:
                System.out.println("   _______   ");
                System.out.println("   |     |   ");
                System.out.println("   |     O   ");
                System.out.println("   |         ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 5:
                System.out.println("   _______   ");
                System.out.println("   |     |   ");
                System.out.println("   |     O/   ");
                System.out.println("   |         ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 6:
                System.out.println("   _______   ");
                System.out.println("   |    |   ");
                System.out.println("   |   \\O/   ");
                System.out.println("   |         ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 7:
                System.out.println("   _______   ");
                System.out.println("   |     |   ");
                System.out.println("   |    \\O/   ");
                System.out.println("   |     |   ");
                System.out.println("   |        ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
            case 8:
                System.out.println("   _______   ");
                System.out.println("   |     |   ");
                System.out.println("   |    \\O/   ");
                System.out.println("   |     |   ");
                System.out.println("   |      \\  ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;

            default:
                System.out.println("   _______   ");
                System.out.println("   |     |   ");
                System.out.println("   |    \\O/   ");
                System.out.println("   |     |   ");
                System.out.println("   |    / \\  ");
                System.out.println("   /\\        ");
                System.out.println("-------------");
                break;
        }
        System.out.println("HangMan" + ANSI_RESET);
        return Mistakes;
    }

    public static Integer printMistakes(int mistakes, int maxMistakes) {
        System.out.println("Chybnych pokusu: " + mistakes);
        System.out.println("Muzes se splest uz jen " + (maxMistakes - mistakes) + " krat.");
        return 1;
    }

    public static Integer printAttempt(Integer attemptNo) {
        if (attemptNo > 0) {
            System.out.println("Pokus cislo: " + (attemptNo));
        } else {
            System.out.println("Hra zacina... ");
        }
        return attemptNo;
    }

    public static Integer printBadLetters(LinkedList<Character> poorLetters) {
        System.out.print("Slovo neobsahuje: ");
        System.out.print(ANSI_RED + poorLetters + ANSI_RESET);
        System.out.print("\n");
        return 1;
    }

    public static Integer printGuessedWord(String guessedWord) {
        Integer hit = 0;
        Integer rest = 0;
        for (int c = 0; c < guessedWord.length(); c++) {
            if (guessedWord.charAt(c) == '-') {

                rest++;
            } else {
                hit++;
            }
        }
        System.out.println(ANSI_YELLOW + "Hadane slovo: " + guessedWord + ANSI_RESET);
        System.out.println("Ma celkem: " + guessedWord.length() + " znaku.");
        System.out.println("Uhadl jsi: " + hit + " znaku.");
        System.out.println("Zbyva uhadnout: " + rest + " znaku.");
        return 1;
    }

    public static Integer printSecretWord(String secretWord) {
        System.out.println("Skryte slovo: " + secretWord);
        return 1;
    }
}
