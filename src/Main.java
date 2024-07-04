import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Game game = new Game();
    static String[][] mazeArray;

    public static void main(String[] args) throws FileNotFoundException {
        int opt=0;

        System.out.print("""
                1. Demo
                2. Example File
                Select one:\s""");
        opt = scanner.nextInt();

        switch (opt) {
            case 1:
                System.out.print("=== Demo ===\n");
                demo();
                break;
            case 2:
                System.out.print("=== From Example File ===\n");

                SwingUtilities.invokeLater(() -> {
                    try {
                        chooseFile();
                    } catch (InterruptedException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    static void demo() {
        File myFile = new File("./maze_folder/examples/maze10_1.txt");
        try {
            parseMazeFile(myFile);
            game.GameArray(mazeArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void chooseFile() throws InterruptedException, FileNotFoundException {
        File initialDirectory = new File("./maze_folder");
        JFrame myWindow = new JFrame("Maze Solver");
        myWindow.setSize(500, 400);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(initialDirectory);
        int returnVal = jFileChooser.showOpenDialog(myWindow);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            parseMazeFile(selectedFile);
            game.GameArray(mazeArray);
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    static void parseMazeFile(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
        int lines = 0;
        int columns = 0;

        while (scanner.hasNextLine()) {
            lines++;
            String line = scanner.nextLine();
            if (line.length() > columns) {
                columns = line.length();
            }
        }

        scanner.close();
        mazeArray = new String[lines][columns];
        scanner = new Scanner(file);
        int lineIndex = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                mazeArray[lineIndex][i] = String.valueOf(line.charAt(i));
            }
            lineIndex++;
        }
        scanner.close();

        // Debug: Print the mazeArray
        System.out.println("Parsed Maze:");
        for (String[] row : mazeArray) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
