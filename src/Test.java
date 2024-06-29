public class Test {
    void testPrint(String [][]mazeArray){
        //             Print the mazeArray (for verification)
            for (int i = 0; i < mazeArray.length; i++) {
                for (int j = 0; j < mazeArray[i].length; j++) {
                    System.out.print(mazeArray[i][j]);
                }
                System.out.println();
            }
    }
}
