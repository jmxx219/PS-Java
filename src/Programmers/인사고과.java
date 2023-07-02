package Programmers;

import java.util.*;

// F
public class 인사고과 {
    public int[] wanho;

    public int solve(int[][] scores) {
        int answer = 1;
        int myPoint = wanho[0] + wanho[1];
        int maxScore = 0;

        for(int[] score : scores) {
            if(score[1] < maxScore) {
                if(score.equals(wanho)) return -1;
            }
            else {
                maxScore = Math.max(score[1], maxScore);
                if(myPoint < score[0] + score[1]) answer++;
            }
        }
        return answer;
    }

    public int solution(int[][] scores) {
        wanho = scores[0];

        Arrays.sort(scores, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });

        return solve(scores);
    }
}