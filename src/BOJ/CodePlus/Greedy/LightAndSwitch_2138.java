package BOJ.CodePlus.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LightAndSwitch_2138 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] prevLight_A;
    private static int[] prevLight_B;
    private static int[] resLight;

    public static void toggle(int x, int[] prevLight) {
        if(x > 0) {
            prevLight[x - 1] = 1 - prevLight[x - 1];
        }
        prevLight[x] = 1 - prevLight[x];
        if(x < prevLight.length - 1) {
            prevLight[x + 1] = 1 - prevLight[x + 1];
        }
    }

    public static int makeLight(int cnt, int[] prevLight) {

        for (int i = 1; i < prevLight.length; i++) {
            if(prevLight[i - 1] != resLight[i - 1]) {
                toggle(i, prevLight);
                cnt += 1;
            }
        }

        return resLight[resLight.length - 1] == prevLight[prevLight.length - 1] ? cnt : Integer.MAX_VALUE;
    }

    public static int solve() {

        prevLight_B = Arrays.copyOf(prevLight_A, prevLight_A.length);

        // 0번째 인덱스 스위치 눌렀을 경우와 안눌렀을 경우
        toggle(0, prevLight_A);
        int res = Math.min(makeLight(1, prevLight_A), makeLight(0, prevLight_B));

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        prevLight_A = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        resLight = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solve());
    }
}
