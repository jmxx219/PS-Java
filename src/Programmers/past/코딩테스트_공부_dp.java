package Programmers.past;

import java.util.Arrays;

// F
class 코딩테스트_공부_dp {
    private static final int INF = 300;
    private int maxAlp;
    private int maxCop;

    private int solve(int alp, int cop, int[][] problems) {
        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[alp][cop] = 0;

        for(int i = alp; i <= maxAlp; i++) {
            for(int j = cop; j <= maxCop; j++) {
                if(i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if(j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for(int[] p : problems) {
                    if(p[0] <= i && p[1] <= j) {
                        int nextAlp = Math.min(maxAlp, i + p[2]);
                        int nextCop = Math.min(maxCop, j + p[3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + p[4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }

    public int solution(int alp, int cop, int[][] problems) {
        maxAlp = -1;
        maxCop = -1;
        for (int[] problem : problems) {
            if(problem[0] > maxAlp) maxAlp = problem[0];
            if(problem[1] > maxCop) maxCop = problem[1];
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        return solve(alp, cop, problems);
    }
}