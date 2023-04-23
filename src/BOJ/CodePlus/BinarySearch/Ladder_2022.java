package BOJ.CodePlus.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ladder_2022 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static double x;
    private static double y;
    private static double c;

    public static double solve() {
        double left = 0;
        double right = Math.min(x, y);

//        while (Math.abs(right-left) > 1e-6) {
        for (int k = 0; k < 10000; k++) {
            double mid = (left + right) / 2.0;
            double d = mid;

            double h1 = Math.sqrt(x * x - d * d);
            double h2 = Math.sqrt(y * y - d * d);
            double h = (h1*h2)/(h1+h2);

            if (h > c) left = mid;
            else right = mid;
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        System.out.println(String.format("%.3f", solve()));
    }
}
