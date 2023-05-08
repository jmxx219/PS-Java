package Etc;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public class Point implements Comparable<Point> {
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point p) {
            return this.y - p.y;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{2, 3}, {1, 2}, {2, 2}};
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        Arrays.stream(arr).forEach(s -> System.out.println(s[0] + " " + s[1]));
    }
}
