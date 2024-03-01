package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Stack<int[]> stack = new Stack<>();
		int[] res = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty() && stack.peek()[0] < x) stack.pop();
			if(!stack.isEmpty() && stack.peek()[0] > x) {
				res[i] = stack.peek()[1];
			}
			
			stack.add(new int[] {x, (i + 1)});
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) sb.append(res[i]).append(" ");
		System.out.println(sb);
	}

}
