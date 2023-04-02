package BOJ.CodePlus.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// TreeMap
public class JewelThief_1202 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static Jewel[] jewel;
    private static TreeMap<Integer, Integer> bag;

    public static class Jewel implements Comparable<Jewel>{
        int weight, price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            return o.price - this.price;
        }
    }

    public static long solve() {
        Arrays.sort(jewel);

        long ans = 0;
        for (int i = 0; i < N; i++) {
            Map.Entry<Integer, Integer> it = bag.ceilingEntry(jewel[i].weight); // lower bound
            if(it != null) {
                ans += jewel[i].price;
                int w = (int) it.getKey();
                int cnt = (int) it.getValue() - 1;
                if(cnt == 0) bag.remove(w);
                else bag.put(w, cnt);
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewel = new Jewel[N]; // 보석
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewel[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bag = new TreeMap<>();
        for (int i = 0; i < K; i++) {
            int w = Integer.parseInt(br.readLine());
            bag.put(w, bag.getOrDefault(w, 0) + 1);
        }

        System.out.println(solve());
    }
}

