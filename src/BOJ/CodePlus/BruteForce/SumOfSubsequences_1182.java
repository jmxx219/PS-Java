package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfSubsequences_1182 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int S;
    private static int[] nums;

    private static int solve() { // bitMask
        int res = 0;

        // 공집합을 제와한 전체집합(1<<N-1)까지 모든 부분 수열
        for(int i = 1; i < (1<<N); i++) {
            int sum = 0;
            for(int k = 0; k < N; k++) { // 해당 집합에 어떤 수가 포함되어 있는지
                if((i & (1<<k)) != 0){
                    sum += nums[k];
                }
            }
            if(sum == S) {
                res += 1;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }

}
