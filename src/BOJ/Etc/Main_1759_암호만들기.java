package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int L;
    private static int C;
    private static char[] ch;
    private static char[] picked;

    public static void solve(int start, int index, boolean[] check) {
        if(index == L) {
            int a = 0, b = 0;
            for(char c : picked) {
                if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') a++;
                else b++;
            }
            if(a >= 1 && b >= 2) {
                System.out.println(String.valueOf(picked));
            }
            return;
        }

        for(int i = start; i < C; i++) {
            if(check[i]) continue;
            check[i] = true;
            picked[index] = ch[i];
            solve(i + 1, index + 1, check);
            check[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ch = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) ch[i] = st.nextToken().charAt(0);
        Arrays.sort(ch);
        picked = new char[L];
        solve(0, 0, new boolean[C]);
    }
}
