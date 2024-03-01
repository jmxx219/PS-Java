package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11286_절대값힙 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] op;
    
    public static void solve() {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            if(abs1 == abs2) return o1 - o2;
            return abs1 - abs2;
        });

        for(int i = 0 ; i < N; i++){
            if(op[i] == 0){
                if(heap.isEmpty()) System.out.println("0");
                else System.out.println(heap.poll());
            }
            else heap.add(op[i]);
        }
    }
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        op = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            op[i] = Integer.parseInt(st.nextToken());
        }
        
        solve();
    }

}
