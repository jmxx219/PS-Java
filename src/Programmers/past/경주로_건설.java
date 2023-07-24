package Programmers.past;

import java.util.*;

// Pass(50m)
class 경주로_건설 {
    // 0: 빈 칸, 1: 벽
    // 출발점은 (0, 0), 도착점은 (N-1, N-1)
    // 경주로는 상, 하, 좌, 우로 인접한 두 빈 칸을 연결하여 건설, 벽에는 설치 x
    // 직선도로: 상하, 좌우로 연결 - 100원
    // 코너: 두 직선도로가 직각으로 만는 지점 - 500원

    private int N;
    private static final int INF = 987654321;
    private final int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    private final int[] dx = {0, 0, -1, 1};

    static class Road implements Comparable<Road>{
        public int y, x;
        public int cost;
        public int dir;
        public Road(int y, int x, int cost, int dir) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.dir = dir;
        }

        @Override
        public int compareTo(Road r) {
            return this.cost - r.cost;
        }
    }


    private boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private boolean isCurve(int currDir, int nextDir) {
        return (currDir <= 1 && nextDir >= 2) || (nextDir <= 1 && currDir >= 2);
    }

    private int solve(int[][] board) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        int[][][] dist = new int[N][N][4];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) Arrays.fill(dist[i][j], INF);
        }

        pq.add(new Road(0, 0, 0, 0));
        dist[0][0][0] = dist[0][0][1] = dist[0][0][2] = dist[0][0][3] = 0;

        while(!pq.isEmpty()) {
            Road here = pq.poll();

            if(dist[here.y][here.x][here.dir] < here.cost) continue;

            for(int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(!isRange(ny, nx)) continue;
                if(board[ny][nx] == 1) continue;

                int nextDist = here.cost + 100;
                if(!(here.y == 0 && here.x == 0)
                        && isCurve(here.dir, dir)) nextDist += 500;
                if(dist[ny][nx][dir] > nextDist) {
                    dist[ny][nx][dir] = nextDist;
                    pq.add(new Road(ny, nx, nextDist, dir));
                }
            }

        }

        int ans = INF;
        for(int i = 0; i < 4; i++) {
            if(ans > dist[N - 1][N - 1][i]) ans = dist[N - 1][N - 1][i];
        }

        return ans;
    }

    public int solution(int[][] board) {
        N = board.length;

        return solve(board);
    }
}