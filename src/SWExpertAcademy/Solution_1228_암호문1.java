package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1228_암호문1 {
	
	static int N = 10;
	static String[] code;
	static int M;
	
	private static void insert(int x, String[] command) {
		int tmpSize = N - (command.length + x);
		if(tmpSize < 0) tmpSize = 0;
		String[] tmp = new String[tmpSize];
		int index = 0;
		for(int i = 0; i < N; i++) {
			if(index < tmpSize) tmp[index++] = code[i + x];
			if(i < command.length && i + x < N) code[i + x] = command[i];
		}
		
		for(int i = 0; i < tmpSize; i++) {
			code[i + command.length + x] = tmp[i];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			code = new String[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) code[i] = st.nextToken();
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				String insert = st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int iCnt = Integer.parseInt(st.nextToken());
				String[] command = new String[iCnt];
				for(int j = 0; j < iCnt; j++) command[j] = st.nextToken();
				insert(x, command);
			}
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < N; i++) {
				sb.append(code[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

}


/*
11
449047 855428 425117 532416 358612 929816 313459 311433 472478 589139 568205
7
I 1 5 400905 139831 966064 336948 119288 I 8 6 436704 702451 762737 557561 810021 771706 I 3 8 389953 706628 552108 238749 661021 498160 493414 377808 I 13 4 237017 301569 243869 252994 I 3 4 408347 618608 822798 370982 I 8 2 424216 356268 I 4 10 512816 992679 693002 835918 768525 949227 628969 521945 839380 479976


19
566753 244528 233616 328235 268163 292641 646305 944392 643695 385788 444752 939244 637877 325283 779273 712343 953523 846222 204307
5
I 0 4 600576 565945 486128 594841 I 0 1 150706 I 8 8 556294 697547 932203 845517 116062 300371 621038 358830 I 10 8 747039 701738 805438 502654 476665 919177 367272 859931 I 15 3 844423 973297 658751
 
*/
