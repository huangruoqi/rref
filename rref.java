import java.util.*;
public class rref {
    public static void main(String[] args) {
        float[][] matrix;
        if (args.length != 2) {
            System.exit(0);
        }

        matrix = new float[Integer.parseInt(args[0])][Integer.parseInt(args[1])];
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
        for (int r = 0, c = 0;c<maxPivot&&r<maxPivot;r++,c++) {
            if (checkPivot(m,c)) {
                elimination(m,r,c);
            } 
            else {
                r--;
            }
        }
        for (int r = maxPivot-1;r>=0;r--) {
            backwardElimination(m,r);
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

    public static void elimination(float[][] m, int r, int c) {
        int row = m.length;
        int column = m[0].length;
        float factor = m[r][c];
        for (int i = 0;i<column;i++) {
            m[r][i]/=factor;
        }

        for (int i = r+1;i<row;i++) {
            factor = m[i][c];
            for (int j = 0;j<column;j++) {
                m[i][j] -= (m[r][j]*factor);
            }
        }
    }

    public static boolean backwardElimination(float[][] m, int r) {
        int row = m.length;
        int column = m[0].length;
        int c = -1;
        for (int i=0;i<column;i++) {
            if (m[r][i]!=0) {
                c = i; 
                break;      
            } 
        }
        if (c==-1) {
            return false;
        }
        float factor = m[r][c];
        for (int i = 0;i<column;i++) {
            m[r][i]/=factor;
        }

        for (int i = r-1;i>=0;i--) {
            factor = m[i][c];
            for (int j = 0;j<column;j++) {
                m[i][j] -= (m[r][j]*factor);
            }
        }
        return true;
    }

    public static void printArray(float[][] m) {
        int row = m.length;
        int column = m[0].length;
        for (int i = 0;i<row;i++) {
            System.out.print("|");
            for (int j = 0;j<column;j++) {
                System.out.printf("%9.2f", m[i][j]);
            }
            System.out.println("     |");
        }
    }

}