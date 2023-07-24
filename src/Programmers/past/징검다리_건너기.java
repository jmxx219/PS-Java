package Programmers.past;

import java.util.*;

// Pass(40m)
class 징검다리_건너기 {

    static class Stone implements Comparable<Stone> {
        int index;
        int size;
        public Stone(int index, int size) {
            this.index = index;
            this.size = size;
        }

        @Override
        public int compareTo(Stone s) {
            return s.size - this.size;
        }

    }

    public int solution(int[] stones, int k) {
        PriorityQueue<Stone> pq = new PriorityQueue<>();

        for(int i = 0; i < k - 1; i++) {
            pq.add(new Stone(i, stones[i]));
        }

        int answer = Integer.MAX_VALUE;
        for(int i = k - 1; i < stones.length; i++) {
            pq.add(new Stone(i, stones[i]));
            while(pq.peek().index < i - k + 1) {
                pq.poll();
            }
            if(answer > pq.peek().size) answer = pq.peek().size;
        }


        return answer;
    }
}