package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Budget_2512 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] budget;
    private static int M;
    
    public static int calc(int mid) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if(budget[i] < mid) sum += budget[i];
            else sum += mid;
        }
        return sum;
    }
    
    public static int solve() {
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            if(right == 0 || right < budget[i]) right = budget[i];
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = calc(mid);
            if(sum <= M) {
                left = mid + 1;
            }
            else right = mid - 1;
        }
        
        return right;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        budget = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
}
