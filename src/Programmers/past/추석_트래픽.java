package Programmers.past;

/**
 * 초당 최대 처리량: 임의 시간부터 1초(=1,000밀리초)간 처리하는 요청의 최대 개수
 * 응답완료시간 S와 처리시간 T가 공백으로 -> 2016-09-15 03:10:33.020 0.011s
 *
 */
public class 추석_트래픽 {

	private static int convertTimeToMs(String time) {
		return (int)(Double.parseDouble(time.substring(0, time.length() - 1)) * 1000);
	}

	private static int convertDateToMs(String time) {
		String[] times = time.split(":");
		int hourMs = Integer.parseInt(times[0]) * 1000 * 60 * 60;
		int minuteMs = Integer.parseInt(times[1]) * 1000 * 60;
		int secondMs = (int)(Double.parseDouble(times[2]) * 1000);
		return hourMs + minuteMs + secondMs;
	}

	public static int solution(String[] lines) {
		int[][] times = new int[lines.length][2];

		for (int i = 0; i < lines.length; i++) {
			String[] split = lines[i].split(" ");
			int workMs = convertTimeToMs(split[2]);
			int endMs = convertDateToMs(split[1]);
			int startMs = endMs - workMs + 1;
			times[i][0] = startMs;
			times[i][1] = endMs;
		}

		int res = 0;
		for (int i = 0; i < lines.length; i++) {
			int cnt = 0;
			for (int j = i; j < lines.length; j++) {

			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(solution(new String[] {
			"2016-09-15 01:00:04.002 2.0s",
			"2016-09-15 01:00:07.000 2s"
		}));
		System.out.println(solution(new String[] {
			"2016-09-15 20:59:57.421 0.351s",
			"2016-09-15 20:59:58.233 1.181s",
			"2016-09-15 20:59:58.299 0.8s",
			"2016-09-15 20:59:58.688 1.041s",
			"2016-09-15 20:59:59.591 1.412s",
			"2016-09-15 21:00:00.464 1.466s",
			"2016-09-15 21:00:00.741 1.581s",
			"2016-09-15 21:00:00.748 2.31s",
			"2016-09-15 21:00:00.966 0.381s",
			"2016-09-15 21:00:02.066 2.62s"
		}));
	}
}
