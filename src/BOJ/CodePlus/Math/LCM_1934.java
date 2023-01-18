package BOJ.CodePlus.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCM_1934 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;

    public static int GCD(int a, int b) {
        if(b == 0){
            return a;
        }
        return GCD(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        while(T > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int g = GCD(a, b);
            int LCM = g * (a / g) * (b / g); // A * B = GCD * LCM
            System.out.println(LCM);
            T -= 1;
        }
    }
}
