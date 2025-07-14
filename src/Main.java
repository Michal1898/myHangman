//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String string;

    public static void main(String[] args) {
        final String Words[] = {"skillmea", "Java", "Python", "Stress", "skull", "Anaconda",
                "paradies", "hell", "devil", "angel", "holiday", "hardware", "office"};
        boolean newGame = false;
        final int Attempts = 6;
        String guessedWord = "";
        String secretWord = "";
        do {
            //init game
            guessedWord = "";
            secretWord = Words[random.nextInt(Words.length)];
            secretWord = secretWord.toUpperCase();
            for (int indexOfLetter = 0; indexOfLetter < secretWord.length(); indexOfLetter++) {
                guessedWord += "-";
            }

            System.out.println("Hangman");
            System.out.println("vytvoril Michal Volf");
            Random random = new Random();

            System.out.println(secretWord);
            System.out.println(guessedWord);
            Scanner scanner = new Scanner(System.in);
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


}

