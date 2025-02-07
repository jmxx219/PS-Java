package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20437_문자열게임2 {
	static String W;
	static int K;
	static List<Integer>[] alphabet;

	private static String solve() {
		alphabet = new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			alphabet[i] = new ArrayList<>();
		}

		for(int i = 0; i < W.length(); i++) {
			alphabet[W.charAt(i) - 'a'].add(i);
		}

		int minLen = 10001;
		int maxLen = 0;
		for (int i = 0; i < 26; i++) {
			int size = alphabet[i].size();
			if(size < K) continue;
			for (int j = 0; j <= size - K; j++) {
				int startIdx = alphabet[i].get(j);
				int endIdx = alphabet[i].get(j + K - 1);
				minLen = Math.min(minLen, endIdx - startIdx + 1);
				maxLen = Math.max(maxLen, endIdx - startIdx + 1);
			}
		}

		if(minLen == 10001 || maxLen == 0) return "-1";
		return minLen + " " + maxLen;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			sb.append(solve()).append("\n");
		}
		System.out.println(sb);
	}

}
