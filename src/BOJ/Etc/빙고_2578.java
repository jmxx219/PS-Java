package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빙고_2578 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int N = 5;
    private static int[][] A;
    private static int[] B;
    private static boolean[][] ok;

    private static int count() {
        int cnt = 0;
        boolean check = true;

        for (int i = 0; i < N; i++) {
            check = true;
            for (int j = 0; j < N; j++) {
                if(! ok[i][j]) check = false;
            }
            if(check) cnt += 1;
        }

        for (int j = 0; j < N; j++) {
            check = true;
            for (int i = 0; i < N; i++) {
                if(! ok[i][j]) check = false;
            }
            if(check) cnt += 1;
        }

        check = true;
        for (int i = 0; i < N; i++) {
            if(!ok[i][i]) check = false;
        }
        if(check) cnt += 1;

        check = true;
        for (int i = 0; i < N; i++) {
            if(!ok[i][N - i - 1]) check = false;
        }
        if(check) cnt += 1;

        return cnt;
    }

    private static void check(int index) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(A[i][j] == B[index]) {
                    ok[i][j] = true;
                    return;
                }
            }
        }
    }

    private static int solve() {
        int i = 0;
        for (; i < N * N; i++) {
            check(i);
            if(count() >= 3) break;
        }
        return i + 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        A = new int[N][N];
        B = new int[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                B[i * 5 + j] = Integer.parseInt(st.nextToken());
            }
        }

        ok = new boolean[N][N];
        System.out.println(solve());
    }
}
