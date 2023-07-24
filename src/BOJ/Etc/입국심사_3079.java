package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 입국심사_3079 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static long max_value;
    private static int [] arr;

    private static long solve() {
        long result = Long.MAX_VALUE;

        long low = 0;
        long high = M * max_value;

        while(low <= high){
            long mid = (low + high) / 2;

            long sum = 0;
            for(long index: arr){
                long count = mid / index;
                if(sum >= M) break;
                sum += count;
            }

            if(sum >= M){
                high = mid - 1;
                result = mid;
            }
            else{
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        max_value = 0;
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max_value = Math.max(max_value, arr[i]);
        }

        System.out.println(solve());
    }
}
