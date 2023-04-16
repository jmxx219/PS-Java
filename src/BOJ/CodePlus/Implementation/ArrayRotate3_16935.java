package BOJ.CodePlus.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ArrayRotate3_16935 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int R;
    private static int[][] A;
    private static int[] op;

    public static void print() {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void swap(int aY, int aX, int bY, int bX) {
        int tmp = A[aY][aX];
        A[aY][aX] =  A[bY][bX];
        A[bY][bX] = tmp;
    }

    public static void one() {
        int up = 0;
        int down = N - 1;
        while (up < down) {
            for (int j = 0; j < M; j++) {
                swap(up, j, down, j);
            }
            up += 1;
            down -= 1;
        }
    }

    public static void two() {
        int left = 0;
        int right = M - 1;
        while (left < right) {
            for (int i = 0; i < N; i++) {
                swap(i, left, i, right);
            }
            left += 1;
            right -= 1;
        }
    }

    public static void three() {
        int[][] tmp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = A[N - j - 1][i];
            }
        }
        int tmpSize = N;
        N = M;
        M = tmpSize;

        A = tmp;
    }

    public static void four() {
        int[][] tmp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = A[j][M - i - 1];
            }
        }
        int tmpSize = N;
        N = M;
        M = tmpSize;

        A = tmp;
    }

    public static void five() {
        int nextN = N / 2;
        int nextM = M / 2;
        int[][] tmp = new int[N][M];

        for (int i = 0; i < nextN; i++) {
            for (int j = 0; j < nextM; j++) {
                tmp[i][j + nextM] = A[i][j];
            }
        }

        for (int i = 0; i < nextN; i++) {
            for (int j = nextM; j < M; j++) {
                tmp[i + nextN][j] = A[i][j];
            }
        }

        for (int i = nextN; i < N; i++) {
            for (int j = nextM; j < M; j++) {
                tmp[i][j - nextM] = A[i][j];
            }
        }

        for (int i = nextN; i < N; i++) {
            for (int j = 0; j < nextM; j++) {
                tmp[i - nextN][j] = A[i][j];
            }
        }

        A = tmp;
    }

    public static void six() {
        int nextN = N / 2;
        int nextM = M / 2;
        int[][] tmp = new int[N][M];

        for (int i = 0; i < nextN; i++) {
            for (int j = 0; j < nextM; j++) {
                tmp[i + nextN][j] = A[i][j];
            }
        }

        for (int i = 0; i < nextN; i++) {
            for (int j = nextM; j < M; j++) {
                tmp[i][j - nextM] = A[i][j];
            }
        }

        for (int i = nextN; i < N; i++) {
            for (int j = nextM; j < M; j++) {
                tmp[i - nextN][j] = A[i][j];
            }
        }

        for (int i = nextN; i < N; i++) {
            for (int j = 0; j < nextM; j++) {
                tmp[i][j + nextM] = A[i][j];
            }
        }

        A = tmp;
    }

    public static void solve() {
        for (int i = 0; i < R; i++) {
            switch (op[i]) {
                case 1: // 상하 반전
                    one();
                    break;
                case 2: // 좌우 반전
                    two();
                    break;
                case 3: // 오른쪽으로 90도 회전
                    three();
                    break;
                case 4: // 왼쪽으로 90도 회전
                    four();
                    break;
                case 5: // Z 시계
                    five();
                    break;
                case 6: // Z 반시계
                    six();
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        op = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        solve();
        print();
    }
}
