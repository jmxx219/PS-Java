package Programmers.Etc;

public class 점프와_순간_이동 {
    public int solve(int N) {
        int cnt = 0;
        while(N != 0) {
            if(N % 2 == 1) {
                N -= 1;
                cnt += 1;
            }
            N /= 2;
        }
        return cnt;

    }
    public int solution(int n) {
        return solve(n);
    }
}
