package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KnightMove_7562 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int I;
    private static int[][] chessboard;
    private static final int[] dy = {-2, -2, 2, 2, -1, -1, 1, 1};
    private static final int[] dx = {-1, 1, -1, 1, -2, 2, -2, 2};

    public static class Point {
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < I && 0 <= x && x < I;
    }

    private static int bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        chessboard[start.y][start.x] = 1;

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            for(int i = 0; i < 8; i++) {
                int nextY = here.y + dy[i];
                int nextX = here.x + dx[i];
                if(isRange(nextY, nextX) && chessboard[nextY][nextX] == 0) {
                    queue.add(new Point(nextY, nextX));
                    chessboard[nextY][nextX] += chessboard[here.y][here.x] + 1;
                    if(nextY == end.y && nextX == end.x) return chessboard[end.y][end.x] - 1;
                }
            }
        }

        return chessboard[end.y][end.x] - 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            chessboard = new int[I][I];

            st = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            System.out.println(bfs(start, end));
        }
    }
}
