package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teaching_1062 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int[] words;
    private static boolean[] learn = new boolean[26];
    private static final char[] basic = {'a', 'n', 't', 'i', 'a'};

    private static boolean isOk(int index) {
        boolean ok = true;
        for (int i = 0; i < 5; i++) {
            if(index == basic[i] - 'a') ok = false;
        }
        return ok;
    }

    private static int count(int learnAlpha) {
        int cnt = 0;

        for(int word : words) {
            if((word & ((1 << 26) - 1 - learnAlpha)) == 0) { // 배우지 않은 알파벳이 단어에 있는지 검사
                cnt += 1;
            }
        }

        return cnt;
    }

    private static int solve(int index, int k, int mask) { // mask : 배운 알파벳을 비트마스크 형태로 저장
        if(k < 0) return 0;
        if(index == 26) return count(mask);

        int ans = 0;

        learn[index] = true;
        ans = Math.max(ans, solve(index + 1, k - 1, mask | (1 << index))); // 배우는 경우

        learn[index] = false;
        if(isOk(index)) {
            ans = Math.max(ans, solve(index + 1, k, mask)); // 배우지 않는 경우
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 남극 단어
        K = Integer.parseInt(st.nextToken()); // 글자

        words = new int[N];
        for (int i = 0; i < N; i++) {
            for(char x : br.readLine().toCharArray()) {
                words[i] |= (1 << (x - 'a')); // 단어의 각 알파벳을 비트 마스크로 저장
            }
        }

        System.out.println(solve(0, K, 0));
    }
}