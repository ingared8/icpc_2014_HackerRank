package com.company.ICPC;

import java.util.Scanner;

/**
 * Created by ingared on 10/22/15.
 */
public class FunHouse {

    public static void printTitles(char[][] plane, int X, int Y) {
        int count = 0;

        for (int j = Y-1; j >=0 ; j--) {
            for (int i = 0; i < X; i++) {
                System.out.print(plane[i][j]);
            }
            System.out.println();
        }
    }


    public static void forwardPass(int ix,int iy,char[][] plane,int X,int Y) {
        //System.out.println("FP " + ix + " , " + " , " + iy);
        char bc = '\\';
        char fw = '/';
        char dot = '.';
        char x = 'x';

        int ixnew = ix+1;
        while (plane[ixnew][iy] == dot) {
            ixnew++;
        }

        if (plane[ixnew][iy] == x) {
            plane[ixnew][iy] = '&';
        } else if (plane[ixnew][iy] == fw) {
            upPass(ixnew, iy, plane, X, Y);
        } else if (plane[ixnew][iy] == bc) {
            downPass(ixnew, iy, plane, X, Y);
        } else {
            System.out.println("FP  " + ixnew + " , " + " , " + iy);
            System.out.println(" Unknown character " + plane[ixnew][iy]);
        }
    }

    public static void backwardPass(int ix,int iy,char[][] plane,int X,int Y) {
        //System.out.println("BP " + ix + " , " + " , " + iy );
        char bc = '\\';
        char fw = '/';
        char dot = '.';
        char x = 'x';
        int ixnew = ix-1;
        while (plane[ixnew][iy] == dot) {
            ixnew--;
        }
        if (plane[ixnew][iy] == x) {
            plane[ixnew][iy] = '&';
        } else if (plane[ixnew][iy] == fw) {
            downPass(ixnew, iy, plane, X, Y);
        } else if (plane[ixnew][iy] == bc) {
            upPass(ixnew, iy, plane, X, Y);
        } else {
            System.out.println(" Unknown character");
        }
    }

    public static void upPass(int ix,int iy,char[][] plane,int X,int Y) {
        //System.out.println("uP " + ix + " , " + " , " + iy );
        char bc = '\\';
        char fw = '/';
        char dot = '.';
        char x = 'x';
        int iynew = iy+1;
        while (plane[ix][iynew] == dot) {
            iynew++;
        }

        if (plane[ix][iynew] == x) {
            plane[ix][iynew] = '&';
        } else if (plane[ix][iynew] == fw) {
            forwardPass(ix, iynew, plane, X, Y);
        } else if (plane[ix][iynew] == bc) {
            backwardPass(ix, iynew, plane, X, Y);
        } else {
            System.out.println(" Unknown character");
        }
    }

    public static void downPass(int ix,int iy,char[][] plane,int X,int Y) {
        //System.out.println("DP " + ix + " , " + " , " + iy );
        char bc = '\\';
        char fw = '/';
        char dot = '.';
        char x = 'x';
        int iynew = iy-1;
        while (plane[ix][iynew] == dot) {
            iynew--;
        }

        if (plane[ix][iynew] == x) {
            plane[ix][iynew] = '&';
        } else if (plane[ix][iynew] == fw) {
            backwardPass(ix, iynew, plane, X, Y);
        } else if (plane[ix][iynew] == bc) {
            forwardPass(ix, iynew, plane, X, Y);
        } else {
            System.out.println(" Unknown character");
        }
    }

    public  static void startJourneyOflight(int ix,int iy,char[][] plane,int X,int Y){

        if (ix ==0) forwardPass(ix,iy,plane,X,Y);
        else if (ix == X-1) backwardPass(ix, iy, plane, X, Y);
        else if (iy == 0) upPass(ix,iy,plane,X,Y);
        else if (iy == Y-1) downPass(ix,iy,plane,X,Y);
        else  System.out.println(" Unknown Start " + ix + " , " + iy + "," + plane[ix][iy]);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int count =0;
        while (true) {

            count++;
            int X = in.nextInt();
            int Y = in.nextInt();
            if ( X == 0 && Y ==0 ) System.exit(0);

            // Create a matrix of length and breadth given
            int ix=0,iy=0;
            char dd = '*';
            char fw = '/';
            char bc = '\\';
            char[][] plane = new char[X][Y];
            char cd;
            for (int j= Y-1; j >= 0; j--) {
                String s = in.next();
                for (int i = 0; i < X; i++) {
                    cd = s.charAt(i);
                    plane[i][j] = s.charAt(i);
                    if (cd == dd) {
                        ix = i;
                        iy = j;
                        //System.out.println("Values are " + ix + " , " + iy);
                    }
                }
            }
            //System.out.println("Ix Iy " + ix + " , " + iy);
            //printTitles(plane, X, Y);
            System.out.println("HOUSE " + count);
            startJourneyOflight(ix, iy, plane, X, Y);
            printTitles(plane,X,Y);
        }
    }
}
