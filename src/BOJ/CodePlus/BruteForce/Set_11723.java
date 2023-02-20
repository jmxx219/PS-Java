package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Set_11723 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int M;
    private static int S;
    private static StringBuilder sb;

    private static void bitMask(String op, int n) {
        switch (op) {
            case "add":
                S = S | 1 << n;
                break;
            case "remove":
                S = S & ~(1 << n);
                break;
            case "check":
                sb.append((S & 1 << n) == 0 ? "0\n" : "1\n");
                break;
            case "toggle":
                S = S ^ 1 << n;
                break;
            case "all":
                S = S | ~0;
                // S = (1 << 21) - 1;
                break;
            case "empty":
                S = S & 0;
                // S = 0;
                break;
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
         for(int i = 0; i < M ; i++) {
             st = new StringTokenizer(br.readLine());
             String op = st.nextToken();
             int n = 0;
             if(st.hasMoreTokens()) {
                 n = Integer.parseInt(st.nextToken());
             }
             bitMask(op, n);
         }
         System.out.println(sb);
         br.close();
    }
}
