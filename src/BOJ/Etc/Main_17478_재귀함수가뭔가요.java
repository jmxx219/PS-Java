package BOJ.Etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_17478_재귀함수가뭔가요 {
	private static BufferedReader br;
	private static int N;
	private static String line = "____";
	
	private static void print(int n, StringBuilder sb) {
		if(n > N) return;
		
		for(int i = 0; i < n; i++) sb.append(line);
		sb.append("\"재귀함수가 뭔가요?\"\n");
		
		if(n < N) {
			for(int i = 0; i < n; i++) sb.append(line);
			sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			for(int i = 0; i < n; i++) sb.append(line);
			sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			for(int i = 0; i < n; i++) sb.append(line);
			sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		}
		else  {
			for(int i = 0; i < n; i++) sb.append(line);
			sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
		}
		
		print(n + 1, sb);
		
		for(int i = 0; i < n; i++) sb.append(line);
		sb.append("라고 답변하였지.\n");
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		print(0, sb);
		System.out.println(sb);
	}

}
