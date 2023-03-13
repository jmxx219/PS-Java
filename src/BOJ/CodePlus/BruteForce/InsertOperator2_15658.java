package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InsertOperator2_15658 {
    public static class Pair {
        public int min, max;
        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;

    static Pair calc(int index, int cur, int plus, int minus, int mul, int div) {
        if (index == nums.length) {
            return new Pair(cur, cur);
        }
        ArrayList<Pair> res = new ArrayList<>();
        if (plus > 0) {
            res.add(calc(index + 1,cur + nums[index],plus - 1,minus,mul,div));
        }
        if (minus > 0) {
            res.add(calc(index + 1,cur - nums[index],plus,minus - 1,mul,div));
        }
        if (mul > 0) {
            res.add(calc(index + 1,cur * nums[index],plus,minus,mul - 1,div));
        }
        if (div > 0) {
            res.add(calc(index + 1,cur / nums[index],plus,minus,mul,div - 1));
        }

        Pair ans = res.get(0);
        for (Pair p : res) {
            if (ans.max < p.max) {
                ans.max = p.max;
            }
            if (ans.min > p.min) {
                ans.min = p.min;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int mul = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        Pair ans = calc(1, nums[0], plus, minus, mul, div);

        System.out.println(ans.max);
        System.out.println(ans.min);
    }
}
