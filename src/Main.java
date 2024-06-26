import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[][] mazeArray;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("""
                1. Demo
                2. Example File
                Select one:\s""");
        int opt = scanner.nextInt();

        switch (opt) {
            case 1:
                System.out.print("=== Demo ===");
                System.out.println();
                demo();
                break;
            case 2:
                System.out.print("=== From Example File ===");
                System.out.println();
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

    /// Demo file
    static void demo() {
        File myFile = new File("./maze_folder/examples/maze10_1.txt");
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


//             Print the mazeArray (for verification)
            for (int i = 0; i < mazeArray.length; i++) {
                for (int j = 0; j < mazeArray[i].length; j++) {
                    System.out.print(mazeArray[i][j]);
                }
                System.out.println();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /// Choose the file
    static void chooseFile() throws InterruptedException, FileNotFoundException {

        // Set the initial directory to the specific folder
        File initialDirectory = new File("./maze_folder");

//        if (initialDirectory.exists() && initialDirectory.isDirectory()) {
//            fileChooser.setCurrentDirectory(initialDirectory);
//        } else {
//            System.out.println("Directory does not exist.");
//            return;
//        }

        JFrame myWindow = new JFrame("Maze Solver");
        myWindow.setSize(500,400);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(initialDirectory);
        int returnVal = jFileChooser.showOpenDialog(myWindow);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            readAndPrintMazeFile(selectedFile);
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    /// Read and print the maze file
    static void readAndPrintMazeFile(File file) {
        try {
            int lines = 0;
            int columns = 0;
            scanner = new Scanner(file);
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

            // Print the mazeArray (for verification)
            for (int i = 0; i < mazeArray.length; i++) {
                for (int j = 0; j < mazeArray[i].length; j++) {
                    System.out.print(mazeArray[i][j]);
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}