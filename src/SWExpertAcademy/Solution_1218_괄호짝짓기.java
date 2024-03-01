package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_괄호짝짓기 {
	
	private static boolean ok(char c1, char c2) {
		if(c1 == '(') return c2 == ')';
		else if(c1 == '{') return c2 == '}';
		else if(c1 == '[') return c2 == ']';
		return c2 == '>';
	}
	private static int solve(char[] ch) {
		Stack<Character> stack = new Stack<>();
		int index = 0;
		
		while(index < ch.length) {
			char c = ch[index];
			if(c == ')' || c == ']' || c == '}' || c == '>') {
				if(!stack.isEmpty() && ok(stack.peek(), c)) stack.pop();
				else return 0;
			}
			else stack.add(c);
			index += 1;
		}
		
		if(!stack.isEmpty()) return 0;
		return 1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			int res = solve(br.readLine().toCharArray());
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}

}
