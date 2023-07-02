package Programmers;

import java.util.*;

// F
public class 최고의_집합 {

    public int[] solution(int n, int s) {
        if(n > s) return new int[]{-1};

        int[] answer = new int[n];

        int remain = s % n;
        for(int i = answer.length - 1; i >= 0; i--) {
            answer[i] = s / n + (remain > 0 ? 1 : 0);
            remain -= 1;
        }

        return answer;
    }
}
