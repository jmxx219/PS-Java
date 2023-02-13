package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Inequality_2529 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int K;
    private static char[] inequality;
    private static int[] picked;
    private static String maxNum = "";
    private static String minNum = "";

    public static boolean isValid(int index) {
        if(inequality[index - 1] == '<') {
            if(picked[index - 1] < picked[index])
                return true;
        }
        else if(inequality[index - 1] == '>') {
            if(picked[index - 1] > picked[index])
                return true;
        }
        return false;
    }

    public static void solve(int index, boolean[] visited) {
        if(index > 1 && !isValid(index - 1)) {
            return;
        }
        if(index == K + 1) {
            String tmp = Arrays.stream(picked).mapToObj(Objects::toString).collect(Collectors.joining(""));
            if(maxNum.equals("") || Double.parseDouble(tmp) > Double.parseDouble(maxNum)) {
                maxNum = tmp;
            }
            if(minNum.equals("") || Double.parseDouble(tmp) < Double.parseDouble(minNum)) {
                minNum = tmp;
            }
            return;
        }
        for(int i = 0; i <= 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked[index] = i;
                solve(index + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        inequality = new char[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            inequality[i] = st.nextToken().charAt(0);
        }

        picked = new int[K + 1];
        solve(0, new boolean[10]);
        System.out.println(maxNum);
        System.out.println(minNum);
    }
}
