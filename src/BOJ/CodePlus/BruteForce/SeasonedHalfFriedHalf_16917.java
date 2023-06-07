package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SeasonedHalfFriedHalf_16917 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int A;
    private static int B;
    private static int C;
    private static int X;
    private static int Y;

    private static int solve() {
        int res = 0;

        if(A + B >= 2 * C){
            int cnt = Math.min(X, Y);
            res = cnt * 2 * C;
            res += Math.min((X - cnt) * A + (Y - cnt) * B,
                    C * 2 * (X - cnt) + C * 2 * (Y - cnt));
        }
        else{
            res += A * X;
            res += B * Y;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
}
