package com.company.ICPC;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by ingared on 10/21/15.
 */
public class Maze {

    public static int getIndex(int i, int j, int m) {
        return (i*(m) + j);
    }

    public static List<Integer> getIandJ(int value, int n, int m) {
        List<Integer> list = new LinkedList<>();
        int j = value%m;
        int i = (value/m);
        list.add(0, i);
        list.add(1, j);
        return list;
    }

    public static void createHashMap(Map<String,String> map ) {

        map.put("0", "0000");
        map.put("1", "0001");
        map.put("2", "0010");
        map.put("3", "0011");
        map.put("4", "0100");
        map.put("5", "0101");
        map.put("6", "0110");
        map.put("7", "0111");
        map.put("8", "1000");
        map.put("9", "1001");
        map.put("A", "1010");
        map.put("B", "1011");
        map.put("C", "1100");
        map.put("D", "1101");
        map.put("E", "1110");
        map.put("F", "1111");
    }

    public static void printmat(int[][] mat, int i, int j) {

        for (int i1=0; i1 < i; i1++) {
            for( int j1 =0; j1 < j; j1++) {
                System.out.print(mat[i1][j1] + ", ");
            }
            System.out.println();
        }
    }

    public static  void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (true) {

            int n = in.nextInt();
            int m = in.nextInt();

            //System.out.println("n is  " + n + " m is " + m);
            if (n == 0 && m == 0) {
                System.exit(0);
            }

            int nodeSize = n*m;
            int[][] weights = new int[nodeSize][nodeSize];
            HashMap<String,String> hexmap = new HashMap<>();
            createHashMap(hexmap);

            // Initialize weights
            initializeWeights(weights, nodeSize);

            char[][] mat = new char[n][m];
            String s;
            for (int i = 0; i < n; i++) {
                s = in.next();
                for (int j = 0; j < m; j++) {
                    mat[i][j] = s.charAt(j);
                    int ch = (int) mat[i][j];
                    //System.out.print(mat[i][j] + " , ");
                    String s3 = Character.toString(mat[i][j]);
                    updateWeights(hexmap.get(s3), weights, i, j, n, m);
                }
            }

            // Find the source and destination
            List<Integer> sourceandDest = findSourceAndDestination(mat,n,m, hexmap);

            //printmat(weights,nodeSize,nodeSize);

            // check wheher there are more than two entry points
            if ( sourceandDest.size() > 2 ) {
                System.out.println("MULTIPLE ENTRY POINTS");
            }

            int source = sourceandDest.get(0);
            int destination = sourceandDest.get(1);

            //System.out.println("Source is " + sourceandDest.get(0));
            //System.out.println("Destination is " + sourceandDest.get(1));

            // The weights are stored in weights graph, 1 refers to edge present , -1 refers to no edge

            // Part 1 -- Path from Source to Destination
            if ( !pathfromSourceToDestination(weights, nodeSize, source, destination)){
                System.out.println("NO SOLUTION");
            }

            // Part 2 -- Unreachable Cell / Using DFS or BFS

            else if ( !dfs(weights, nodeSize)) {
                System.out.println("UNREACHABLE CELL");
            }

            // Part 3 -- Multiple paths

            else if ( multiplePaths(weights,nodeSize, source, destination)) {
                System.out.println("MULTIPLE PATHS");
            }

            // Part 4 -- MAZE OK
            else {
                System.out.println("MAZE OK");
            }
        }
    }

    public static boolean dfs(int[][] weights, int v ) {

        int s = 1;
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[v];

        for (int i=0; i < v; i++) {
            visited[i] = 0;
        }

        stack.push(s);
        visited[s] = 1;

        while ( stack.size() != 0) {

            int s1 = stack.pop();

            for (int i=0; i < v; i++) {
                if (weights[s1][i] == 1) {
                    if (visited[i] == 0) {
                        visited[i] = 1;
                        stack.push(i);
                    }
                }
            }
        }

        for (int i=0; i < v; i++) {
            if (visited[i] != 1) return false;
        }

        return true;
    }

    public static boolean pathfromSourceToDestination(int[][] weights, int v, int s, int d) {

        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[v];

        for (int i=0; i < v; i++) {
            visited[i] = 0;
        }

        stack.push(s);
        visited[s] = 1;

        if ( s == d) {
            //System.out.print("Source and dest is " + s);
            return true;
        } else if (weights[s][d] == 1 ) {
            //System.out.print("Source and dest is " + s + ", "+  d);
            return true;
        } else {

            while ( stack.size() != 0 ) {
                int s1 = stack.pop();
                for (int i=0; i < v ; i++) {
                    if (weights[s1][i] == 1) {
                        if ( i == d) return true;
                        else if (visited[i] == 0)  {
                            stack.push(i);
                            visited[i] = 1;
                        }
                    }
                }
            }

        }
        return false;
    }

    public static boolean multiplePaths(int[][] weights1, int v, int s, int d) {

        int[][] weights = new int[v][v];

        for (int i=0; i < v; i++) {
            for (int j=0; j < v; j++) {
                weights[i][j] = weights1[i][j];
            }
        }

        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[v];

        for (int i=0; i < v; i++) {
            visited[i] = 0;
        }

        stack.push(s);
        visited[s] = 1;
        String path = "s";
        int count = 0;

        if ( s == d) {
            //System.out.print("Source and dest is " + s);
            return true;
        } else if (weights[s][d] == 1 ) {
            //System.out.print("Source and dest is " + s + ", "+  d);
            return true;
        } else {

            while ( stack.size() != 0 ) {
                int s1 = stack.pop();
                for (int i=0; i < v ; i++) {
                    if (weights[s1][i] == 1) {
                        if ( i == d) {
                            count++;
                        }
                        else if (visited[i] == 0)  {
                            weights[s1][i] = -1;
                            weights[i][s1] = -1;
                            path += "->" + s1;
                            stack.push(i);
                            visited[i] = 1;
                        }
                    }
                }
            }
        }
        return (count > 1);
    }

    private static List<Integer> findSourceAndDestination(char[][] mat, int n, int m, Map<String ,String> map) {

        int count = 0;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < m ; i++ ) {
            if (map.get(Character.toString(mat[0][i])).charAt(0) == '0') {
                if (!list.contains(getIndex(0,i,m))) {
                    list.add(count, getIndex(0, i, m));
                    count++;
                }
            }
        }
        for (int i = 0; i < n ; i++ ) {
            if (map.get(Character.toString(mat[i][m-1])).charAt(1) == '0') {
                if (!list.contains(getIndex(i,m-1,m))) {
                    list.add(count, getIndex(i, m - 1, m));
                    count++;
                }
            }
        }
        for (int i = 0; i < m ; i++ ) {
            if (map.get(Character.toString(mat[n-1][i])).charAt(2) == '0') {
                if (!list.contains(getIndex(n-1,i,m))) {
                    list.add(count, getIndex(n - 1, i, m));
                    count++;
                }
            }
        }
        for (int i = 0; i < n ; i++ ) {
            if ( map.get(Character.toString(mat[i][0])).charAt(3) == '0' ) {
                if (!list.contains(getIndex(i,0,m))) {
                    list.add(count, getIndex(i, 0, m));
                    count++;
                }
            }
        }
        return list;
    }

    private static void updateWeights(String s2, int[][] weights, int i, int j, int n, int m) {

        if ( s2.charAt(0) == '0') updateGraphUpEdges(weights,i,j, n,m);
        if ( s2.charAt(1) == '0') updateGraphForwardEdges(weights, i, j, n, m);
        if ( s2.charAt(2) == '0') updateGraphDownEdges(weights, i, j, n, m);
        if ( s2.charAt(3) == '0') updateGraphBackwardEdges(weights,i,j, n,m);
    }

    private static void initializeWeights(int[][] weights, int nodeSize) {

        for (int i=0; i < nodeSize; i++) {
            for (int j = 0; j < nodeSize; j++) {
                weights[i][j] = -1;
            }
        }

        for (int i=0; i < nodeSize; i++) {
            weights[i][i] = 0;
        }
    }

    private static void updateGraphForwardEdges(int[][] weights, int i, int j, int n,int m) {

        int node1 = getIndex(i,j,m);
        int node2 = getIndex(i,j+1, m);
        if ((j + 1) < m) weights[node1][node2] = 1;
    }

    private static void updateGraphBackwardEdges(int[][] weights, int i, int j, int n, int m) {

        int node1 = getIndex(i,j,m);
        int node2 = getIndex(i,j-1, m);
        if ((j-1) >= 0) weights[node1][node2] = 1;
    }

    private static void updateGraphUpEdges(int[][] weights, int i, int j, int n, int m) {

        int node1 = getIndex(i,j,m);
        int node2 = getIndex(i-1,j, m);
        if ((i-1) >= 0) weights[node1][node2] = 1;
    }

    private static void updateGraphDownEdges(int[][] weights, int i, int j, int n, int m) {

        int node1 = getIndex(i,j,m);
        int node2 = getIndex(i+1,j, m);
        if ((i+1) < n) weights[node1][node2] = 1;
    }

}
