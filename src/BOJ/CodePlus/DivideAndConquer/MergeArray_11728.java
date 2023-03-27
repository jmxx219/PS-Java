package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MergeArray_11728 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[] A;
    private static int[] B;
    private static int[] mergeAB;

    public static void solve() {
        int index = 0;
        int a = 0, b = 0;

        while (a < A.length && b < B.length) {
            if(A[a] <= B[b]) {
                mergeAB[index++] = A[a++];
            }
            else {
                mergeAB[index++] = B[b++];
            }
        }

        while (a < A.length) {
            mergeAB[index++] = A[a++];
        }

        while (b < B.length) {
            mergeAB[index++] = B[b++];
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());

        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        mergeAB = new int[sizeA + sizeB];
        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mergeAB.length; i++) {
            sb.append(mergeAB[i] + " ");
        }
        System.out.println(sb);
    }
}
