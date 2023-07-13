package Programmers.Etc;

import java.util.HashSet;
import java.util.Set;

import java.util.*;

// 20m(Pass)
class 연속부분수열_합의개수 {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int N = elements.length;

        int[][] dp = new int[N][N];
        for(int j = 0; j < N ; j++) {
            dp[0][j] = elements[j];
            set.add(dp[0][j]);
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int next = (j == N - 1) ? dp[i - 1][0] : dp[i - 1][j + 1];
                dp[i][j] = dp[0][j] + next;
                set.add(dp[i][j]);
            }
        }

        return set.size();
    }
}