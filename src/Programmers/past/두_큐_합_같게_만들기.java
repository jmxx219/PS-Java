package Programmers.past;

import java.util.*;

class 두_큐_합_같게_만들기 {

    public int solve_queue(int[] queue1, int[] queue2) {
        int ans = 0;
        int N = queue1.length;
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        long q1Sum = 0, target = 0;

        for(int i = 0; i < N; i++) {
            target += queue1[i] + queue2[i];
            q1Sum += queue1[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        if(target % 2 != 0) return -1;

        target /= 2;
        while(true) {
            if(ans > N * 4) return -1;

            if(q1Sum == target) break;
            else if(q1Sum > target) {
                q1Sum -= q1.peek();
                q2.add(q1.poll());
            }
            else {
                q1Sum += q2.peek();
                q1.add(q2.poll());
            }
            ans++;
        }
        return ans;
    }

    public int solve_twoPointer(int[] queue1, int[] queue2) {
        int N = queue1.length;
        int[] A = new int[2 * N];

        long total = 0;
        long q1Sum = 0;
        for(int i = 0; i < N; i++) {
            total += queue1[i] + queue2[i];
            q1Sum += queue1[i];
            A[i] = queue1[i];
            A[i + N] = queue2[i];
        }
        if(total % 2 != 0) return -1;

        total /= 2;

        int ans = 0;
        int startP = 0;
        int endP = N - 1;
        while (q1Sum != total) {
            if (ans > 4 * N) return -1;

            if (q1Sum < total) {
                endP++;
                if (endP < 2 * N) {
                    q1Sum += A[endP];
                }
            }
            else if (q1Sum > total) {
                if (startP <= endP) {
                    q1Sum -= A[startP];
                    startP++;
                }
                else return -1;
            }
            ans += 1;
        }

        return ans;
    }

    public int solution(int[] queue1, int[] queue2) {
//        return solve_queue(queue1, queue2);
        return solve_twoPointer(queue1, queue2);
    }
}