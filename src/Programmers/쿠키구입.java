package Programmers;

public class 쿠키구입 {
    public static int solution(int[] cookie) {
        int res = 0;
        for(int i = 0; i < cookie.length - 1; i++) {
            int a = i;
            int aSum = cookie[a];
            int b = i + 1;
            int bSum = cookie[b];
            while (true) {
                if(aSum == bSum && res < bSum) res = bSum;

                if(a > 0 && aSum <= bSum) aSum += cookie[--a];
                else if(b < cookie.length - 1 && aSum >= bSum) bSum += cookie[++b];
                else break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,1,2,3}));
        System.out.println(solution(new int[] {1, 2, 4, 5}));
        System.out.println(solution(new int[] {0, 3, 3, 3, 3, 3, 5, 5, 5, 6}));
    }
}
