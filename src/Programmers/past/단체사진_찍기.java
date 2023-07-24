package Programmers.past;

import java.util.*;

// Pass(30m)
class 단체사진_찍기 {
    private char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private int N = 8;
    private HashMap<Character, Integer> picked;
    private int cnt = 0;
    private char[][] datas;

    public boolean isOk() {

        for(char[] data : datas) {
            int a = picked.get(data[0]);
            int b = picked.get(data[1]);
            int diff = (int)(data[3] - '0');

            if(data[2] == '=') {
                if(Math.abs(a - b) - 1 != diff) return false;
            }
            else if(data[2] == '>') {
                if(Math.abs(a - b) - 1 <= diff) return false;
            }
            else {
                if(Math.abs(a - b) - 1 >= diff) return false;
            }

        }

        return true;
    }

    public void solve(int index, boolean[] check) {
        if(index == N) {
            if(isOk()) cnt += 1;
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!check[i]) {
                check[i] = true;
                picked.put(friends[i], index);
                solve(index + 1, check);
                check[i] = false;
            }
        }
    }

    public int solution(int n, String[] data) {
        picked = new HashMap<>();

        datas = new char[n][4];
        for(int i = 0; i < n; i++) {
            datas[i][0] = data[i].charAt(0);
            datas[i][1] = data[i].charAt(2);
            datas[i][2] = data[i].charAt(3);
            datas[i][3] = data[i].charAt(4);
        }

        solve(0, new boolean[N]);

        return cnt;
    }
}