package com.company.ICPC;

import com.sun.xml.internal.stream.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ingared on 10/20/15.
 */
public class Encrypt {

    public  static  void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (true) {

            int n = in.nextInt();

            if ( n== 0) System.exit(0);

            String s = in.next();
            String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_.";
            Map<Integer, Character> map1 = new HashMap<>();
            Map<Character,Integer> map2 = new HashMap<>();

            for (int i=0; i < a.length(); i++) {
                map1.put(i,a.charAt(i));
                map2.put(a.charAt(i),i);
            }

            char[] res = new char[s.length()];

            for (int i = s.length()-1; i >= 0; i--) {
                char d2 = s.charAt(i);
                res[s.length()-1-i] = map1.get((map2.get(d2) + n)%28);
            }

            System.out.println(res);
        }
    }
}
