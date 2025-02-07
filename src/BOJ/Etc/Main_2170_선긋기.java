package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2170_선긋기 {
	static class Point implements Comparable<Point> {
		int start, end;

		public Point(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Point o) {
			if(this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}
	static int N;
	static Point[] points;

	private static long solve() {
		Arrays.sort(points);

		long res = 0;
		int i = 0;
		while (i < N) {
			int start = points[i].start;
			int end = points[i].end;

			int j = i + 1;
			while (j < N && points[j].start <= end) {
				end = Math.max(end, points[j].end);
				j++;
			}
			res += Math.abs(end - start);
			i = j;
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		points = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		System.out.println(solve());
	}
}
