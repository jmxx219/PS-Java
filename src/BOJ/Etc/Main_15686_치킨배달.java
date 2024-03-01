package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
	static int N;
	static int M;
	static int[][] map;
	static int cityDist;
	static List<int[]> chicken;
	static List<int[]> house;

	private static int calc(int flag) {
		int total = 0;
		
		for(int i = 0; i < house.size(); i++) {
			int dist = -1;
			for(int j = 0; j < chicken.size(); j++) {
				if((flag & 1<<j) != 0) {
					int tmp = Math.abs(house.get(i)[0] - chicken.get(j)[0]) + Math.abs(house.get(i)[1] - chicken.get(j)[1]);
					if(dist == -1 || dist > tmp) dist = tmp;
				}
			}
			total += dist;
		}
		
		return total;
	}
	
	private static void solve(int index, int start, int flag) {
		if(index == M) {
			int dist = calc(flag);
			if(cityDist == -1 || cityDist > dist) cityDist = dist;
			return;
		}
		
		for(int i = start; i < chicken.size(); i++) {
			solve(index + 1, i + 1, flag | 1<<i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		chicken = new ArrayList<>();
		house = new ArrayList<>();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) chicken.add(new int[] {i, j});
				else if (map[i][j] == 1) house.add(new int[] {i, j});
			}
		}
		cityDist = -1;
		solve(0, 0, 0);
		System.out.println(cityDist);
	}
}
