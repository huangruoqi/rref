import java.util.*;
public class rref {
    public static void main(String[] args) {
        float[][] matrix;
        // if (args.length != 2) {
        //     System.exit(0);
        // }

        //matrix = new float[Integer.parseInt(args[0])][Integer.parseInt(args[1])];
        matrix = new float[2][3];
        enterMatrix(matrix);
        printArray(matrix);
        System.out.println();
        rrefAlgorithm(matrix);
        printArray(matrix);
    }

    public static void enterMatrix(float[][] m) {
        Scanner scan = new Scanner(System.in);
        int row = m.length;
        int column = m[0].length;
        for (int i = 0;i<row;i++) {
            for (int j = 0;j<column;j++) {
                m[i][j] = scan.nextFloat();
            }
        }

    }

    public static void rrefAlgorithm(float[][] m) {
        int row = m.length;
        int column = m[0].length;
        int maxPivot = Math.min(row,column);
        for (int i = 0;i<maxPivot;i++) {
            if (checkPivot(m,i)) {
                elimination(m,i);
            }
        }
        for (int i = maxPivot-1;i>=0;i--) {
            if (checkPivot(m,i)) {
                backwardElimination(m,i);
            }
        }
        for (int i = 0;i<row;i++) {
            for (int j = 0;j<column;j++) {
                if (m[i][j] == 0 ) {
                    m[i][j] = 0;
                }
            }
        }

    }

    public static boolean checkPivot(float[][] m, int n) {
        if (m[n][n]!=0) {
            return true;
        }
        for (int i = n+1;i<m.length;i++) {
            if (m[i][n]!=0){
                float[] temp = m[i];
                m[i] = m[n];
                m[n] = temp;
                return true;
            }
        }
        return false;
    }

    public static void elimination(float[][] m, int n) {
        int row = m.length;
        int column = m[0].length;
        float factor = m[n][n];
        for (int i = 0;i<column;i++) {
            m[n][i]/=factor;
        }

        for (int i = n+1;i<row;i++) {
            factor = m[i][n];
            for (int j = 0;j<column;j++) {
                m[i][j] -= (m[n][j]*factor);
            }
        }
    }

    public static void backwardElimination(float[][] m, int n) {
        int row = m.length;
        int column = m[0].length;
        float factor = m[n][n];
        for (int i = 0;i<column;i++) {
            m[n][i]/=factor;
        }

        for (int i = n-1;i>=0;i--) {
            factor = m[i][n];
            for (int j = 0;j<column;j++) {
                m[i][j] -= (m[n][j]*factor);
            }
        }
    }

    public static void printArray(float[][] m) {
        int row = m.length;
        int column = m[0].length;
        
        for (int i = 0;i<row;i++) {
            System.out.print("|\t");
            for (int j = 0;j<column;j++) {
                if (j!=0) System.out.print("\t"); 
                System.out.printf("%f", m[i][j]);
            }
            System.out.println("\t|");
        }
    }

}