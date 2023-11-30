package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// F
public class 부분수열의합2_1208 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int S;
    private static int[] nums;
    private static Map<Integer, Integer> sumMap;

    private static long solve() {
        long res = 0;
        int mid = N / 2;

        for (int i = 0; i < (1 << mid); i++) {
            int sum = 0;
            for (int j = 0; j < mid; j++) {
                if((i & (1 << j)) != 0) {
                    sum += nums[j];
                }
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }

        for (int i = 0; i < (1 << (N - mid)); i++) {
            int sum = 0;
            for (int j = 0; j < N - mid; j++) {
                if((i & (1 << j)) != 0) {
                    sum += nums[j + mid];
                }
            }
            res += sumMap.getOrDefault(S - sum, 0);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        sumMap = new HashMap<>();
        long res = solve() + ((S == 0) ? -1 : 0);
        System.out.println(res);
    }
}
