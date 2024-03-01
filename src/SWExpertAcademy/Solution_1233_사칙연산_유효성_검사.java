package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_사칙연산_유효성_검사 {
	static class Node {
		char c;
		int left;
		int right;
		public Node(char c, int left, int right) {
			this.c = c;
			this.left = left;
			this.right = right;
		}
	}
	static int N;
	static Node[] trees;
	static int ok;
	
	private static void solve(int root) {
		char c = trees[root].c;
		int left = trees[root].left;
		int right = trees[root].right;
		
		if(left == -1 && right == -1) {
			if(!Character.isDigit(c)) ok = 0;
			return;
		}
		
		solve(left);
		solve(right);
		
		if(Character.isDigit(c)) ok = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			ok = 1;
			trees = new Node[N + 1];
			for(int i = 0; i < N; i++) {
				st  = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				char c = st.nextToken().charAt(0);
				int left = -1;
				int right = -1;
				if(st.countTokens() == 1) ok = 0; // 자식 하나 -> 유효 x
				if(st.hasMoreTokens()) left = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens()) right = Integer.parseInt(st.nextToken());
				
				trees[n] = new Node(c, left, right);
			}
			if(ok == 1) solve(1);
			System.out.println("#" + t + " " + ok);
		}
	}

}
