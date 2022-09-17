package Kmitl.OS.Lap7;

/* 1*/ import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };/* 5 */
        MyData matA = new MyData(inputA);/* 6 */
        MyData matB = new MyData(inputB);/* 7 */
        int matC_r = matA.data.length;/* 8 */
        int matC_c = matB.data[0].length;/* 9 */
        MyData matC = new MyData(matC_r, matC_c);/* 10 */
        // Q4 construct 2D array for each "thread"/* 11 */
        /*
         * with respected to each cell in matC,
         * then start each thread
         */

        Thread t1 = new Thread(new MatrixMulThread(0, 0, matA, matB, matC));
        t1.start();
        Thread t2 = new Thread(new MatrixMulThread(0, 1, matA, matB, matC));
        t2.start();
        Thread t3 = new Thread(new MatrixMulThread(1, 0, matA, matB, matC));
        t3.start();
        Thread t4 = new Thread(new MatrixMulThread(1, 1, matA, matB, matC));
        t4.start();

        // Q5 join each thread/* 12 */
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            System.out.println(e);
        }

        matC.show();/* 13 */
    }/* 14 */
}

class MatrixMulThread implements Runnable {/* 16 */
    int processing_row;/* 17 */
    int processing_col;
    MyData datA;/* 18 */
    MyData datB;
    MyData datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {/* 19 */
        /* 20 */
        // Q1 code here
        processing_col = tCol;
        processing_row = tRow;
        datA = a;
        datB = b;
        datC = c;
        /* 21 */ }

    /* Q2 */public void run() {/* 23 */
        // Q3/* 24 */
        int sizeCol = datA.data[0].length;
        int Multiple = 0;
        for (int i = 0; i < sizeCol; i++) {
            Multiple += (datA.data[processing_col][i] * datB.data[i][processing_row]);
        }
        datC.data[processing_col][processing_row] = Multiple;
        System.out.println("perform sum of multiplication of assigned row:" + processing_row + " and col:"
                + processing_col + " = " + Multiple);
    } /* 25 */
}/* 26 */

// class
class MyData {/* 27 */
    public int[][] data;/* 28 */

    MyData(int[][] m) {/* 29 */
        data = m;
    }

    MyData(int r, int c) {/* 30 */
        data = new int[r][c];/* 31 */
        for (int[] aRow : data)/* 32 */
            Arrays.fill(aRow, 9);/* 33 */
        // 9 will be overwritten anyway
    }/* 34 */

    void show() {/* 35 */
        System.out.println(Arrays.deepToString(data));
    }/* 36 */
} // class /* 37 */
