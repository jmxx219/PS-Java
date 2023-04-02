package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Escape_3055 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int R;
    private static int C;
    private static char[][] map;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};

    public static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    private static int solve() {
        Queue<Point> queue = new LinkedList<>();
        int[][] wTime = new int[R][C];
        int[][] dist = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(wTime[i], -1);
            Arrays.fill(dist[i], -1);
        }

        Point start = null;
        for (int i = 0; i < R; i++) { // 고슴도치: S, 비버: D, 물: *, 돌: X, 빈칸: .
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'S') {
                    start = new Point(i, j);
                }
                if(map[i][j] == '*') {
                    queue.add(new Point(i, j));
                    wTime[i][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) { // 물 채우기
            Point here = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if (!isRange(ny, nx)) continue;
                if (map[ny][nx] == 'X' || map[ny][nx] == 'D') continue;

                if(wTime[ny][nx] == -1) {
                    wTime[ny][nx] = wTime[here.y][here.x] + 1;
                    queue.add(new Point(ny, nx));
                }
            }
        }

        queue.add(start);
        dist[start.y][start.x] = 0;
        while (!queue.isEmpty()) {
            Point here = queue.poll();
            if(map[here.y][here.x] == 'D') return dist[here.y][here.x];

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;
                if(map[ny][nx] == 'X') continue;

                int nDist = dist[here.y][here.x] + 1;
                if(wTime[ny][nx] != -1 && nDist >= wTime[ny][nx]) continue; // 다음 칸이 물로 찼을 경우
                if(dist[ny][nx] == - 1) {
                    dist[ny][nx] = nDist;
                    queue.add(new Point(ny, nx));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int res = solve();
        if (res == -1) System.out.println("KAKTUS");
        else System.out.println(res);
    }
}
