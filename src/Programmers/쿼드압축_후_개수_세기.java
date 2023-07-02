package Programmers;

// 5m(Pass)
public class 쿼드압축_후_개수_세기 {
    private int[] answer;
    private int N;

    public boolean isSameAll(int y, int x, int size, int[][] arr) {
        for(int i = y; i < y + size; i++) {
            for(int j = x; j < x + size; j++) {
                if(arr[y][x] != arr[i][j]) return false;
            }
        }
        return true;
    }

    private void solve(int y, int x, int size, int[][] arr) {
        if(isSameAll(y, x, size, arr)) {
            answer[arr[y][x]] += 1;
            return;
        }

        int nSize = size / 2;
        solve(y, x, nSize, arr);
        solve(y, x + nSize, nSize, arr);
        solve(y + nSize, x, nSize, arr);
        solve(y + nSize, x + nSize, nSize, arr);
    }

    public int[] solution(int[][] arr) {
        N = arr.length;
        answer = new int[2];
        solve(0, 0, N, arr);
        return answer;
    }
}