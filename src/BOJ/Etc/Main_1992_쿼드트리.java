package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1992_쿼드트리 {
	static int[][] paper;
	static StringBuilder sb;
	
	public static boolean compression( int row, int col, int N) {
		for(int i = row; i < N + row; i++) {
			for(int j = col; j< N + col; j++) {
				if(paper[row][col] != paper[i][j]) return false;
			}
		}
		return true;
	}
	
	
	public static void quadTree(int row, int col, int N) {
		if(compression(row,col,N)) {
			sb.append(paper[row][col]);
			return;
		}

		sb.append("(");
		int M = N/2;
		quadTree(row, col, M); // 0,0
		quadTree(row, col+M, M); // 0,1
		quadTree(row+M, col, M); // 1,0
		quadTree(row+M, col+M, M); // 1,1
		sb.append(")");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			paper[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		sb = new StringBuilder();
		quadTree(0,0,N);
		System.out.println(sb);
	}


}
