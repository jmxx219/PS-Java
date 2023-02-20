package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PieceOfPaper_14391 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] paper;

    private static int sumWidth(int S) {
        int widthSum = 0;
        for(int i = 0; i < N; i++) { // 가로 연속
            int currSum = 0;
            for(int j = 0; j < M; j++) {
                int k = i * M + j;
                if((S & (1<<k)) == 0) { // 해당 인덱스는 가로
                    currSum = currSum * 10 + paper[i][j];
                }
                else { // 해당 인덱스는 세로
                    widthSum += currSum;
                    currSum = 0;
                }
            }
            widthSum += currSum;
        }
        return widthSum;
    }

    private static int sumHeight(int S) {
        int heightSum = 0;
        for(int j = 0; j < M; j++) { // 세로 연속
            int currSum = 0;
            for(int i = 0; i < N; i++) {
                int k = i * M + j;
                if((S & (1<<k)) != 0) { // 해당 인덱스는 세로
                    currSum = currSum * 10 + paper[i][j];
                }
                else { // 해당 인덱스는 세로
                    heightSum += currSum;
                    currSum = 0;
                }
            }
            heightSum += currSum;
        }
        return heightSum;
    }

    private static int solve() {
        int res = 0;

        // 0 : 가로, 1: 세로
         for(int s = 0; s < (1<<(N*M)); s++) {
             int totalSum = sumWidth(s) + sumHeight(s);
             res = Math.max(res, totalSum);
         }

         return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        for(int i = 0; i < N; i++) {
            paper[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solve());
    }
}
