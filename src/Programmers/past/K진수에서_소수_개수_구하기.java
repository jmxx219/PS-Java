package Programmers.past;

import java.util.*;

public class K진수에서_소수_개수_구하기 {
    public String toConvert(int n, int k) {
        List<Integer> nums = new ArrayList<>();

        while(n != 0) {
            nums.add(n % k);
            n /= k;
        }

        String s = "";
        for(int i = nums.size() - 1; i >= 0; i--) {
            s += String.valueOf(nums.get(i));
        }
        return s;
    }

    public boolean isPrime(long x) {
        if(x <= 1) return false;

//        for(long i = 2; i * i <= x; i++) { // i -> long으로
        for(int i = 2; i <= (int) Math.sqrt(x); i++) {
            if(x % i == 0) return false;
        }
        return true;
    }

    public int findPrime(String kB) {
        String[] binary = kB.split("0");
        int cnt = 0;

        for(String b : binary) {
            if(!b.equals("")) {
                long x = Long.parseLong(b); // Long
                if(isPrime(x)) cnt += 1;
            }
        }

        return cnt;
    }

    public int solution(int n, int k) {
        String kB = toConvert(n, k);
        return findPrime(kB);
    }
}