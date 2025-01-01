package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2457_공주님의_정원 {
	static class Flower implements Comparable<Flower> {
		int start, end;

		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Flower o) {
			if(this.start == o.start) {
				return o.end - this.end;
			}
			return this.start - o.start;
		}

		@Override
		public String toString() {
			return "Flower{" +
				"start=" + start +
				", end=" + end +
				'}';
		}
	}

	static int N;
	static Flower[] flowers;
	static final int START_DAY = 301;
	static final int END_DAY = 1201;

	private static int solve() {
		Arrays.sort(flowers);

		int start = START_DAY;
		int end = 0;
		int index = 0;
		int res = 0;

		while (start < END_DAY) {

			boolean find = false;
			for (int i = index; i < N; i++) {
				if(flowers[i].start > start) break;

				if(end < flowers[i].end) {
					find = true;
					end = flowers[i].end;
					index++;
				}
			}
			if(find) {
				start = end;
				res++;
			}
			else break;
		}

		return end < END_DAY ? 0 : res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		flowers = new Flower[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			flowers[i] = new Flower(sm * 100 + sd, em * 100 + ed);
		}
		System.out.println(solve());
	}
}
