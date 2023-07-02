package Programmers;

import java.util.PriorityQueue;

// F
public class 디펜스_게임 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < enemy.length; i++) {
            pq.add(enemy[i]);
            if(pq.size() > k) {
                int x = pq.poll();
                if(n < x) {
                    return i;
                }
                n -= x;
            }
        }

        return enemy.length;
    }
}