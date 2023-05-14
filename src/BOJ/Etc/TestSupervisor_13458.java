package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestSupervisor_13458 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] testerNum;
    private static int B; // 총감독관
    private static int C; // 부감독관

    public static long solve() {
        long cnt = 0; // 자료형 좀 더 생각해보자~~~~~~
        for(int tester : testerNum) {
            tester -= B;
            cnt += 1;

            if(tester > 0) {
                cnt += tester / C + (tester % C == 0 ? 0 : 1);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        testerNum = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            testerNum[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }

}
