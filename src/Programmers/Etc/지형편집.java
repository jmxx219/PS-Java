package Programmers.Etc;

import java.util.Arrays;

public class 지형편집 {
    public int N;
    public long minHeight = 0;
    public long maxHeight = 0;

    public long calc(long height, int[][] land, int P, int Q) {
        long add = 0;
        long remove = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                if(land[i][j] < height) add += (height - land[i][j]);
                else if(land[i][j] > height) remove += (land[i][j] - height);
            }
        }
        return add * P + remove * Q;
    }

    public long solve(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;

        long left = minHeight;
        long right = maxHeight;
        while(left <= right) {
            long mid = (left + right) / 2;

            long costLeft = calc(mid, land, P, Q);
            long costRight = calc(mid + 1, land, P, Q);

            if(costLeft <= costRight) {
                answer = costLeft;
                right = mid - 1;
            }
            else {
                answer = costRight;
                left = mid + 1;
            }
        }
        return answer;
    }

    public long solution(int[][] land, int P, int Q) { // 1. 이분탐색
        N = land.length;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                if(maxHeight < land[i][j]) maxHeight = land[i][j];
                if(minHeight > land[i][j]) minHeight = land[i][j];
            }
        }

        return solve(land, P, Q);
    }

    public long solution2(int[][] land, int P, int Q) { // 2. 블럭 높이를 일차원 배열로 만들어서 정렬 후 계산
        int[] blocks = new int[land.length*land.length];
        for(int i=0;i<land.length;i++) {
            System.arraycopy(land[i], 0, blocks, i*land.length, land.length);
        }
        Arrays.sort(blocks);

        long total = 0L; // 블럭 전체 갯수
        for(int i = 0; i < blocks.length; i++) total += blocks[i];

        long prevSum = 0L;
        long answer = Long.MAX_VALUE;
        int prevN = -1;
        for(int i = 0; i <blocks.length; i++) {
            if(prevN!=blocks[i]) {
                long up = (long) blocks[i] * i - prevSum;
                long down = total - prevSum - (long)(blocks.length - i) * blocks[i];
                long cost = up * P + down * Q;
                if(cost < answer) answer = cost;
                prevN = blocks[i];
            }
            prevSum += blocks[i];
        }
        return answer;
    }
}
