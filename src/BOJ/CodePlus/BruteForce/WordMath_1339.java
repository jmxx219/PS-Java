package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class WordMath_1339 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<List<Character>> words;
    private static int maxNumLen = 0;
    private static int[] alpha = new int[26];


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            int base = (int) Math.pow(10, word.length() - 1);

            for(int j = 0; j < word.length() ; j++) {
                alpha[word.charAt(j) - 'A'] += base;
                base /= 10;
            }
        }

        Arrays.sort(alpha);

        int res = 0;
        for(int i = 25; i >= 17; i--) {
            res += alpha[i] * (i - 16);
        }

        System.out.println(res);

    }
}
