package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두개의_숫자열_1959 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int[] A; // long
    private static int[] B;

    private static int solve() {
        int res = 0;
        int x = A.length - B.length;
        for(int i = 0; i <= x; i++) {
            int tmpSum = 0;
            for(int j = 0; j < B.length; j++) {
                tmpSum += B[j] * A[j + i];
            }
            res = Math.max(res, tmpSum);
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a < b) {
                B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            else {
                A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            System.out.println("#" + (i + 1) + " " + solve());
        }
    }
}
