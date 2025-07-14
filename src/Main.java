//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String string;

    public static void main(String[] args) {
        final String Words[] = {"skillmea", "Java", "Python", "Stress", "skull", "Anaconda",
                "paradies", "hell", "devil", "angel", "holiday", "hardware", "office"};
        boolean newGame = false;
        Integer attemptNo;
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
            attemptNo = 0;

            System.out.println("Hangman");
            System.out.println("vytvoril Michal Volf");

            System.out.println(secretWord);
            System.out.println(guessedWord);
            // guess, until you guess the word
            // or you use all attempts
            do {
                drawHangman(badGuesses);
                printAttempt(attemptNo);
                printGuessedWord(guessedWord);
                printBadLetters(badLetters);
                printMistakes(badGuesses, MAX_INCORRECT_GUESSES);

                Character guessedLetter = inputLetter();
                if (secretWord.contains(guessedLetter.toString())) {
                    if (guessedWord.contains(guessedLetter.toString())) {
                        System.out.println("Tohle pismeno uz hadane slovo obsahuje!");
                    } else {
                        System.out.println("Bravo! Uhadl jsi");
                        guessedWord = updateGuessedWord(guessedWord, secretWord, guessedLetter);
                        attemptNo++;
                    }
                    guessedWord = updateGuessedWord(guessedWord, secretWord, guessedLetter);
                } else if (badLetters.contains(guessedLetter)) {
                    System.out.println("Tohle pismeno jsi uz zkousel!");
                } else {
                    System.out.println("Netrefil jsi se!");
                    badLetters.add(guessedLetter);
                    badGuesses++;
                    attemptNo++;
                }
                System.out.println(guessedWord);
            } while (guessedWord.contains("-") && badGuesses <= MAX_INCORRECT_GUESSES);
            //game over
            System.out.println("Konec hry:");
            System.out.println("Zaverecna zprava:");
            drawHangman(badGuesses);
            printAttempt(attemptNo);
            printGuessedWord(guessedWord);
            printBadLetters(badLetters);
            if (guessedWord.contains("-")) {

                System.out.println("Prohral jsi. Ha ha ha !!");
            } else {
                System.out.println("Vyhral jsi. Gratuluji !!");
            }
            // Do you wish new game (ano / ne)
            newGame = anotherGame();
            System.out.println(newGame);
        } while (newGame);
        System.out.println("Konec hry");
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
                    System.out.println("Odpovez ano, nebo ne!");
                    again.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                System.out.println("Odpovez ano, nebo ne!");
            }

        }
        if (yesNo.equals("ano")) {
            System.out.println("Pravda");
            return true;
        } else {
            System.out.println("NePravda");
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
                    throw new Exception("Zadej prave 1 znak!");
                } else if (!Character.isLetter(inputChar.charAt(0))) {
                    throw new Exception("Znak musi byt A-Z!");
                }
                System.out.println("breaknu ");
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!" + e.getMessage());
            }

            System.out.println("opakuju: ");
        }

        System.out.println("return");
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
        System.out.println("HangMan");
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
        System.out.println("HangMan");
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
        System.out.print(poorLetters);
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
        System.out.println("Hadane slovo: " + guessedWord);
        System.out.println("Ma celkem: " + guessedWord.length() + " znaku.");
        System.out.println("Uhadl jsi: " + hit + " znaku.");
        System.out.println("Zbyva uhadnout: " + rest + " znaku.");
        return 1;
    }
}
