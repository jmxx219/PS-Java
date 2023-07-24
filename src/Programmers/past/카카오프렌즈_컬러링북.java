package Programmers.past;

import java.util.*;

// Pass(18m)
class 카카오프렌즈_컬러링북 {
    private final int[] dy = {1, -1, 0, 0};
    private final int[] dx = {0, 0, 1, -1};
    private int M;
    private int N;

    private boolean isRange(int y, int x) {
        return 0 <= y && y < M && 0 <= x && x < N;
    }

    private int solve(int y, int x, boolean[][] visited, int[][] picture) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {y, x});

        visited[y][x] = true;

        while(!queue.isEmpty()) {
            int[] here = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ny = here[0] + dy[i];
                int nx = here[1] + dx[i];
                if(!isRange(ny, nx)) continue;

                if(picture[ny][nx] == picture[y][x] && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    cnt += 1;
                    queue.add(new int[] {ny, nx});
                }
            }
        }

        return cnt;
    }

    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[M][N];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(picture[i][j] == 0) continue;
                if(!visited[i][j]) {
                    int size = solve(i, j, visited, picture);
                    if(maxSizeOfOneArea < size) maxSizeOfOneArea = size;
                    numberOfArea += 1;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}