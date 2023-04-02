package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sudoku_2580 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int N = 9;
    private static int[][] board;
    private static List<int[]> blank;

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

    public static boolean solve(int index) {
        if(index == blank.size()) {
            return true;
        }

        for (int i = 1; i <= N; i++) {
            int y = blank.get(index)[0];
            int x = blank.get(index)[1];
            if(isOk(y, x, i)) {
                board[y][x] = i;
              if(solve(index + 1)) return true;
                board[y][x] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[N][N];
        blank = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) blank.add(new int[]{i, j});
            }
        }

        solve(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
