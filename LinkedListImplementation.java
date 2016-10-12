package com.company.ICPC;

import com.company.LinkedList;

import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by ingared on 10/26/15.
 */
public class LinkedListImplementation {

    public static void main(String[] args) {

        List<Integer> list = new java.util.LinkedList<>();

        for (int i=0; i < 20 ; i ++) {
            list.add(i, i + 1);
        }

        printList(list);

        for (int i=0; i < 10; i ++) {
            list.remove(2*i-1);
        }

        printList(list);
    }

    private static void printList(List<Integer> list) {

        for (int i=0; i < list.size(); i++) {
            System.out.print(list.get(i) + " , ");
        }
        System.out.println();
    }
}
