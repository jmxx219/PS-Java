package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1158_요세푸스문제 {
	static int N;
	static int K;
	
	private static void solve1() {
		int[] pick = new int[N];
		int index = 0;
		boolean[] check = new boolean[N];
		int i = 0;
		int k = 1;
		while(true) {
			if(k == K) {
				k = 0;
				pick[index++] = i;
				check[i] = true;
			}
			if(index == N) break;
			i = (i + 1) % N;
			if(!check[i]) k += 1;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int j = 0; j < N; j++) {
			sb.append((pick[j] + 1));
			if(j != N - 1) sb.append(", ");
		}
		sb.append(">");
		System.out.println(sb);
	}
	
	private static void solve2() {
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) dq.add(i);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!dq.isEmpty()) {
			int num = 0;
			while(num++ < K - 1) {
				dq.addLast(dq.pollFirst());
			}
			sb.append(dq.pollFirst());
			if(dq.size() != 0) sb.append(", ");
		}
		sb.append(">");
		System.out.println(sb);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
//		solve1();
		solve2();
	}

}
