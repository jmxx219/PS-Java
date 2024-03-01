package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_암호생성기 {

	private static void solve(Queue<Integer> code) {
		int n = 0;
		while(true) {
			int x = code.poll() - (n + 1);
			if(x <= 0) x = 0;
			code.offer(x);
			n = (n + 1) % 5;
			if(x == 0) return;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Queue<Integer> code = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) code.offer(Integer.parseInt(st.nextToken()));
			solve(code);
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < 8; i++) sb.append(code.poll()).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
