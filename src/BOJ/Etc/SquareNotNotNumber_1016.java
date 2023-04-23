package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SquareNotNotNumber_1016 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static long min;
    private static long max;
    private static boolean[] check; // 제곱 ㄴㄴ 수가 아니면 T

    public static int solve() {
        int cnt = (int)(max - min + 1);
        for (long i = 2; i <= Math.sqrt(max); i++) {
            long squareNum = i * i;
            long start = min / squareNum + (min % squareNum == 0 ? 0 : 1);
            for (long j = start; squareNum * j <= max; j++) {
                if(!check[(int) (squareNum * j - min)]) {
                    check[(int) (squareNum * j - min)] = true;
                    cnt -= 1;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());

        check = new boolean[(int) (max - min + 1)];
        System.out.println(solve());
    }
}
