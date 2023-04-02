package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Sudominoku_4574 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] board;
    private static List<int[]> domino;
    private static final int[] dy = {0, 1};
    private static final int[] dx = {1, 0};
    private static final int SIZE = 9;
    private static boolean[] isVisited;

    private static void print(int cnt) {
        System.out.println("Puzzle " + cnt);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isOk(int y, int x, int value) {
        // 열 검사
        for (int j = 0; j < N; j++) {
            if(board[y][j] == value) return false;
        }

        // 행 검사
        for (int i = 0; i < N; i++) {
            if(board[i][x] == value) return false;
        }

        // 정사각형 검사
        int ny = (y / 3) * 3, nx = (x / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[ny + i][nx + j] == value) return false;
            }
        }

        return true;
    }

    private static int[] findBlank() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(board[i][j] == 0) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    private static boolean solve(int index) {
        int[] here = findBlank();
        int y = here[0];
        int x = here[1];

        if(y == -1 && x == -1) return true;

        for (int i = 0; i < domino.size(); i++) {
            if(isVisited[i]) continue;
            int u = domino.get(i)[0];
            int v = domino.get(i)[1];

            for (int j = 0; j < 2; j++) { // ㅡ, ㅣ
                int ny = y + dy[j];
                int nx = x + dx[j];
                if (board[ny][nx] == 0) { // uv, vu
                    if (isOk(y, x, u) && isOk(ny, nx, v)) {
                        board[y][x] = u;
                        board[ny][nx] = v;
                        isVisited[i] = true;
                        if (solve(index + 1)) return true;
                        board[y][x] = 0;
                        board[ny][nx] = 0;
                        isVisited[i] = false;
                    }
                    if (isOk(y, x, v) && isOk(ny, nx, u)) {
                        board[y][x] = v;
                        board[ny][nx] = u;
                        isVisited[i] = true;
                        if (solve(index + 1)) return true;
                        board[y][x] = 0;
                        board[ny][nx] = 0;
                        isVisited[i] = false;
                    }
                }
            }
        }

        return false;
    }

    private static void setBoard(int value, String p) {
        board[(int) p.charAt(0) - 'A'][(int) p.charAt(1) - '0' - 1] = value;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            board = new int[N][N];
            List<int[]> placed = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int x1 = Integer.parseInt(st.nextToken());
                setBoard(x1, st.nextToken());

                int x2 = Integer.parseInt(st.nextToken());
                setBoard(x2, st.nextToken());

                if(x1 < x2) placed.add(new int[] {x1, x2});
                else placed.add(new int[] {x2, x1});
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < SIZE; i++) {
                setBoard(i + 1, st.nextToken());
            }

            Collections.sort(placed, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                }
            });

            int index = 0;
            domino = new ArrayList<>();
            for (int i = 1; i <= SIZE; i++) {
                for (int j = i + 1; j <= SIZE; j++) {
                    if(index < placed.size() && placed.get(index)[0] == i && placed.get(index)[1] == j) {
                        index += 1;
                        continue;
                    }
                    domino.add(new int[]{i, j});
                }
            }
            isVisited = new boolean[domino.size()];
            solve(0);
            print(cnt);
            cnt += 1;
        }
    }
}
