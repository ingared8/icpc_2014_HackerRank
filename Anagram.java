package com.company.ICPC;

import com.company.LinkedList;

import java.util.*;

/**
 * Created by ingared on 10/21/15.
 */
public class Anagram {

    public static  int[] sortArray(int[] a) {

        int[] res = new int[a.length];

        for (int i=0; i < a.length; i++) res[i] = a[i];

        for (int i=0;i < res.length;i++) {
            int min = res[i];
            int index = i;
            for (int j = i+1; j < res.length; j++) {
                if ( min > res[j]) {
                    min = res[j];
                    index = j;
                }
            }
            int temp = res[i];
            res[i] = res[index];
            res[index] = temp;
        }
        return res;
    }

    public static int noOfElementsLess(int[] array, int element) {

        int count =0;
        int j=0;
        while ( (j < array.length) && (array[j] <= element) ) {
            count++;
            j++;
        }
        return count;
    }

    public static long[] repFact(int[] array) {

        long val = 1;
        long[] res = new long[array.length];
        res[0] = 1;
        int count = 1;
        for (int i=1; i < array.length ; i++) {
            if (array[i] == array[i-1]) {
                count++;
            } else {
                count = 1;
            }
            val *= count;
            res[i] = val;
        }

        return res;
    }

    // TODO Mergesort
    public static void mergesortArray(int[] a, int a1 , int b1) {


        int[] res = new int[a.length];

        for (int i=0; i < a.length; i++) res[i] = a[i];

        for (int i=0;i < res.length;i++) {
            int min = a[i];
            int index = i;
            for (int j = i+1; j < res.length; j++) {
                if ( min > res[j]) {
                    min = res[j];
                    index = j;
                }
            }
            int temp = res[i];
            res[i] = res[index];
            res[index] = temp;
        }

    }

    public static int[] getLessThanArray(int[] sa){

        int[] la = new int[sa.length];
        for (int i=0; i < sa.length; i++) {
            la[i] = noOfElementsLess(sa,sa[i]);
        }
        return la;
    }

    public static void main(String[] args) {

        String s;
        String lim = "# 0";
        long k;
        char d;

        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<Integer, Character> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for (int i=0; i < a.length(); i++) {
            map1.put(i,a.charAt(i));
            map2.put(a.charAt(i),i);
        }

        Scanner in = new Scanner(System.in);
        while (true) {
            s = in.next();
            if (s.equals(lim)) System.exit(0);
            if (in.hasNextInt()) k = (long)in.nextInt();
            else k = in.nextLong();
            int len = s.length();
            int[] ar = new int[len];
            for (int i=0;i < len; i++) {
                d = s.charAt(i);
                ar[i] = map2.get(d);
            }

            int[] sa = sortArray(ar);
            printArray(ar);
            printArray(sa);
            int[] la = new int[ar.length];
            for (int i=0; i < ar.length; i++) {
                la[i] = noOfElementsLess(sa,sa[i]);
            }

            printArray(la);
            //Find the string for a given rank
            long[] fa = repFact(sa);

            for (int i=0; i < ar.length; i++) {
                System.out.print(fa[i] + " , ");
            }
            System.out.println();

            // Using Hashmap will be helpful
            String res = getRankAdjusted(sa,k);
            System.out.println(res);

        }
    }

    public static  String getRankAdjusted(int[] array,  long rank) {

        long x = 1;
        int i = 0;
        int len = array.length;
        int[] cumArray = getLessThanArray(array);
        while ( x < rank ) {
            x = (cumArray[i]-1)* (fact(len-1))/(getRepeat(array[i],array));
            System.out.println("X is " + x + " Character is " + array[i]);
            i++;
        }
        System.out.println(" X is " + x + " index is " + i + " length is " + len);
        String res ="";
        int[] newArray = new int[array.length-1];
        int[] newCumArray = new int[array.length-1];

        for (int j=0; j < newArray.length ; j++) {
            if ( j == i) {
              i++;
            }
            newArray[j] = array[i];
        }
        return res + array[i] + getRankAdjusted(newArray,rank-x);
    }

    private static long getRepeat(int k, int[] array) {

        long val = 1;
        int count = 1;
        for (int i=1; i < array.length ; i++) {
            if ( i == k) {
                count = 1;
            } else if (array[i] == array[i-1]) {
                count++;
            } else {
                count = 1;
            }
            val *= count;
        }
        return val;
    }


    public static void printArray(int[] a){

        for (int i=0; i< a.length; i++) {
            System.out.print(a[i]+ " , ");
        }
        System.out.println();
    }

    public static int fact(int n)
    {
        return (n <= 1)? 1 :n * fact(n-1);
    }

    public static int findSmallerInRight(String str, int low, int high)
    {
        int countRight = 0;

        for (int i = low+1; i <= high; ++i)
            if (str.charAt(i) < str.charAt(low))
                countRight++;

        return countRight;
    }

    public static int findRank (String  str)
    {
        int len = str.length();
        int mul = fact(len);
        int rank = 1;
        int countRight;

        int i;
        for (i = 0; i < len; ++i)
        {
            mul /= len - i;

            // count number of chars smaller than str[i]
            // fron str[i+1] to str[len-1]
            countRight = findSmallerInRight(str, i, len-1);
            rank += countRight * mul ;
        }
        return rank;
    }

    public static int rank(String str) {

        Comparator<Character> characterComparator = new Comparator<Character>() {

            @Override
            public int compare(Character c, Character t1) {
                return (c - t1);
            }
        };

        PriorityQueue<Character> pq = new PriorityQueue<>(str.length(),characterComparator);

        for (int i=0; i < str.length(); i++) {
            pq.add(str.charAt(i));
        }

        String s2 = "";
        for (int i=0; i < str.length(); i++){
            s2 +=  Character.toString(pq.remove());
        }


        System.out.println(s2);
        return 0;

    }

}
