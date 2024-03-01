package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12891_DNA비밀번호 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int S; // DNA 문자열 길이
	private static int P; // 비밀번호 길이
	private static char[] str;
	private static int[] target; // A, C, G, T
	
	private static int getIndex(char c) {
		if(c == 'A') return 0;
		else if(c == 'C') return 1;
		else if(c == 'G') return 2;
		return 3;
	}
	
	private static int same(int[] cnt) {
		if(target[0] <= cnt[0] && target[1] <= cnt[1]
				&& target[2] <= cnt[2] && target[3] <= cnt[3]) return 1;
		return 0;
	}
	
	private static int solve() {
		int[] cnt = new int[4];
		for(int i = 0; i < P; i++) {
			cnt[getIndex(str[i])] += 1;
		}
		
		int res = same(cnt);
		for(int i = 1; i < S - P + 1; i++) {
			cnt[getIndex(str[i - 1])] -= 1;
			cnt[getIndex(str[i + P - 1])] += 1;
			res += same(cnt);
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		str = br.readLine().toCharArray();
		target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(solve());
	}

}
