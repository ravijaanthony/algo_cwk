/**
 * This is a Test file used to optimize the game for different variation of mazes
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    static String[][] mazeArray;
    static Scanner scanner = new Scanner(System.in);
    static Game game = new Game();

    public static void main(String[] args) {
        int opt = 0;


        System.out.print("=== TEST ===\n" +
                "1. Example maze10_1\n" +
                "2. Example maze10_2\n" +
                "3. Example maze30_5\n" +
                "4. Benchmark puzzle_10\n" +
                "5. Benchmark puzzle_20\n" +
                "6. Benchmark puzzle_40\n" +
                "7. Benchmark puzzle_2560\n" +
                "99. Exit\n" +
                "\n" +
                "Enter Test number: ");

        opt = scanner.nextInt();

        switch (opt) {
            case 1:
                File myFile = new File("./maze_folder/examples/maze10_1.txt");
                testMethod(myFile);
                break;
            case 2:
                myFile = new File("./maze_folder/examples/maze10_2.txt");
                testMethod(myFile);
                break;
            case 3:
                myFile = new File("./maze_folder/examples/maze30_5.txt");
                testMethod(myFile);
                break;
            case 4:
                myFile = new File("./maze_folder/benchmark_series/puzzle_10.txt");
                testMethod(myFile);
                break;
            case 5:
                myFile = new File("./maze_folder/benchmark_series/puzzle_20.txt");
                testMethod(myFile);
                break;
            case 6:
                myFile = new File("./maze_folder/benchmark_series/puzzle_40.txt");
                testMethod(myFile);
                break;
            case 7:
                myFile = new File("./maze_folder/benchmark_series/puzzle_2560.txt");
                testMethod(myFile);
                break;

            default:
                System.out.println("No input file given");
                break;
        }

    }

    static void testMethod(File myFile) {
        try {
            int lines = 0;
            int columns = 0;
            scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                lines++;
                String line = scanner.nextLine();
                if (line.length() > columns) {
                    columns = line.length();
                }
            }

            scanner.close();

            // Initialize mazeArray
            mazeArray = new String[lines][columns];

            scanner = new Scanner(myFile);
            int lineIndex = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    mazeArray[lineIndex][i] = String.valueOf(line.charAt(i));
                }
                lineIndex++;
            }
            scanner.close();

            // Send the maze as an array to the game
            game.GameArray(mazeArray);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}