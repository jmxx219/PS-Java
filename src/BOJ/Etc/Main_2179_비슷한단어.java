package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2179_비슷한단어 {
	static class Word implements Comparable<Word> {
		int idx;
		String str;
		public Word(int idx, String str) {
			this.idx = idx;
			this.str = str;
		}

		@Override
		public int compareTo(Word o) {
			if(this.str.equals(o.str)) return this.idx - o.idx;
			return this.str.compareTo(o.str);
		}

		@Override
		public String toString() {
			return "Word{" +
				"idx=" + idx +
				", str='" + str + '\'' +
				'}';
		}
	}
	static int N;
	static Word[] A;
	static Word[] B;

	private static int countSamePrefix(String str1, String str2) {
		int idx = 0;
		while (idx < str1.length() && idx < str2.length()) {
			if (str1.charAt(idx) != str2.charAt(idx)) break;
			idx++;
		}
		return idx;
	}

	private static String solve() {
		Arrays.sort(B);

		int[] len = new int[N];
		int maxCnt = 0;
		for (int i = 0; i < N - 1; i++) {
			if(B[i].str.equals(B[i + 1].str)) continue;
			int cnt = countSamePrefix(B[i].str, B[i + 1].str);
			maxCnt = Math.max(maxCnt, cnt);
			len[B[i].idx] = Math.max(len[B[i].idx], cnt);
			len[B[i + 1].idx] = Math.max(len[B[i + 1].idx], cnt);
		}

		String str1 = null;
		String str2 = null;
		for (int i = 0; i < N; i++) {
			if(len[i] != maxCnt) continue;
			if(str1 == null) {
				str1 = A[i].str;
			}
			else {
				int cnt = countSamePrefix(str1, A[i].str);
				if(len[i] == maxCnt && len[i] == cnt) {
					str2 = A[i].str;
					break;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(str1).append("\n");
		sb.append(str2).append("\n");
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new Word[N];
		B = new Word[N];
		for (int i = 0; i < N; i++) {
			A[i] = new Word(i, br.readLine());
			B[i] = A[i];
		}
		System.out.println(solve());
	}
}
