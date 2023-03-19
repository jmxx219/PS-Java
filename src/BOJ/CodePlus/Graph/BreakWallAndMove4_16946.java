package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallAndMove4_16946 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[][] group;
    private static boolean[][] check;
    private static List<Integer> groupSize;
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
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static void bfs(int y, int x) {
        int color = groupSize.size();
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(y, x));
        check[y][x] = true;
        group[y][x] = color;

        int cnt = 1;
        while (!queue.isEmpty()) {
            Point here = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(map[ny][nx] == 0 && !check[ny][nx]) {
                    queue.add(new Point(ny, nx));
                    group[ny][nx] = color;
                    check[ny][nx] = true;
                    cnt += 1;
                }
            }
        }

        groupSize.add(cnt);
    }

    private static int moveCnt(int y, int x) {
        HashSet<Integer> near = new HashSet<>();

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(isRange(ny, nx) && map[ny][nx] == 0) {
                near.add(group[ny][nx]);
            }
        }

        int cnt = 1;
        for (int g : near) {
            cnt += groupSize.get(g);
        }
        return cnt;
    }

    private static void solve() throws IOException {
        groupSize = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !check[i][j]) {
                    bfs(i, j);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    // 틀림 왜일까?
//                    map[i][j] = moveCnt(i, j) % 10;
//                    bw.write(String.valueOf(map[i][j]));
                    bw.write(String.valueOf(moveCnt(i, j) % 10));
                }
                else {
                    bw.write("0");
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        group = new int[N][M];
        check = new boolean[N][M];
        for(int i = 0; i < N; i++) {
          String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                check[i][j] = false;
                group[i][j] = -1;
            }
        }
        solve();
    }
}
