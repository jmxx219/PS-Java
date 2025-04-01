package Programmers.Etc;

import java.util.*;

public class 요격_시스템 {
	public int solution(int[][] targets) {
		int answer = 0;

		Arrays.sort(targets, (o1, o2) -> {
			return o1[1] - o2[1];
		});

		int end = 0;
		for(int[] target : targets) {
			if(target[0] >= end) {
				answer++;
				end = target[1];
			}
		}


		return answer;
	}
}
