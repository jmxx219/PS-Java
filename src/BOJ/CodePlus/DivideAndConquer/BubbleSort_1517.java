package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BubbleSort_1517 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;
    private static long swapCnt = 0;

    public static void merge(int start, int mid, int end) {
        int l = start;
        int r = mid + 1;

        int[] tmp = new int[end - start + 1];
        int index = 0;

        while (l <= mid && r <= end) {
            if(nums[l] <= nums[r]) tmp[index++] = nums[l++];
            else {
                swapCnt += (long)(mid - l + 1);
                tmp[index++] = nums[r++];
            }
        }

        while (l <= mid) tmp[index++] = nums[l++];
        while (r <= end) tmp[index++] = nums[r++];

        for (int i = start; i <= end; i++) {
            nums[i] = tmp[i - start];
        }
    }

    public static void solve(int start, int end) {
        if(start == end) return;

        int mid = (start + end) / 2;
        solve(start, mid);
        solve(mid + 1, end);
        merge(start, mid, end);
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

        solve(0, N - 1);
        System.out.println(swapCnt);
    }
}
