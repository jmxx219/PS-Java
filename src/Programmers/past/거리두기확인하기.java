package Programmers.past;

import java.util.*;

public class 거리두기확인하기 {
    public static final int[] dy = {1, -1, 0, 0};
    public static final int[] dx = {0, 0, 1, -1};

    public boolean isRange(int y, int x){
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }

    // O: 빈 테이블, X: 파티션
    public boolean isOk(int y, int x, char[][] place) {
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!isRange(ny, nx) || place[ny][nx] == 'X') continue;
            if(place[ny][nx] == 'P') return false;

            for(int j = 0; j < 4; j++) {
                int nny = ny + dy[j];
                int nnx = nx + dx[j];
                if(nny == y && nnx == x) continue;
                if(!isRange(nny, nnx)) continue;
                if(place[nny][nnx] == 'P') return false;
            }
        }

        return true;
    }

    public int solve(String[] p) {
        char[][] place = new char[5][5];
        for(int i = 0; i < 5; i++) {
            place[i] = p[i].toCharArray();
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(place[i][j] == 'P' && !isOk(i, j, place)){
                    return 0;
                }
            }
        }

        return 1;
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i = 0; i < 5; i++) {
            answer[i] = solve(places[i]);
        }
        return answer;
    }
}