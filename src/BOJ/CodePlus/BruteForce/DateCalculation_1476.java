package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 날짜 계산
public class DateCalculation_1476 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int E;
    private static int S;
    private static int M;

    public static int calculateDate() {
        int y = 1;
        while(true) {
            if((y % 15 == E) && (y % 28 == S) && (y % 19 == M)){
                break;
            }
            y += 1;
        }
        return y;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        E = Integer.parseInt(st.nextToken()) - 1;
        S = Integer.parseInt(st.nextToken()) - 1;
        M = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(calculateDate());
    }
}
