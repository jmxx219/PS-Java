package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrevPermutation_10973 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] nums;

    private static void swap(int a, int b) {
        int tmp = nums[b];
        nums[b] = nums[a];
        nums[a] = tmp;
    }

    private static boolean prev_permutation() {
        int i = N - 1;
        for(; i > 0; i--) {
            if(nums[i - 1] > nums[i]) break;
        }
        if(i == 0) return false;

        int j = N - 1;
        for(; j >= i; j--) {
            if(nums[i - 1] > nums[j]) break;
        }

        swap(i - 1, j);

        j = N - 1;
        while (i < j) {
            swap(i, j);
            i += 1;
            j -= 1;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if(prev_permutation()) {
            for(int i = 0; i < N; i++) {
                System.out.print(nums[i] + " ");
            }
        }
        else {
            System.out.println(-1);
        }
    }
}
