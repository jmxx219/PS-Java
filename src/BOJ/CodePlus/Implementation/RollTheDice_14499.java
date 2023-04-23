package BOJ.CodePlus.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RollTheDice_14499 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int DiceY;
    private static int DiceX;
    private static int K;
    private static int[][] map;
    private static Dice currDice;
    private static int[] diceValue;
    private static final int[] dx = {0, 0, -1, 1}; // 오, 왼, 위, 아래
    private static final int[] dy = {1, -1, 0, 0};

    public static class Dice {
        public int top, up, down, left, right, bottom;
        public Dice(int top, int up, int down, int left, int right, int bottom) {
            this.top = top;
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
            this.bottom = bottom;
        }
    }

    public static Dice findNextDice(int k) {
        int t = currDice.top;
        int u = currDice.up;
        int d = currDice.down;
        int l = currDice.left;
        int r = currDice.right;
        int b = currDice.bottom;

        if(k == 1) return new Dice(l, u, d, b, t, r); // 오른쪽
        else if(k == 2) return new Dice(r, u, d, t, b, l); // 왼쪽
        else if(k == 3) return new Dice(u, b, t, l, r, d); // 위쪽
        return new Dice(d, t, b, l, r, u); // 아래쪽
    }

    public static boolean isRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static int roll(int k) {
        int nx = DiceX + dx[k - 1];
        int ny = DiceY + dy[k - 1];

        if(!isRange(nx, ny)) return -1 ;

        currDice = findNextDice(k);
        DiceX = nx;
        DiceY = ny;

        if(map[nx][ny] == 0) {
            map[nx][ny] = diceValue[currDice.bottom];
        }
        else {
            diceValue[currDice.bottom] = map[nx][ny];
            map[nx][ny] = 0;
        }

        return diceValue[currDice.top];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DiceX = Integer.parseInt(st.nextToken());
        DiceY = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        diceValue = new int[6];
        currDice =  new Dice(0, 1, 2, 3, 4, 5);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int top = roll(Integer.parseInt(st.nextToken()));
            if (top != -1) System.out.println(top);
        }
    }
}
