package Programmers.past;

import java.util.*;

// Pass(80ms)
class 프렌즈4블록 {
    private final int[] dy = {0, 1, 1};
    private final int[] dx = {1, 0, 1};
    private char[][] block;


    public void move(int m, int n) {
        for(int j = 0; j < n; j++) {
            for(int i = m - 1; i >= 0; i--) {
                if(block[i][j] == 'X') {
                    int curr = i;
                    while(curr >= 0) {
                        if(block[curr][j] != 'X') break;
                        curr -= 1;
                    }
                    if(curr == -1) continue;
                    block[i][j] = block[curr][j];
                    block[curr][j] = 'X';
                }
            }
        }
    }

    public int count(int i, int j, boolean[][] check) {
        int cnt = 0;
        for(int k = 0; k < 3; k++) {
            if(!check[i + dy[k]][j + dx[k]]) {
                cnt += 1;
                check[i + dy[k]][j + dx[k]] = true;
            }
        }

        if(!check[i][j]) {
            check[i][j] = true;
            cnt += 1;
        }

        return cnt;
    }

    public boolean isOk(int i, int j) {
        char c = block[i][j];
        return c == block[i + dy[0]][j + dx[0]] &&
                c == block[i + dy[1]][j + dx[1]] &&
                c == block[i + dy[2]][j + dx[2]];
    }

    public int find(int m, int n) {
        boolean[][] check = new boolean[m][n];
        int cnt = 0;
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                if(block[i][j] == 'X') continue;
                if(isOk(i, j)) {
                    cnt += count(i, j, check);
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(check[i][j]) block[i][j] = 'X';
            }
        }

        return cnt;
    }

    public int solution(int m, int n, String[] board) {
        block = new char[m][n];
        for(int i = 0; i < m; i++) {
            block[i] = board[i].toCharArray();
        }

        int answer = 0;
        int cnt = -1;
        while(cnt != 0) {
            cnt = find(m, n);
            move(m, n);
            answer += cnt;
        }
        return answer;
    }
}