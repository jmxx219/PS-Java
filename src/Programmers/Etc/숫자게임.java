package Programmers.Etc;

import java.util.*;

public class 숫자게임 {
    public PriorityQueue<Integer> pA;
    public PriorityQueue<Integer> pB;

    public int solve() {
        int cnt = 0;
        while(!pA.isEmpty() && !pB.isEmpty()) {
            int a = pA.peek();
            int b = pB.peek();
            if(a < b) {
                pA.poll();
                pB.poll();
                cnt += 1;
            }
            else {
                pB.poll();
            }
        }

        return cnt;
    }
    public int solution(int[] A, int[] B) {
        pA = new PriorityQueue<>();
        pB = new PriorityQueue<>();

        for(int i = 0; i < A.length; i++) {
            pA.add(A[i]);
            pB.add(B[i]);
        }

        return solve();
    }
}
