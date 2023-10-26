package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// FFF -> 백트래킹
// E까지 가는 모든 경로 탐색 + 도착했을 때 최단 거리 값 구하기
// 현재 저장된 distH(진행가능 거리) 보다 다음 nhp + numb가 더 클 경우만 진행
public class 죽음의비_22944 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int H; // 현재 체력
    private static int D; // 우산 내구도
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static Node start;
    private static char[][] map;

    private static class Node {
        int y, x;
        int hp;
        int umb;
        int dist;

        public Node(int y, int x, int hp, int umb, int dist) {
            this.y = y;
            this.x = x;
            this.hp = hp;
            this.umb = umb;
            this.dist = dist;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static int solve() {
        Queue<Node> queue = new LinkedList<>();
        int[][] distH = new int[N][N];

        queue.add(start);
        distH[start.y][start.x] = start.hp;

        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node here = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = here.y + dy[i];
                int nx = here.x + dx[i];
                if(!isRange(ny, nx)) continue;

                if(map[ny][nx] == 'E') {
                    res = Math.min(res, here.dist + 1);
                    continue;
                }

                int nhp = here.hp;
                int numb = (map[ny][nx] == 'U') ? D : here.umb;

                if(numb > 0) numb -= 1;
                else nhp -= 1;

                if(nhp > 0 && distH[ny][nx] < nhp + numb) {
                    distH[ny][nx] = nhp + numb;
                    queue.add(new Node(ny, nx, nhp, numb, here.dist + 1));
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 'S') start = new Node(i, j, H, 0, 0);
            }
        }
        System.out.println(solve());
    }
}
