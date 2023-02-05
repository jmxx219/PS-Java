package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Abcde_13023 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static List<List<Integer>> friends;
    private static boolean[] isVisited;

    public static boolean dfs(int v, int depth) {
        if(depth == 4) {
            return true;
        }

        for(int w : friends.get(v)) {
            if(!isVisited[w]) {
                isVisited[w] = true;
                if(dfs(w, depth + 1)) {
                    return true;
                }
                isVisited[w] = false;
            }
        }

        return false;
    }

    public static int solve() {
        for(int i = 0; i < N; i++) {
            isVisited[i] = true;
            if(dfs(i, 0)) {
                return 1;
            }
            isVisited[i] = false;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            friends.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }
        isVisited = new boolean[N];
        System.out.println(solve());
    }
}
