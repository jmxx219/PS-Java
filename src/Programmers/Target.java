package Programmers;

import java.util.Arrays;

// 양궁 대회
public class Target {
    public static int[] answer;
    public static int[] ryan;
    public static int[] apeach;
    public static final int SIZE = 11;
    public static int maxDiff = 0;

    public static void compute() {
        int a = 0;
        int r = 0;

        for (int i = 0; i < SIZE; i++) {
            if(apeach[i] == 0 && ryan[i] == 0) continue;

            if(apeach[i] < ryan[i]) r += (10 - i);
            else a += (10 - i);

        }

        int diff = r - a;
        if(diff <= 0) return;

        if (maxDiff < diff) {
            maxDiff = diff;
            answer = ryan.clone();
        }
        else if (maxDiff == diff) {
            for (int i = SIZE - 1; i >= 0 ; i--) { // 문제점 3. 점수 차이가 같을 경우, 낮은 점수가 많을 때만 갱신해야 함
                if(ryan[i] != answer[i])  {
                    if(ryan[i] > answer[i]) answer = ryan.clone();
                    break;
                }
            }
        }
    }

    public static void solve(int N, int shoot, int start) {
        if(shoot == N) {
            compute();
            return;
        }

        for (int i = start; i < SIZE; i++) { // 문제점 1. 인덱스를 계속 0부터 시작했음 -> start 부터
            if(ryan[i] > apeach[i]) continue; // 문제점 2. 라이언이 어치피보다 많이 쐇을 경우 해당 점수에 더 쏠 필요가 없음 -> 중단
            ryan[i] += 1;
            solve(N, shoot + 1, i);
            ryan[i] -= 1;
        }
    }


    public static int[] solution(int n, int[] info) {
        ryan = new int[SIZE];
        apeach = info.clone();
        maxDiff = 0;

        solve(n, 0, 0);

        if(maxDiff == 0) return new int[] {-1};

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
        System.out.println(Arrays.toString(solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1})));
        System.out.println(Arrays.toString(solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3})));
    }
}
