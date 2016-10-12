package com.company.ICPC;

import java.util.Scanner;

/**
 * Created by ingared on 10/20/15.
 */
public class LatticePrint {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner((System.in));
        while (true) {

            int a = in.nextInt();
            int b = in.nextInt();

            int k =0;

            int alength = findLength(a);
            int blength = findLength(b);

            if (a == 0 && b==0) {
                break;
            }

            int matLength = matrixLength(blength);
            int matbreadth = matrixBreadth(alength);

            char[][] mat = new char[matLength][matbreadth];

            // Initialize the matrix with empty spaces
            initializeMat(mat, matLength, matbreadth);

            // Update the borders of the matrix
            updateFirstAndLastRow(mat, matLength, matbreadth);

            // Update the separations
            updatetheElements(mat, matLength, matbreadth, a, b);
            //printMatrix(mat, matLength, matbreadth);
            //System.out.println();

            // update the grid with values of integers
            updateRowsAndColumnValues(mat, matLength, matbreadth, a, b);
            //printMatrix(mat, matLength, matbreadth);
            //System.out.println();

            //Update the final result
            int product = a*b;
            updateProductRes(mat, matLength, matbreadth, a,b);
            printMatrix(mat, matLength, matbreadth);
            //System.out.println();
        }
    }

    private static void updateProductRes(char[][] mat, int matLength, int matbreadth, int a, int b) {

        int alen = findLength(a);
        int blen = findLength(b);
        int product = a * b;
        int plen = findLength(product);
        int[] pArr = new int[plen];

        int divideA = 10;
        for (int i = plen - 1; i >= 0; i--) {
            int val = product % divideA;
            product = product / divideA;
            pArr[i] = val;
        }

        char diag = '/';
        //System.out.println("The product is " +product +" , length is " + plen );

        //for (int i=0; i< plen; i++) {
            //System.out.print(pArr[i] + " , ");
        //}
        //System.out.println();

        int k = 0;
        if (plen <= alen) {
            for (int j = plen - 1; j >= 0; j--) {
                //System.out.println("Values are " + j + " , " + (4 + 4 * j) + " , " + (plen - k) + " , " + pArr[plen - 1 - k]);
                if (j == 0) {
                    mat[matLength - 2][3 + 4 * j] = Character.forDigit(pArr[plen - 1 - k], 10);
                } else {
                    mat[matLength - 2][3 + 4 * j] = Character.forDigit(pArr[plen - 1 - k], 10);
                    mat[matLength - 2][1 + 4 * j] = diag;
                }
                k++;
            }
        } else {

            for (int j = alen - 1; j >= 0; j--) {
                //System.out.println("Values are " + j + " , " + (4 + 4 * j) + " , " + (plen - k) + " , " + pArr[plen - 1 - k]);
                mat[matLength - 2][3 + 4 * j] = Character.forDigit(pArr[plen - 1 - k], 10);
                mat[matLength - 2][1 + 4 * j] = diag;
                k++;
            }
            int diff = plen - alen;
            for (int i = 0; i < diff ; i++) {
                mat[matLength - 4 * (i) - 4][1] = Character.forDigit(pArr[diff-1-i], 10);
                mat[matLength - 4 * i - 2][1] = diag;
            }
        }
    }

    private static void updateRowsAndColumnValues(char[][] mat, int matLength, int matbreadth, int a , int b) {

        int divideA = 10;
        int alen = findLength(a);
        int blen = findLength(b);

        int[] aArr = new int[alen];
        int[] bArr = new int[blen];

        for (int i= alen-1; i >= 0; i--) {
            int val = a%divideA;
            a  = a/divideA;
            aArr[i] = val;
            mat[1][4+4*i] = Character.forDigit(val,10);
        }

        divideA = 10;
        for (int i= blen-1; i >= 0; i--) {
            int val = b%divideA;
            b  = b/divideA;
            bArr[i] = val;
            mat[4+4*i][matbreadth-2] = Character.forDigit(val,10);
        }

        for (int i= 0; i < blen; i++) {
            for (int j=0; j < alen; j++) {
                int z = aArr[j] *bArr[i];
                int k2 = (z%10);
                int k3 = (z/10)%10;
                valueUpdate(mat, 2+4*i,2+4*j,k3,k2);
            }
        }
    }

    private static void initializeMat(char[][] mat, int matLength, int matbreadth) {
        for (int i=0; i < matLength; i++) {
            for (int j=0; j < matbreadth; j++) {
                char spacebar = ' ';
                mat[i][j] =  spacebar;
            }
        }
    }

    private static void updatetheElements(char[][] mat, int matLength, int matbreadth, int a, int b) {

        int alen = findLength(a);
        int blen = findLength(b);

        for (int i= 2; i < matLength-2; i++) {
            for (int j = 0; j < alen+1; j++ ) {
                char vertSpacebar = '|';
                mat[i][2+4*j] =  vertSpacebar;
            }
        }

        for (int i= 0; i < blen+1; i++) {
            for (int j = 2; j < matbreadth-2; j++ ) {
                char minusbar = '-';
                char posbar = '+';
                if (((j-2)%4) == 0)  mat[2+4*i][j] = posbar;
                else mat[2+4*i][j] =  minusbar;
            }
        }

        for (int i= 0; i < blen; i++) {
            for (int j = 0; j < alen; j++) {
                diagonalUpdate(mat, 2+4*i,2+4*j);
            }
        }

    }

    private static void diagonalUpdate(char[][] mat, int i, int j) {
        char diag = '/';
        mat[i+3][j+1] = diag;
        mat[i+2][j+2] = diag;
        mat[i+1][j+3] = diag;
    }

    private static void valueUpdate(char[][] mat, int i, int j, int a , int b) {
        mat[i+1][j+1] = Character.forDigit(a,10);
        mat[i+3][j+3] = Character.forDigit(b,10);
    }

    private static void updateFirstAndLastRow(char[][] mat, int matLength, int matbreadth) {

        char posbar = '+';
        mat[0][0] = posbar;
        mat[0][matbreadth-1] = posbar;
        mat[matLength-1][0] = posbar;
        mat[matLength-1][matbreadth-1] = posbar;

        for (int i = 1; i < matbreadth-1; i++){
            char minusbar = '-';
            mat[0][i] = minusbar;
            mat[matLength-1][i] = minusbar;
        }

        char vertSpacebar = '|';
        for (int i = 1; i < matLength-1; i++){
            mat[i][0] = vertSpacebar;
            mat[i][matbreadth-1] = vertSpacebar;
        }
    }

    private static void printMatrix(char[][] mat, int matLength, int matbreadth) {

        for (int i=0; i < matLength; i++) {
            for (int j=0; j< matbreadth; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    private static int findLength(int a) {

        if ( a < 10) return 1;
        else if ( a < 100) return 2;
        else if ( a < 1000) return 3;
        else if ( a < 10000) return 4;
        else if ( a < 100000) return 5;
        else if ( a < 1000000) return 6;
        else if ( a < 10000000) return 7;
        else if ( a < 100000000) return 8;
        else if ( a < 1000000000) return 9;
        else return 10;
    }

    private static int matrixLength(int a) {

        return (4  + a*3 + (a+1));
    }

    private static int matrixBreadth(int a) {
        return (4 + a*3 + (a+1));
    }

}
