package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// F -> BitMask
public class 기차가_어둠을_헤치고_은하수를_15787 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] train;

    public static void command(int m, int i, int x) {
        if(m == 1) train[i] |= (1 << x - 1);
        else if(m == 2) train[i] &= ~(1 << x - 1);
        else if(m == 3) train[i] = (train[i] & ~(1 << 19)) << 1;
        else train[i] = (train[i] & ~(1 << 0)) >> 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        train = new int[N + 1];
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int x = 0;
            if(m == 1 || m == 2) x = Integer.parseInt(st.nextToken());
            command(m, i, x);
        }

        HashSet<Integer> res = new HashSet<>();
        for(int i = 1; i <= N; i++) res.add(train[i]);
        System.out.println(res.size());
    }
}
