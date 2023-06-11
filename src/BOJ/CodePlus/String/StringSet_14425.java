package BOJ.CodePlus.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class StringSet_14425 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static Set<String> setStr;
    private static String[] findStr;

    private static int solve() {
        int cnt = 0;

        for(String find : findStr) {
            if(setStr.contains(find)) cnt += 1;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        setStr = new HashSet<>();
        for (int i = 0; i < N; i++) {
            setStr.add(br.readLine());
        }

        findStr = new String[M];
        for (int i = 0; i < M; i++) {
            findStr[i] = br.readLine();
        }

        System.out.println(solve());
    }
}
