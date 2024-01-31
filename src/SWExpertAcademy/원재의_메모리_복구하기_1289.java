package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 원재의_메모리_복구하기_1289 {
    private static BufferedReader br;
    private static char[] bit;

    private static int solve() {
        int cnt = bit[0] == '0' ? -1 : 0;

        int i = 0;
        while(i < bit.length) {
            int j = i + 1;
            while(j < bit.length && bit[i] == bit[j]) j += 1;

            cnt += 1;
            i = j;
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            bit = br.readLine().toCharArray();
            System.out.println("#" + t + " " + solve());
        }
    }

}