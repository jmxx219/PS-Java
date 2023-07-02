package Programmers;

// 15m(Pass)
class 피로도 {
    // k: 현재 피로도
    // dungeons: 던전 별, ["최소 필요 피로도", "소모 피로도"]
    // return: 유저가 탐험할수 있는 최대 던전 수
    private int[] picked;
    private int N;
    private int answer;
    private int K;

    private int calc(int[][] dungeons) {
        int k = K;
        for(int i = 0; i < N; i++) {
            int required = dungeons[picked[i]][0];
            int reduce = dungeons[picked[i]][1];
            if(k >= required) {
                k -= reduce;
            }
            else return i;
        }
        return N;
    }

    private void solve(int index, boolean[] check, int[][] dungeons) {
        if(index == N) {
            answer = Math.max(calc(dungeons), answer);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!check[i]) {
                check[i] = true;
                picked[index] = i;
                solve(index + 1, check, dungeons);
                check[i] = false;
            }
        }
    }

    public int solution(int k, int[][] dungeons) {
        K = k;
        N = dungeons.length;
        picked = new int[N];
        answer = -1;

        solve(0, new boolean[N], dungeons);

        return answer;
    }
}