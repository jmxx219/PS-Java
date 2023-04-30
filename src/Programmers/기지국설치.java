package Programmers;

public class 기지국설치 {
    public static int spread;

    public int solution(int n, int[] stations, int w) {
        spread = 2 * w + 1;
        int answer = 0;

        int left = 1;
        int right = stations[0] - w - 1;
        for(int i = 0; i < stations.length; i++) {
            int cnt = right - left + 1;
            if(cnt > 0) {
                int install = cnt / spread + (cnt % spread == 0 ? 0 : 1);
                answer += install;
            }
            left = stations[i] + w + 1;
            right = (i + 1 < stations.length) ? stations[i + 1] - w - 1 : n;
        }

        if(left <= right) {
            int cnt = right - left + 1;
            int install = cnt / spread + (cnt % spread == 0 ? 0 : 1);
            answer += install;
        }

        return answer;
    }
}
