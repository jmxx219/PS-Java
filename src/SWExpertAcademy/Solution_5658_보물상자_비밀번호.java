package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Solution_5658_보물상자_비밀번호 {

	static int N;
	static int K;
	static int M;
	static char[] arr;
	
	private static int toConvert(char[] ch) { // 16 -> 10
		int n = 0;
		int x = 1;
		for(int i = ch.length - 1; i >= 0; i--) {
			if(Character.isDigit(ch[i])) n += (int)(ch[i] - '0') * x;
			else n += ((int)(ch[i] - 'A') + 10) * x;
			x *= 16;
		}
		return n;
	}
	
	public static int solve() {
		List<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		char[] tmp = new char[M];
		for(int r = 0; r < M; r++) {
			for(int i = r; i < N; i+=M) {
				for(int j = 0; j < M; j++) {
					tmp[j] = arr[i + j];
				}
				int num = toConvert(tmp);
				if(!set.contains(num)) {
					list.add(num);
					set.add(num);
				}
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list.get(K - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			M = N / 4;
			char[] tmp = br.readLine().toCharArray();
			arr = new char[N + M - 1];
			for(int i = M - 1; i < N + M - 1; i++) {
				arr[i] = tmp[i - M + 1];
			}
			
			for(int i = 0; i < M - 1; i++) {
				arr[i] = arr[N + i];
			}
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);

	}

}
