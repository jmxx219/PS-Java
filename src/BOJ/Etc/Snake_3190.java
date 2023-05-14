package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Snake_3190 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int L;
    private static int[][] directions; // 왼쪽L : 0, 오른쪽 D: 1
    private static int[] dy = {-1, 0, 1, 0}; // U, R, D, L
    private static int[] dx = {0, 1, 0, -1};
    private static int[][] map; // 사과:1, 뱀:2

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
    private static boolean isBody(int y, int x) {
        return map[y][x] == 2;
    }

    public static int changeDirection(int currD, int nextD) {
        if(currD == 0) return nextD == 0 ? 3 : 1;
        else if(currD == 1) return nextD == 0 ? 0 : 2;
        else if(currD == 2) return nextD == 0 ? 1 : 3;
        return nextD == 0 ? 2 : 0;
    }

    private static int solve() {
        Queue<Point> snack = new LinkedList<>();
        int time = 0;
        int dIndex = 0;
        int currDirection = 1; // R

        snack.add(new Point(0, 0));
        Point tail = new Point(0, 0);
        Point head = new Point(0, 0);
        map[0][0] = 2;

        while (true) {
            int ny = head.y + dy[currDirection];
            int nx = head.x + dx[currDirection];

            if(!isRange(ny, nx)) break;
            if(isBody(ny, nx)) break;

            head = new Point(ny, nx);
            snack.add(new Point(ny, nx));


            if(map[ny][nx] != 1) {
                snack.poll();
                map[tail.y][tail.x] = 0;
                tail = new Point(snack.peek().y, snack.peek().x);

            }
            map[ny][nx] = 2;
            time += 1;

            if(dIndex < directions.length && time == directions[dIndex][0]) {
                currDirection = changeDirection(currDirection, directions[dIndex][1]);
                dIndex += 1;
            }
        }

        return time + 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = 1;
        }

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        directions = new int[L][2];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            directions[i][0] = Integer.parseInt(st.nextToken());
            if(st.nextToken().equals("L")) directions[i][1] = 0;
            else directions[i][1] = 1;
        }

        System.out.println(solve());
    }
}
