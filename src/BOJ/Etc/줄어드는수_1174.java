package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// F
public class 줄어드는수_1174 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static final int[] num = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    private static List<Long> list;

    private static void solve(long curr, int index) {
        if(!list.contains(curr)) {
            list.add(curr);
        }

        if(index >= 10) return;

        solve(curr * 10 + num[index], index + 1);
        solve(curr, index + 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        solve(0, 0);
        Collections.sort(list);

        // 10개의 숫자를 선택하는 경우의 수 => 1024(2^10)
        if(N > 1023) System.out.println(-1);
        else System.out.println(list.get(N - 1));

    }
}
