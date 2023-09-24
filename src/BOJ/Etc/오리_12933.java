package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오리_12933 {
    private static BufferedReader br;
    private static char[] D;
    private static final char[] sound = {'q', 'u', 'a', 'c', 'k'};

    public static boolean isEnd() {
        boolean end = true;
        for (int i = 0; i < D.length; i++) {
            if(D[i] != ' ') end = false;
        }
        return end;
    }

    public static int solve() {
        if(D.length % 5 != 0 || D[0] != 'q') return -1;

        int cnt = 0;
        int index = 0;
        while (true) {
            for (int i = 0; i < D.length; i++) {
                if(D[i] == sound[index]) {
                    index = (index + 1) % 5;
                    D[i] = ' ';
                }
            }
            if(index != 0) return -1;
            cnt += 1;
            if(isEnd()) break;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        D = br.readLine().toCharArray();
        System.out.println(solve());
    }
}
