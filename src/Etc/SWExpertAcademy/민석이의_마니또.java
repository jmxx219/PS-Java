package Etc.SWExpertAcademy;

import java.util.Scanner;

public class 민석이의_마니또 {
    private static int N;
    private static int[][] edge;
    private static final int INF = 987654321;
    private static int res;

    public static void floydWarshall() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
                }
            }
        }
    }

    public static void solve() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (edge[i][j] == INF) System.out.print("INF ");
                else System.out.print(edge[i][j] + " ");
            }
            System.out.println();
        }


        for(int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (edge[i][j] == INF || edge[j][i] == INF) continue;
                if(res > edge[i][j] + edge[j][i]) {
                    res = edge[i][j] + edge[j][i];
                }
            }
        }

        if(res == INF) res = -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            int M = sc.nextInt();

            edge = new int[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i != j) edge[i][j] = INF;
                }
            }

            res = INF;
            for (int i = 0; i < M; i++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int c = sc.nextInt();
                edge[x][y] = Math.min(edge[x][y], c);
                if(x == y && res > c) res = c;
            }

            floydWarshall();
            solve();
            System.out.println("#" + test_case + " " + res);
        }
    }
}
