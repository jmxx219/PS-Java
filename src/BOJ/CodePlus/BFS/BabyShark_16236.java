package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark_16236 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] space;
    private static int sharkY = -1;
    private static int sharkX = -1;
    private static int sharkSize = 2;
    private static int[] fishesSize;
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    public static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int eatable() {
        int cnt = 0;
        for (int i = 1; i < fishesSize.length; i++) {
            if(i < sharkSize) cnt += fishesSize[i];
        }
        return cnt;
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int eat() {
        int minTime = Integer.MAX_VALUE;
        Point fish = null;
        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        queue.add(new Point(sharkY, sharkX));
        dist[sharkY][sharkX] = 0;

        while (!queue.isEmpty()) {
            Point here = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;
                if(space[ny][nx] > sharkSize) continue;

                if(dist[ny][nx] == -1) {
                    dist[ny][nx] = dist[here.y][here.x] + 1;
                    queue.add(new Point(ny, nx));
                    if(space[ny][nx] != 0 && space[ny][nx] < sharkSize) {
                        if(minTime > dist[ny][nx]) {
                            fish = new Point(ny, nx);
                            minTime = dist[ny][nx];
                        }
                        else if(minTime == dist[ny][nx] && fish.y > ny) {
                            fish = new Point(ny, nx);
                        }
                        else if(minTime == dist[ny][nx] && fish.y == ny && fish.x > nx) {
                            fish = new Point(ny, nx);
                        }
                    }
                }
            }
        }

        if(fish == null) return 0;

        fishesSize[space[fish.y][fish.x]] -= 1;
        space[fish.y][fish.x] = 0;
        sharkY = fish.y;
        sharkX = fish.x;

        return dist[fish.y][fish.x];
    }

    private static int solve() {
        int eatingFishSize = 0;
        int time = 0;
        while (eatable() > 0) {
            int t = eat();
            if(t == 0) return time;
            time += t;
            eatingFishSize += 1;
            if(eatingFishSize == sharkSize) {
                sharkSize += 1;
                eatingFishSize = 0;
            }
        }
        return time;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        fishesSize = new int[7];
        space = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if(space[i][j] == 9) {
                    space[i][j] = 0;
                    sharkY = i;
                    sharkX = j;
                }
                else if(space[i][j] != 0) {
                    fishesSize[space[i][j]] += 1;
                }
            }
        }

        System.out.println(solve());
    }
}
