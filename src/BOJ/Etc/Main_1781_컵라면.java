package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1781_컵라면 {
	static class Problem implements Comparable<Problem> {
		int index;
		int deadline;
		int ramen;

		public Problem(int index, int deadline, int ramen) {
			this.index = index;
			this.deadline = deadline;
			this.ramen = ramen;
		}

		@Override
		public String toString() {
			return "{" +
				"index=" + index +
				", deadline=" + deadline +
				", ramen=" + ramen +
				'}';
		}

		@Override
		public int compareTo(Problem o) {
			if(this.deadline == o.deadline) {
				return o.ramen - this.ramen;
			}
			return this.deadline - o.deadline;
		}
	}
	static int N;
	static Problem[] problems;

	private static int solve() {
		Arrays.sort(problems);

		PriorityQueue<Integer> ramens = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			Problem p = problems[i];
			if(p.deadline > ramens.size()) ramens.add(p.ramen);
			else if(p.deadline == ramens.size()) {
				if(ramens.peek() < p.ramen) {
					ramens.poll();
					ramens.add(p.ramen);
				}
			}
		}

		int totalRamen = 0;
		while (!ramens.isEmpty()) {
			totalRamen += ramens.poll();
		}
		return totalRamen;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		problems = new Problem[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());
			problems[i] = new Problem(i + 1, deadline, ramen);
		}

		System.out.println(solve());
	}
}
