package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// FFF -> RRR!!!!
public class MarblesEscape2_13460 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static char[][] board;
    private static int[] red = new int[2];
    private static int[] blue = new int[2];
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    public static class Marbles {
        int ry, rx;
        int by, bx;
        int dist;

        public Marbles(int ry, int rx, int by, int bx, int dist) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.dist = dist;
        }
    }

    public static int solve() {
        Queue<Marbles> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.add(new Marbles(red[0], red[1], blue[0], blue[1], 0));
        visited[red[0]][red[1]][blue[0]][blue[1]] = true;

        while (!queue.isEmpty()) {
            Marbles marbles = queue.poll();
            int ry = marbles.ry;
            int rx = marbles.rx;
            int by = marbles.by;
            int bx = marbles.bx;
            int dist = marbles.dist;
            if (dist >= 10) return -1;

            for (int dir = 0; dir < 4; dir++) {
                boolean redFinish = false;
                boolean blueFinish = false;

                int nRy = ry;
                int nRx = rx;
                while (board[nRy + dy[dir]][nRx + dx[dir]] != '#') {
                    nRy += dy[dir];
                    nRx += dx[dir];
                    if (board[nRy][nRx] == 'O') {
                        redFinish = true;
                        break;
                    }
                }

                int nBy = by;
                int nBx = bx;
                while (board[nBy + dy[dir]][nBx + dx[dir]] != '#') {
                    nBy += dy[dir];
                    nBx += dx[dir];
                    if (board[nBy][nBx] == 'O') {
                        blueFinish = true;
                        break;
                    }
                }

                if (blueFinish) continue;
                if (redFinish) return dist + 1;

                // 겹치는 경우
                if (nRy == nBy && nRx == nBx) {
                    if (dir == 0) { // 위쪽으로 기울이기
                        if (ry > by) nRy += 1;
                        else nBy += 1;
                    } else if (dir == 1) { // 오른쪽으로 기울이기
                        if (rx < bx) nRx -= 1;
                        else nBx -= 1;
                    } else if (dir == 2) { // 아래쪽으로 기울이기
                        if (ry < by) nRy -= 1;
                        else nBy -= 1;
                    } else { // 왼쪽으로 기울이기
                        if (rx > bx) nRx += 1;
                        else nBx += 1;
                    }
                }

                if (!visited[nRy][nRx][nBy][nBx]) {
                    visited[nRy][nRx][nBy][nBx] = true;
                    queue.add(new Marbles(nRy, nRx, nBy, nBx, dist+1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                }
                if(board[i][j] == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                }
            }
        }

        System.out.println(solve());
    }
}
