package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class NM5_15654 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] nums;
    private static List<List<Integer>> result;

    public static void solve(int index, List<Integer> picked, boolean[] visited) {
        if(index == M) {
            result.add(picked.stream().collect(Collectors.toList()));
            return;
        }
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked.add(nums[i]);
                solve(index + 1, picked, visited);
                picked.remove(picked.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        result = new ArrayList<>();
        solve(0, new ArrayList<>(), new boolean[N]);

        for(List<Integer> p : result) {
            p.stream().forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }
}
