package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤커브_15685 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int M = 100;
    private static boolean[][] map;
    private static final int[] dy = {0, -1, 0, 1};
    private static final int[] dx = {1, 0, -1, 0};

    private static List<Integer> calcDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        while(g-- > 0) {
            for(int i = directions.size() - 1; i >= 0; i--) {
                int nextDir = (directions.get(i) + 1) % 4;
                directions.add(nextDir);
            }
        }
        return directions;
    }

    private static void drawCurve(int y, int x, int d, int g) {
        List<Integer> directions = calcDirections(d, g);
        map[y][x] = true;
        for(int dir : directions) {
            y += dy[dir];
            x += dx[dir];
            map[y][x] = true;
        }
    }

    private static int countCurve() {
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) cnt += 1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new boolean[M + 1][M + 1];
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            drawCurve(y, x, d, g);
        }

        System.out.println(countCurve());
    }
}
