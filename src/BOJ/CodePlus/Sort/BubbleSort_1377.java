package BOJ.CodePlus.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BubbleSort_1377 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Pair[] nums;

    public static class Pair implements Comparable<Pair> {
        public int num;
        public int position;

        public Pair(int num, int position) {
            this.num = num;
            this.position = position;
        }

        public int compareTo(Pair o) {
            return this.num - o.num;
        }
    }

    public static int solve() {
        Arrays.sort(nums);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if(nums[i].position - i > ans) ans = nums[i].position - i;
        }
        return ans + 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = new Pair(Integer.parseInt(st.nextToken()), i);
        }

        System.out.println(solve());
    }
}
