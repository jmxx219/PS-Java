package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class OneTwoThreeSum2_12101 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int[] nums = {1, 2, 3};

    private static String solve2(int N, int K) {
        List<List<String>> res = new ArrayList<>();

        res.add(new ArrayList<>());
        res.get(0).add("");

        for (int i = 1; i <= N; i++) {
            res.add(new ArrayList<>());
            for(int num : nums) {
                if(i - num >= 0) {
                    for(String op : res.get(i - num)) {
                        if(op.equals("")) res.get(i).add(num + "");
                        else res.get(i).add(num + "+" + op);
                    }
                }
            }
        }

        if(res.get(N).size() < K) return "-1";
        return res.get(N).get(K - 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        // 2
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(solve2(N, K));
    }
}
