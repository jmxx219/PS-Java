package BOJ.CodePlus.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberOfPapers_1780 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] paper;
    private static int[] answer = new int[3];

    public static boolean isSameRange(int y, int x, int size) {
        for (int i = y; i < y + size; i += 1) {
            for (int j = x; j < x + size; j += 1) {
                if(paper[i][j] != paper[y][x]) return false;
            }
        }
        answer[paper[y][x] + 1] += 1;
        return true;
    }

    public static void solve(int y, int x, int size) {
        if(isSameRange(y, x, size)) return;

        int nSize = size / 3;
        for (int i = y; i < y + size; i += nSize) {
            for (int j = x; j < x + size; j += nSize) {
                solve(i, j, nSize);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            paper[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solve(0, 0, N);

        for (int i = 0; i < 3; i++) {
            System.out.println(answer[i]);
        }

    }
}
