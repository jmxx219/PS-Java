package Programmers.Etc;

import java.util.*;

// Pass(40m)
class 전력망을_둘로_나누기 {
    public boolean[][] connect;

    public int solve(int start) {
        int cnt = 1;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] check = new boolean[connect.length];

        queue.add(start);
        check[start] = true;

        while(!queue.isEmpty()) {
            int here = queue.poll();
            for(int i = 1; i < check.length; i++) {
                if(!connect[here][i]) continue;
                if(!check[i]) {
                    check[i] = true;
                    cnt += 1;
                    queue.add(i);
                }
            }
        }

        return cnt;
    }

    public int solution(int n, int[][] wires) {
        connect = new boolean[n + 1][n + 1];

        for(int[] wire : wires) {
            connect[wire[0]][wire[1]] = true;
            connect[wire[1]][wire[0]] = true;
        }

        int answer = -1;
        for(int i = 0; i < n - 1; i++) {
            connect[wires[i][0]][wires[i][1]] = false;
            connect[wires[i][1]][wires[i][0]] = false;

            int v2 = solve(wires[i][1]);
            int v1 = n - v2;

            if(answer == -1 || answer > Math.abs(v1 - v2)) {
                answer = Math.abs(v1 - v2);
            }

            connect[wires[i][0]][wires[i][1]] = true;
            connect[wires[i][1]][wires[i][0]] = true;
        }


        return answer;
    }
}