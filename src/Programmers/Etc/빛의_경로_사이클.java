package Programmers.Etc;

import java.util.*;

// F
public class 빛의_경로_사이클 {
    private static int N;
    private static int M;
    private static char[][] map;
    private static boolean[][][] visited;
    private static final int[] dy = {-1, 0, 1, 0}; // 하, 좌, 상, 우
    private static final int[] dx = {0, -1, 0, 1};

    private static int search(int y, int x, int dir) {
        int cnt = 0;

        while (!visited[y][x][dir]) {
            visited[y][x][dir] = true;
            cnt += 1;

            if(map[y][x] == 'L') {
                dir = (dir == 0) ? 3 : dir - 1;
            }
            else if(map[y][x] == 'R') {
                dir = (dir == 3) ? 0 : dir + 1;
            }

            y = (y + dy[dir] + N) % N;
            x = (x + dx[dir] + M) % M;
        }

        return cnt;
    }

    public static int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        map = new char[N][M];
        visited = new boolean[N][M][4]; // 상 하 좌 우 - 0 1 2 3

        for (int i = 0; i < grid.length; i++) {
            map[i] = grid[i].toCharArray();
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    if(!visited[i][j][dir]) {
                        res.add(search(i, j, dir));
                    }
                }
            }
        }

        return res.stream().sorted().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] solution = solution(new String[] {"R","R"});
        for (int x : solution) System.out.println(x + " ");
    }
}
