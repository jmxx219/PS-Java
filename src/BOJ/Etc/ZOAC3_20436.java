package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ZOAC3_20436 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    private static final String vowel = "yuiophjklbnm";
    private static String SL;
    private static String SR;

    public static int[] getPoint(String c) {
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length(); j++) {
                if(keyboard[i].charAt(j) == c.charAt(0)) return new int[] {i, j};
            }
        }
        return null;
    }

    public static int solve(String c) {
        int time = 0;
        int[] p1 = getPoint(c);
        int[] p2;

        if(vowel.indexOf(c) == -1) { // 자음
            p2 = getPoint(SL);
            SL = c;
        }
        else { // 모음
            p2 = getPoint(SR);
            SR = c;
        }

        time = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        return time + 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        SL = st.nextToken();
        SR = st.nextToken();
        
        int time = 0;
        for(String c : br.readLine().split("")) {
            time += solve(c);
        }
        System.out.println(time);
    }
}
