package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Guess_1248 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] picked;
    private static char[][] guess;

    private static boolean isSame(int i, int index, int pSum) {
        if(guess[i][index] == '-') {
            if(pSum >= 0) return false;
        }
        else if(guess[i][index] == '+') {
            if(pSum <= 0) return false;
        }
        else if(guess[i][index] == '0') {
            if(pSum != 0) return false;
        }
        return true;
    }

    private static boolean isOk(int index) {
        int pSum = 0;
        for(int i = index; i >= 0; i--) {
            pSum += picked[i];
            if(!isSame(i, index, pSum)) return false;
        }
        return true;
    }

    private static boolean solve(int index) {
        if(index == N) {
            return true;
        }

        for(int i = -10; i <= 10; i++) {
            picked[index] = i;
            if(isOk(index) && solve(index + 1)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        char[] S = br.readLine().toCharArray();

        int sLen = 0;
        guess = new char[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = i; j < N; j++) {
                guess[i][j] = S[sLen++];
            }
        }

        picked = new int[N];
        solve(0);
        Arrays.stream(picked).forEach(s -> System.out.print(s + " "));
    }
}
