package Programmers;

public class 스티커모으기 {
    public int N;

    public int solve(int sticker[]) {
        int[] dp1 = new int[N]; // 0번째 뜯을 때
        int[] dp2 = new int[N]; // 0번째 안 뜯을 때

        dp1[0] = sticker[0];
        dp1[1] = dp1[0];
        for(int i = 2; i < N - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        dp2[1] = sticker[1];
        for(int i = 2; i < N; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        return Math.max(dp1[N - 2], dp2[N - 1]);
    }

    public int solution(int sticker[]) {
        N = sticker.length;
        if(N == 1) return sticker[0];
        return solve(sticker);
    }
}
