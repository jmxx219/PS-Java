package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 삼분 탐색: 최소값 또는 최대값이 하나인 함수에서 최소/최대값을 찾는 방법
// 넘 어렵 ,,
public class LineAndPoint_11664 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static Point A;
    private static Point B;
    private static Point C;

    public static class Point {
        double x, y, z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static double dist(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x)
                + (p2.y - p1.y) * (p2.y - p1.y)
                + (p2.z - p1.z) * (p2.z - p1.z));
    }

    public static double solve() {
        double dx = B.x - A.x; // 비율 이용
        double dy = B.y - A.y;
        double dz = B.z - A.z;

        double left = 0.0; // A
        double right = 1.0; // B
        double m = 0;

        while (true) {
            if (Math.abs(right - left) < 1e-9) {
                m = (left + right) / 2;
                break;
            }

            double m1 = left + (right - left) / 3;
            double m2 = right - (right - left) / 3;

            Point p1 = new Point(A.x + m1 * dx, A.y + m1 * dy, A.z + m1 * dz);
            Point p2 = new Point(A.x + m2 * dx, A.y + m2 * dy, A.z + m2 * dz);

            double d1 = dist(p1, C);
            double d2 = dist(p2, C);

            if(d1 > d2) left = m1;
            else right = m2;
        }

        Point P = new Point(A.x + m * dx, A.y + m * dy, A.z + m * dz);
        return dist(P, C);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        B = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        C = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));

        System.out.printf("%.10f\n", solve());
    }
}
