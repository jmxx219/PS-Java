package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TalkMiddle_1655 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;
    private static PriorityQueue<Integer> left;
    private static PriorityQueue<Integer> right;
    private static int mid;

    public static void addNum(int n) {
        if(n >= mid) right.add(n);
        else left.add(n);

        if(right.size() - left.size() >= 2) {
            left.add(mid);
            mid = right.poll();
        }
        else if(left.size() - right.size() >= 1) {
            right.add(mid);
            mid = left.poll();
        }
    }

    public static void solve() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
        mid = nums[0];
        StringBuilder sb = new StringBuilder();
        sb.append(mid + "\n");

        for(int i = 1; i < N; i++){
            addNum(nums[i]);
            sb.append(mid + "\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solve();
    }
}
