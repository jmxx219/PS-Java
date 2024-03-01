package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1208_Flatten {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int dump;
	private static final int N = 100;
	private static final int T = 10;
	private static PriorityQueue<Integer> minq;
	private static PriorityQueue<Integer> maxq;
	
	private static int solve() {
		int res = 0;
	
		for(int i = 0; i < dump; i++) {
			int minN = minq.poll();
			int maxN = maxq.poll();
			if(maxN - minN <= 1) return maxN - minN;
			minq.add(minN + 1);
			minq.add(maxN - 1);
			maxq.add(minN + 1);
			maxq.add(maxN - 1);
		}
		
		return maxq.poll() - minq.poll();
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		for(int t = 1; t <= T; t++) {
			minq = new PriorityQueue<>();
			maxq = new PriorityQueue<>(Collections.reverseOrder());
			
			st = new StringTokenizer(br.readLine());
			dump = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				minq.add(x);
				maxq.add(x);
			}
			System.out.println("#" + t + " " + solve());
		}

	}

}
