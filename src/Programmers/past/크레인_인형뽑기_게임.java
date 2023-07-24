package Programmers.past;

import java.util.*;

// Pass(15m)
class 크레인_인형뽑기_게임 {
    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        int M = board[0].length;
        int[] toy = new int[M];

        for(int i = N - 1; i >= 0; i--) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] != 0) {
                    toy[j] = i;
                }
            }
        }

        int answer = 0;
        Stack<Integer> crane = new Stack<>();
        for(int move : moves) {
            if(toy[move - 1] >= N) continue;

            int curr = board[toy[move - 1]][move - 1];
            if(!crane.isEmpty() && crane.peek() == curr) {
                answer += 2;
                crane.pop();
            }
            else crane.add(curr);
            toy[move - 1] += 1;
        }

        return answer;
    }
}