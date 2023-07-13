package Programmers.Etc;

public class 방문길이 {
    public char[] operator;
    public static final int[] dy = {-1, 0, 0, 1}; // U, R, L, D
    public static final int[] dx = {0, 1, -1, 0};
    public static final int N = 10;

    public int move(char op) {
        if(op == 'U') return 0;
        else if(op == 'R') return 1;
        else if(op == 'L') return 2;
        return 3;
    }

    public boolean isRange(int y, int x) {
        return 0 <= y && y <= N && 0 <= x && x <= N;
    }

    public int solve() {
        int cnt = 0;
        boolean[][][] visited = new boolean[N + 1][N + 1][4];

        int y = 5;
        int x = 5;
        for(char op : operator) {
            int i = move(op);
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!isRange(ny, nx)) continue;

            if(!visited[y][x][i] && !visited[ny][nx][3-i]) {
                cnt += 1;
                visited[y][x][i] = true;
                visited[ny][nx][3-i] = true;
            }

            y = ny;
            x = nx;
        }

        return cnt;
    }

    public int solution(String dirs) {
        operator = dirs.toCharArray();
        return solve();
    }
}
