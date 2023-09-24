package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 에너그램_6443 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static char[] picked;
    private static StringBuilder sb;
    
    private static void solve(int index, int[] alpha) {
        if(index == picked.length) {
            for (int i = 0; i < picked.length; i++) {
                sb.append(picked[i]);
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < alpha.length; i++) {
            if(alpha[i] > 0) {
                alpha[i] -= 1;
                picked[index] = (char) (i + 'a');
                solve(index + 1, alpha);
                alpha[i] += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        while (N-- > 0) {
            int[] alpha = new int[26];
            int len = 0;
            for(char c: br.readLine().toCharArray()){
                alpha[c - 'a'] += 1;
                len += 1;
            }
            picked = new char[len];
            solve(0,  alpha);
        }
        System.out.println(sb);

    }
}
