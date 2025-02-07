package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12919_A와B2 {
	static String S;
	static String T;

	private static boolean solve(String str) {
		if(str.length() == S.length()) {
			return str.equals(S);
		}

		if(str.endsWith("A")) {
			if(solve(str.substring(0, str.length() - 1))) return true;
		}

		if(str.startsWith("B")) {
			StringBuilder sb = new StringBuilder();
			sb.append(str.substring(1));
			sb.reverse();
			if(solve(sb.toString())) return true;
		}

		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		System.out.println(solve(T) ? "1" : "0");
	}
}
