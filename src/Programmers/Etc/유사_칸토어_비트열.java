package Programmers.Etc;

public class 유사_칸토어_비트열 {

	private static int getIndex(long i) {
		while (i > 0) {
			if(i % 5 == 2) return 2;
			i /= 5;
		}
		return (int) i;
	}

	public static int solution(int n, long l, long r) {
		int[] mapping = {1, 1, 0, 1, 1};
		int answer = 0;
		for(long i = l - 1; i <= r - 1; i++) {
			answer += mapping[getIndex(i)];
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution(2, 4, 17));
	}
}
