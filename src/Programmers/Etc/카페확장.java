package Programmers.Etc;

import java.util.LinkedList;
import java.util.Queue;

public class 카페확장 {
	public static int solution(int[] menu, int[] order, int k) {
		Queue<int[]> queue = new LinkedList<>();
		int time = 0;
		int idx = 0;
		int res = 0;

		while (idx < order.length || !queue.isEmpty()) {
			if(!queue.isEmpty() && queue.peek()[1] <= time) {
				// System.out.println(queue.peek()[0] + ", " + queue.peek()[1] + " : " + res);
				queue.poll();

				if(!queue.isEmpty()) {
					if(time >= queue.peek()[0]) queue.peek()[0] = time;
					queue.peek()[1] += queue.peek()[0];
				}
			}

			if(time % k == 0 && idx < order.length) {
				if(queue.isEmpty()) queue.add(new int[]{k * idx, k * idx + menu[order[time/k]]});
				else queue.add(new int[]{k * idx, menu[order[time/k]]});
				idx++;
			}
			res = Math.max(res, queue.size());
			time++;
		}

		return res;
	}

	public static void main(String[] args) {
		System.out.println(solution(
			new int[] {5, 12, 30}, new int[] {1, 2, 0, 1}, 10
		));
		System.out.println(solution(
			new int[] {5, 12, 30}, new int[] {2, 1, 0, 0, 0, 1, 0}, 10
		));
		System.out.println(solution(
			new int[] {5}, new int[] {0, 0, 0, 0, 0}, 5
		));
		System.out.println(solution(
			new int[] {5, 6, 7, 11}, new int[] {1, 2, 3, 3, 2, 1, 1}, 10
		));
	}
}
