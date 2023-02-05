package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KaingCalendar_6064 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int M;
    private static int N;
    private static int X;
    private static int Y;

    public static int solve() {
        X -= 1;
        Y -= 1;

        for(int year = X; year < (N * M); year += M) {
            if(year % N == Y) {
                return year + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            System.out.println(solve());
        }

    }
}
