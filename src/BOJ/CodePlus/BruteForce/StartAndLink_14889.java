package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StartAndLink_14889 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] startLink;

    private static int compute(List<Integer> start, List<Integer> link) {
        int startSum = 0;
        int linkSum = 0;
        for(int i = 0; i < N / 2; i++) {
            for(int j = 0; j < N / 2; j++) {
                if(i == j) continue;;
                startSum += startLink[start.get(i)][start.get(j)];
                linkSum += startLink[link.get(i)][link.get(j)];
            }
        }
        return Math.abs(startSum - linkSum);
    }

    public static int solve() {
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < (1<<N); i++) { // bitMask
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();
            for(int j = 0; j < N; j++) {
                if((i & (1 << j)) != 0) {
                    start.add(j);
                } else {
                    link.add(j);
                }
            }

            if(start.size() == N / 2) {
                res = Math.min(res, compute(start, link));
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        startLink = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                startLink[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
