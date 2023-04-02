package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quadrant_1891 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int D;
    private static String quadrant;
    private static double hereY;
    private static double hereX;

    public static boolean isRange(double ny, double nx, double nSize) {
        return ny <= hereY && hereY< ny + nSize && nx <= hereX && hereX< nx + nSize;
    }

    public static String findQuadrant(double y, double x, double size, String nextQ) {
        if(size == 1) {
            return nextQ;
        }

        double nSize = size / 2;
        if(isRange(y, x + nSize, nSize)) return findQuadrant(y, x + nSize, nSize, nextQ + "1");
        if(isRange(y, x, nSize)) return findQuadrant(y, x, nSize, nextQ + "2");
        if(isRange(y + nSize, x, nSize)) return findQuadrant(y + nSize, x, nSize, nextQ + "3");
        if(isRange(y + nSize, x + nSize, nSize)) return findQuadrant(y + nSize, x + nSize, nSize, nextQ + "4");

        return "-1";
    }

    public static void findPoint(double y, double x, double size, int index) {
        if(index == quadrant.length()) {
            hereY = y;
            hereX = x;
            return;
        }

        double nSize = size / 2;
        if(quadrant.charAt(index) == '1') findPoint(y, x + nSize, nSize, index + 1);
        if(quadrant.charAt(index) == '2') findPoint(y, x, nSize, index + 1);
        if(quadrant.charAt(index) == '3') findPoint(y + nSize, x, nSize, index + 1);
        if(quadrant.charAt(index) == '4') findPoint(y + nSize, x + nSize, nSize, index + 1);
    }

    public static String solve(double dy, double dx) {
        double N = (double) Math.pow(2, D);
        findPoint(0, 0, N, 0);

        hereY -= dy;
        hereX += dx;
        if(0 <= hereY && hereY < N && 0 <= hereX && hereX < N) {
            String s = "";
            return findQuadrant(0, 0, N, s);
        }

        return "-1";
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        quadrant = st.nextToken();

        st = new StringTokenizer(br.readLine());
        double dx = Double.parseDouble(st.nextToken());
        double dy = Double.parseDouble(st.nextToken());

        System.out.println(solve(dy, dx));
    }
}
