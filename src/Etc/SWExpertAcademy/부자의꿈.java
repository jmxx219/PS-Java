package Etc.SWExpertAcademy;

import java.util.Scanner;

public class 부자의꿈 {
    private static int N;
    private static int M;
    private static int[][] A;
    private static int[] vRow;
    private static int[] vCol;
    private static int[] row;
    private static int[] col;
    private static int safety;
    private static int res;

    public static void init() {
        vRow = new int[N]; // 최대값
        vCol = new int[M];
        row = new int[N]; // 위치
        col = new int[M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(vRow[r] == 0 || vRow[r] < A[r][c]) {
                    vRow[r] = A[r][c];
                    row[r] = c;
                }
            }
        }

        for (int c = 0; c < M; c++) {
            for (int r = 0; r < N; r++) {
                if(vCol[c] == 0 || vCol[c] < A[r][c]) {
                    vCol[c] = A[r][c];
                    col[c] = r;
                }
            }
        }

        for (int c = 0; c < M; c++) {
            if(row[col[c]] == c) {
                safety += 1;
            }
        }


    }

    public static void update(int r, int c, int num) {
        A[r][c] = num;

        if(row[r] == c && col[c] == r) {
            vRow[r] = vCol[c] = num;
        }
        else {
            if(vRow[r] < num && vCol[c] < num) {
                safety += 1;
                if(col[row[r]] == r) safety -= 1;
                if(row[col[c]] == c) safety -= 1;
                row[r] = c;
                col[c] = r;
                vRow[r] = vCol[c] = num;
            }
            else if(vRow[r] < num) {
                if(col[row[r]] == r) safety -= 1;
                row[r] = c;
                vRow[r] = num;
            }
            else if(vCol[c] < num) {
                if(row[col[c]] == c) safety -= 1;
                col[c] = r;
                vCol[c] = num;
            }
        }

        res += safety;
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            int Q = sc.nextInt();
            
            A = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    A[i][j] = sc.nextInt();
                }
            }

            safety = 0;
            res = 0;
            init();
            for (int q = 0; q < Q; q++) {
                int r = sc.nextInt() - 1;
                int c = sc.nextInt() - 1;
                int x = sc.nextInt();
                update(r, c, x);
            }

            System.out.println("#" + test_case + " " + res);
            
        }
    }
}
