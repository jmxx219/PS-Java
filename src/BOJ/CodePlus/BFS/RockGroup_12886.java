package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RockGroup_12886 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int A;
    private static int B;
    private static int C;
    private static final int MAX = 1500;
    private static int SUM;

    public static class Rock {
        public int a;
        public int b;
        public int cost;

        public Rock(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    public static int bfs() {
        Queue<Rock> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[MAX + 1][MAX + 1];

        queue.add(new Rock(A, B, 0));
        isVisited[A][B] = true;


        while (!queue.isEmpty()) {
            Rock here = queue.poll();

            int a = here.a;
            int b = here.b;
            int c = SUM - (here.a + here.b);

            if(a == b && b == c) return 1;

            if(a != b) {
                if(a < b && !isVisited[a + a][b - a]){
                    queue.add(new Rock(a + a, b - a, here.cost + 1));
                    isVisited[a + a][b - a] = true;
                }
                else if(a > b && !isVisited[a - b][b + b]) {
                    queue.add(new Rock(a - b, b + b, here.cost + 1));
                    isVisited[a - b][b + b] = true;
                }
            }

            if(b != c) {
                if(b < c && !isVisited[a][b + b]){
                    queue.add(new Rock(a, b + b, here.cost + 1));
                    isVisited[a][b + b] = true;
                }
                else if(b > c && !isVisited[a][b - c]) {
                    queue.add(new Rock(a, b - c, here.cost + 1));
                    isVisited[a][b - c] = true;
                }
            }

            if(c != a) {
                if(a < c && !isVisited[a + a][b]){
                    queue.add(new Rock(a + a, b, here.cost + 1));
                    isVisited[a + a][b] = true;
                }
                else if(a > c && !isVisited[a - c][b]) {
                    queue.add(new Rock(a - c, b, here.cost + 1));
                    isVisited[a - c][b]= true;
                }
            }

        }

        return 0;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        SUM = A + B + C;

        if(SUM % 3 != 0){
            System.out.println(0);
        }
        else {
            System.out.println(bfs());
        }
    }
}
