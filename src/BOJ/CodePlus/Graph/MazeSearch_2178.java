package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MazeSearch_2178 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] maze;
    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    public static class Point {
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static int bfs(int y, int x) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(y, x));

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextY = here.y + dy[i];
                int nextX = here.x + dx[i];
                if(isRange(nextY, nextX) && maze[nextY][nextX] == 1) {
                    queue.add(new Point(nextY, nextX));
                    maze[nextY][nextX] += maze[here.y][here.x];
                    if(y == N - 1 && x == M - 1) return maze[N-1][M-1];
                }
            }
        }

        return maze[N-1][M-1];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        for(int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs(0, 0));
    }
}
