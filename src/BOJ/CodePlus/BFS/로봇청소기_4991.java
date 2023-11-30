package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇청소기_4991 {
    public static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int W;
    private static int H;
    private static char[][] room;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};
    private static Point start;
    private static List<Point> dirty;
    private static int[][] dirtyDist;
    private static int[] startDist;
    private static int[] picked;
    private static int res;

    private static boolean isRange(int y, int x){
        return 0 <= y && y < H && 0 <= x && x < W;
    }

    private static int bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dist[i], -1);
        }

        queue.add(start);
        dist[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Point here = queue.poll();

            if(here.y == end.y && here.x == end.x) break;

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];

                if(!isRange(ny, nx)) continue;

                if(dist[ny][nx] == -1 && room[ny][nx] != 'x') {
                    dist[ny][nx] = dist[here.y][here.x] + 1;
                    queue.add(new Point(ny, nx));
                }
            }
        }

        return dist[end.y][end.x];
    }

    private static int calcDist() {
        if(startDist[picked[0]] == -1) return Integer.MAX_VALUE;

        int resDist = startDist[picked[0]];
        for (int i = 0; i < picked.length - 1; i++) {
            int nextDist = dirtyDist[picked[i]][picked[i + 1]];
            if(nextDist == -1) return Integer.MAX_VALUE;
            resDist += nextDist;
        }

        return resDist;
    }

    private static void permutation(int index, boolean[] check) {
        if(index == dirty.size()) {
            res = Math.min(res, calcDist());
            return;
        }

        for (int i = 0; i < dirty.size(); i++) {
            if(!check[i]) {
                check[i] = true;
                picked[index] = i;
                permutation(index + 1, check);
                check[i] = false;
            }
        }
    }

    private static int solve() {
        startDist = new int[dirty.size()];
        for (int i = 0; i < dirty.size(); i++) {
            startDist[i] = bfs(start, dirty.get(i));
        }

        dirtyDist = new int[dirty.size()][dirty.size()];
        for (int i = 0; i < dirty.size(); i++) {
            for (int j = i + 1; j < dirty.size(); j++) {
                if(i == j) continue;
                dirtyDist[i][j] = bfs(dirty.get(i), dirty.get(j));
                dirtyDist[j][i] = dirtyDist[i][j];
            }
        }

        res = Integer.MAX_VALUE;
        picked = new int[dirty.size()];
        permutation(0, new boolean[dirty.size()]);

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0) break;

            room = new char[H][W];
            dirty = new ArrayList<>();
            for (int i = 0; i < H; i++) {
                room[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    if(room[i][j] == 'o') {
                        start = new Point(i, j);
                    }
                    if(room[i][j] == '*') {
                        dirty.add( new Point(i, j));
                    }
                }
            }
            System.out.println(solve());
        }

    }
}
