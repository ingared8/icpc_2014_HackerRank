package com.company.ICPC;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ingared on 10/21/15.
 */
public class WetTiles {

    public static void updateWalls1(int x1,int y1, int x2, int y2,int[][] mat){

        if ( x1== x2) {
            for (int i=Math.min(y1,y2); i <= Math.max(y1,y2) ; i++) {
                mat[x1][i] = -1;
            }
        } else if ( y1 == y2) {
            for (int i=Math.min(x1,x2); i <= Math.max(x1,x2) ; i++) {
                mat[i][y1] = -1;
            }
        } else if (x2 > x1) {
            if (y2 > y1) {
                for (int k= x1 ; k <= x2 ; k++ ) mat[k][y1+k-x1] = -1;
            } else {
                for (int k= x1 ; k <= x2 ; k++ ) mat[k][y1-(k-x1)] = -1;
            }
        } else  {
            if (y2 > y1) {
                for (int k= x2 ; k <= x1 ; k++ ) mat[k][y2-(k-x2)] = -1;
            } else {
                for (int k= x2 ; k <= x1 ; k++ ) mat[k][y2 + (k-x2)] = -1;
            }
        }
    }

    public static void updateIterations(int[][] mat, int k, int X, int Y) {

        for (int z=1;z < k; z++) {
            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    if (mat[i][j] == z) {
                        updateValue(mat, z + 1, i, j, X, Y);
                    }
                }
            }
        }
    }

    public static void  updateIterations1(int[][] mat, int k, int X, int Y,List<Integer> leaklist ) {

        int z = 0;
        int value = 0;
        List<Integer> newList = new LinkedList<>();
        //System.out.println("For Loop " + (z+1) + " Size is " + value + " k is " + k);
        while ((leaklist.size() != 0) && (z < k )) {
            //System.out.println("For Loop " + (z+1) + " Size is " + value);
            newList = updateListOfLeakages( mat,z+1, X, Y,leaklist);
            value += leaklist.size()/2;
            //System.out.println("For Loop " + (z+1) + " Size is " + value);
            leaklist = newList;
            z++;
        }
        //value += leaklist.size();
        System.out.println(value);
    }

    public static List<Integer> updateListOfLeakages(int[][] mat, int k, int X, int Y,List<Integer> leaklist) {

        List<Integer> newleakList = new LinkedList<>();
        int x, y;
        int count = 0;
        //System.out.println(" s1 : For Loop " + (k+1) + " Count is " + count + " size is " + leaklist.size()) ;
        for (int i = 0; i < leaklist.size() / 2; i++) {
            x = leaklist.get(2 * i);
            y = leaklist.get(2 * i + 1);
            if (mat[x][y] != k) {
            } else {
                if ((x + 1) < X) {
                    if (mat[x + 1][y] == 0) {
                        mat[x + 1][y] = k + 1;
                        newleakList.add(2 * count, x + 1);
                        newleakList.add(2 * count + 1, y);
                        count++;
                    }
                }
                if ((x - 1) >= 0) {
                    if (mat[x - 1][y] == 0) {
                        mat[x - 1][y] = k+1;
                        newleakList.add(2 * count, x - 1);
                        newleakList.add(2 * count + 1, y);
                        count++;
                    }
                }
                if ((y + 1) < Y) {
                    if (mat[x][y + 1] == 0) {
                        mat[x][y + 1] = k+1;
                        newleakList.add(2 * count, x);
                        newleakList.add(2 * count + 1, y + 1);
                        count++;
                    }
                }
                if ((y - 1) >= 0) {
                    //System.out.println("hdsa :" + x + " y : " + y);
                    if (mat[x][y - 1] == 0) {
                        mat[x][y - 1] = k+1;
                        newleakList.add(2 * count, x);
                        newleakList.add(2 * count + 1, y - 1);
                        count++;
                    }
                }
            }

        }
        //System.out.println("s1 : For Loop " + (k+1) + " Count is " + count + " size is " + newleakList.size()) ;
        return newleakList;
    }


    private static void updateValue(int[][] mat, int i, int x, int y, int X, int Y) {

        if ( (x+ 1) < X) {
            if  (mat[x+1][y] == 0) mat[x+1][y] = i;
        }
        if ( (x -1) >= 0) {
            if  (mat[x-1][y] == 0) mat[x-1][y] = i;
        }
        if ( (y+ 1) < Y) {
            if  (mat[x][y+1] == 0) mat[x][y+1] = i;
        }
        if ( (y-1) >= 0) {
            //System.out.println("hdsa :" + x + " y : " + y);
            if  (mat[x][y-1] == 0) mat[x][y-1] = i;
        }
    }


    public static void updatewalls(int[] walls, int[][] mat, int X, int Y) {

        if ( walls.length == 0)
            return ;
        for (int i=0; i < (walls.length/4) ; i++) {
            updateWalls1(walls[4*i],walls[4*i+ 1],walls[4*i+ 2],walls[4*i+ 3],mat);
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (true) {

            int X = in.nextInt();
            if ( X == -1) System.exit(0);
            int Y = in.nextInt();
            int T = in.nextInt();
            int L = in.nextInt();
            int W = in.nextInt();

            int [] LeaksX = new int[L];
            int [] LeaksY = new int[L];
            int [] walls = new int[4*W];
            List<Integer> leaklist = new LinkedList<>();

            // Create a matrix of length and breadth given
            int[][] plane = new int[X][Y];
            for (int i=0; i < X ; i++) {
                for (int j=0; j < Y; j++) {
                    plane[i][j] = 0;
                }
            }

            for (int i=0; i < L ; i ++) {
                int a = in.nextInt()-1;
                int b = in.nextInt()-1;
                LeaksX[i] = a;
                LeaksY[i] = b;
                leaklist.add(2*i,a);
                leaklist.add(2*i+1,b);
                plane[a][b] = 1;
            }

            // Mark the walls as with -1

            for (int i=0; i < W ; i++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                //System.out.println(" X1: " + x1 + " y1: " + y1 +" X2: " + x2 + " y2: " + y2 );
                updateWalls1(x1 - 1, y1 - 1, x2 - 1, y2 - 1, plane);
            }

            //updateIterations(plane, T, X, Y);

            updateIterations1(plane, T, X, Y, leaklist);
            //findWetTiles(plane, X, Y);
            //printTitles(plane, X, Y);

        }
    }

    private static void findWetTiles(int[][] plane, int X, int Y) {
        int count = 0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (plane[i][j] > 0)  count++;
            }
        }
        System.out.println(count);
    }

    private static void printTitles(int[][] plane, int X, int Y) {
        int count = 0;

        for (int j = Y-1; j >=0 ; j--) {
            for (int i = 0; i < X; i++) {
                System.out.print(plane[i][j] + " , ");
            }
            System.out.println();
        }
        System.out.println(count);
    }
}
