package Programmers.Etc;

import java.util.*;

// Pass(10m)
class 야근지수 {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int w : works) pq.add(w);

        for(int i = 0; i < n; i++) {
            if(pq.peek() == 0) return 0;
            pq.add(pq.poll() - 1);
        }

        long answer = 0;
        while(!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}