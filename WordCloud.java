package com.company.ICPC;

import org.omg.CORBA.MARSHAL;

import java.util.*;

/**
 * Created by ingared on 10/21/15.
 */
public class WordCloud {


    public static int getHeight(int cw, int cmax) {
        return 8 + (int)Math.ceil(40.0*(cw - 4.0)/(cmax - 4.0));
    }

    public static int getWidth(int t, int p){

        return (int)Math.ceil(p*t*9.0/16.0);
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int counter = 0;
        while (true) {

            counter++;
            int w = in .nextInt();
            int n = in.nextInt();

            if (w ==0 && n ==0) System.exit(0);

            Map<String,Integer> map1 = new HashMap<>();
            Map<String,Integer> map2 = new HashMap<>();
            Map<String ,Integer> map3 = new HashMap<>();

            List<String> words = new LinkedList<>();
            int k;
            String s;
            int cmax = 0;

            for (int i=0; i < n; i++) {
                s = in.next();
                k = in.nextInt();
                words.add(i, s);
                map1.put(s, k);
                cmax = Math.max(k,cmax);
            }

            // Start updating the data
            int widthused = 0;
            int max_height = 0;
            int total_height = 0;

            for (int i=0; i<n;i++) {
                s = words.get(i);

                int height = getHeight(map1.get(s),cmax) ;
                int width = getWidth(s.length(), height);
                map2.put(s,height);
                map3.put(s, width);

                if ( (widthused + width)  <= w) {
                    max_height = Math.max(height,max_height);
                    widthused += (width+10);
                    //System.out.println("Normal " + total_height + " , " + max_height + " , " + widthused);
                } else {
                    total_height += max_height;
                    max_height = height;
                    widthused = (width+10);
                    //System.out.println("Line " + total_height  + " , " + max_height + " , " + widthused);
                }
            }
            if ( widthused != 0) total_height += max_height;

            //System.out.println(map1.toString());
            //System.out.println(map2.toString());
            //System.out.println(map3.toString());
            System.out.println("ClOUD "+counter+ ": " + total_height );
        }

    }
}
