import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
        
    static void multMatrix(int[][] matrix1, int[][] matrix2, int[][] matrix3) throws IOException{
        //Multiplies matrix 1 and 2
        for (int i=0; i<matrix3.length; i++){
            for(int j=0; j<matrix3[i].length; j++){
                for(int k=0; k<matrix1[i].length; k++) {
                    matrix3[i][j] += matrix1[i][k] * matrix2[k][j];                    
                }                
            }                 
        }

        //Prints matrix to terminal
        System.out.println("\nMultiplied matrix: ");
        for(int i=0; i<matrix3.length; i++){
            for(int j=0; j<matrix3[i].length; j++){
                System.out.print(matrix3[i][j]+" ");
            }
            System.out.println();
        }

        //Writes to "matrix3.txt" file
        FileWriter writer = new FileWriter("matrix3.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write("%d %d\n".formatted(matrix3.length, matrix3[0].length));
        for(int i=0; i<matrix3.length; i++){
            for(int j=0; j<matrix3[i].length; j++){
                buffer.write("%d".formatted(matrix3[i][j]));
                if (j != matrix3[i].length -1){
                    buffer.write(" ");
                }
            }
            if (i != matrix3.length -1){
                buffer.write("\n");
            }            
        }
        buffer.close();
        
    }

    static void fileMatrix() throws IOException{        
        Scanner scanner = new Scanner(System.in);

        //User enters first file and program tests if file is valid.
        //I created "matrixA.txt" for testing.
        System.out.println("Enter first file name: ");
        String input1 = scanner.nextLine();
        File file1 = new File(input1);
        if(file1.exists()){
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }

        //User enters second file and program tests if file is valid.
        //I created "matrixB.txt" for testing.
        System.out.println("\nEnter second file name: ");
        String input2 = scanner.nextLine();
        File file2 = new File(input2);
        if(file2.exists()){
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }

        //Matricies are read and stored from the files and sent to be multiplied.
        int[][] matrix1 = readMatrix(file1);
        int[][] matrix2 = readMatrix(file2);
        int rows = matrix1.length;
        int cols = matrix2[0].length;
        int[][] matrix3 = new int[rows][cols];
        multMatrix(matrix1, matrix2, matrix3);        
        scanner.close();
    }

    static int[][] readMatrix(File file) throws IOException{
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        String[] size = line.split(" ");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);

        //Reads matrix from file
        int[][] matrix = new int[rows][cols];
        for(int i=0; i<rows; i++){
            line = buffer.readLine();
            String[] row = line.split(" ");
            for(int j=0; j<cols; j++){
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }

        //Outputs Matrix to terminal
        System.out.println("\nMatrix from "+file);
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        buffer.close();
        return matrix;        
    }

    static void randomMatrix() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int randNum = 0;

        System.out.println("Enter number of rows to generate two square matrices.");
        int input = scanner.nextInt();
        int[][] matrix1 = new int[input][input];
        int[][] matrix2 = new int[input][input];
        int[][] matrix3 = new int[input][input];

        //Generates first matrix
        System.out.println("\nFirst matrix: ");
        for (int i=0; i<matrix1.length; i++){
            for(int j=0; j<matrix1[i].length; j++){                
                randNum = rand.nextInt(10);
                matrix1[i][j] = randNum;
                System.out.print(randNum+" ");
            }
            System.out.println();      
        }

        //Generates second matrix
        System.out.println("\nSecond matrix: ");
        for (int i=0; i<matrix2.length; i++){
            for(int j=0; j<matrix2[i].length; j++){
                randNum = rand.nextInt(10);
                matrix2[i][j]= randNum;
                System.out.print(randNum+" ");
            }
            System.out.println();      
        }

        //Prints first generated matrix to "matrix1.txt"
        FileWriter writer = new FileWriter("matrix1.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write("%d %d\n".formatted(matrix1.length, matrix1[0].length));
        for(int i=0; i<matrix1.length; i++){
            for(int j=0; j<matrix1[i].length; j++){
                buffer.write("%d".formatted(matrix1[i][j]));
                if (j != matrix1[i].length -1){
                    buffer.write(" ");
                }
            }
            if (i != matrix1.length -1){
                buffer.write("\n");
            }            
        }

        //Prints second generated matrix to "matrix2.txt"
        FileWriter writer2 = new FileWriter("matrix2.txt");
        BufferedWriter buffer2 = new BufferedWriter(writer2);
        buffer2.write("%d %d\n".formatted(matrix2.length, matrix2[0].length));
        for(int i=0; i<matrix2.length; i++){
            for(int j=0; j<matrix2[i].length; j++){
                buffer2.write("%d".formatted(matrix2[i][j]));
                if (j != matrix2[i].length -1){
                    buffer2.write(" ");
                }
            }
            if (i != matrix2.length -1){
                buffer2.write("\n");
            }            
        }        
        
        //Matricies are multiplied
        multMatrix(matrix1, matrix2, matrix3);
        buffer.close();
        buffer2.close();
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type \"1\" to read from files. Type \"2\" to generate random matrix.");
        int input = scanner.nextInt();
        if(input == 1){
            fileMatrix();
        } else if (input == 2){
            randomMatrix();
        } else{
            System.out.println("Not a valid response.");
        }
        scanner.close();
    }
}
