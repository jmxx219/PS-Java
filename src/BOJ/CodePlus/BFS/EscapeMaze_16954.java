package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EscapeMaze_16954 {
    private static BufferedReader br;
    private static char[][] board;
    private static final int N = 8;
    private static List<Point> wall;
    private static final int[] dy = {0, 0, 1, - 1, 1, 1, -1, -1, 0};
    private static final int[] dx = {1, -1, 0, 0, -1, 1, -1, 1, 0};
    private static int timeOut = 0;

    public static class Point {
        int y, x;
        int time;
        public Point(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int solve() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N - 1, 0, 0));

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            if(here.time >= timeOut || (here.y == 0 && here.x == N - 1)) return 1;

            int nTime = here.time + 1;
            for (int i = 0; i < 9; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(ny - here.time >= 0 && board[ny - here.time][nx] == '#') continue; // 이동할 칸에 벽이 있는 경우

                // 벽이 움직이는 경우도 리스트 만들 필요 없이 시간으로 체크 가능 ! ! !
//                boolean movable = true;
//                for(Point w : wall) {
//                    if(ny == w.y + nTime && nx == w.x) { // 이동할 칸에 벽이 올 경우 -> 해당 칸은 이동 불가
//                        movable = false;
//                        break;
//                    }
//                }
//                if(movable) {
//                    queue.add(new Point(ny, nx, nTime));
//                }

                if(ny - nTime >= 0 && board[ny - nTime][nx] == '#') continue;
                queue.add(new Point(ny, nx, nTime));
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[N][N];
        wall = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if(board[i][j] == '#') {
                    if(timeOut == 0) timeOut = N - i;
                    wall.add(new Point(i, j, 0));
                }
            }
        }
        System.out.println(solve());
    }
}
