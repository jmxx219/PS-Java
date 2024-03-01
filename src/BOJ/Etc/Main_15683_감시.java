package BOJ.Etc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_감시 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] map;
    private static List<Point> cctv;
    private static final int[] cctvDirection = {0, 4, 2, 4, 4, 1};
    private static int res;
    private static final int[] dy = {-1, 0, 1, 0}; // U, R, D, L
    private static final int[] dx = {0, 1, 0, -1};

    private static class Point {
        int y, x;
        int num;

        public Point(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }

    private static boolean isPossibleWatch(int cctv, int cctvDir, int dir) {
        if(cctv == 1) {
            if(cctvDir == dir) return true;
            else return false;
        }
        else if(cctv == 2){
            if(cctvDir == 0 && (dir == 0 || dir == 2)) return true;
            else if(cctvDir == 1 && (dir == 1 || dir == 3)) return true;
            else return false;
        }
        else if(cctv == 3){
            if(cctvDir == 0 && (dir == 0 || dir == 1)) return true;
            else if(cctvDir == 1 && (dir == 1 || dir == 2)) return true;
            else if(cctvDir == 2 && (dir == 2 || dir == 3)) return true;
            else if(cctvDir == 3 && (dir == 3 || dir == 0)) return true;
            else return false;
        }
        else if(cctv == 4){
            if(cctvDir != dir) return true;
            else return false;
        }
        return true; // cctv 5: 모든 방향 감시 가능
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }


    private static void watch(Point currCctv, int cctvDir, int[][] tmp) {
        for (int i = 0; i < 4; i++) {
            if(isPossibleWatch(currCctv.num, cctvDir, i)) {
                int y = currCctv.y;
                int x = currCctv.x;

                while (true) {
                    y += dy[i];
                    x += dx[i];

                    if(!isRange(y, x) || tmp[y][x] == 6) break;
                    if(1 <= tmp[y][x] && tmp[y][x] <= 5) continue;
                    tmp[y][x] = -1; // # 감시 완료
                }
            }
        }
    }

    private static int calc(int[] picked) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < cctv.size(); i++) {
            Point currCctv = cctv.get(i);
            int direction = picked[i];
            watch(currCctv, direction, tmp);
        }
        
        int space = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == 0) space += 1;
            }
        }

        return space;
    }

    private static void solve(int index, int[] picked) {
        if(index == cctv.size()) {
            res = Math.min(res, calc(picked));
            return;
        }

        int currCctv = cctv.get(index).num;
        for (int i = 0; i < cctvDirection[currCctv]; i++) {
            picked[index] = i;
            solve(index + 1, picked);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctv = new ArrayList<>();
        map = new int[N][M]; // 0: 빈칸, 6: 벽, 1~5: CCTV 번호(CCTV 간에는 통과 가능)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(1 <= map[i][j] && map[i][j] <= 5) {
                    cctv.add(new Point(i, j, map[i][j]));
                }
            }
        }
        
        res = N * M;
        solve(0, new int[cctv.size()]);
        System.out.println(res);
    }
}
