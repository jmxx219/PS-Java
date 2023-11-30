package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4_17406 {
    public static class Rotation {
        public int r;
        public int c;
        public int s;
        public Rotation(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int K;
    private static int[][] arr;
    private static int[][] nArr;
    private static Rotation[] rotation;
    private static int[] picked;
    private static int res;

    private static void rotateArr(int y, int x, int len) {
        int start = nArr[y][x];
        for (int i = y; i < y + len; i++) {
            nArr[i][x] = nArr[i + 1][x];
        }

        for (int j = x; j < x + len; j++) {
            nArr[y + len][j] = nArr[y + len][j + 1];
        }

        for (int i = y + len; i > y; i--) {
            nArr[i][x + len] = nArr[i - 1][x + len];
        }

        for (int j = x + len; j > x; j--) {
            nArr[y][j] = nArr[y][j - 1];
        }

        nArr[y][x + 1] = start;
    }

    private static void rotate(int y, int x, int len) {
        if(len == 0) return;
        rotateArr(y, x, len);
        rotate(y + 1, x + 1, len - 2);
    }

    private static int calc() {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += nArr[i][j];
            }
            res = Math.min(res, sum);
        }
        return res;
    }

    private static void rotateAndCalc() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nArr[i][j] = arr[i][j];
            }
        }
        for (int i = 0; i < K; i++) {
            Rotation cur = rotation[picked[i]];
            rotate(cur.r - cur.s, cur.c - cur.s, 2 * cur.s);
        }
        res = Math.min(res, calc());
    }

    private static void solve(int index, boolean[] check) {
        if(index == K) {
            rotateAndCalc();
            return;
        }

        for (int i = 0; i < K; i++) {
            if(!check[i]) {
                check[i] = true;
                picked[index] = i;
                solve(index + 1, check);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        nArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation = new Rotation[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            rotation[i] = new Rotation(r, c, s);
        }

        res = Integer.MAX_VALUE;
        picked = new int[K];
        solve(0, new boolean[K]);
        System.out.println(res);
    }
}
