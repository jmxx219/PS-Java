package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16434_드래곤앤던전 {
	static class Room {
		boolean isMonster;
		long a, h;
		public Room(boolean isMonster, long a, long h) {
			this.isMonster = isMonster;
			this.a = a;
			this.h = h;
		}
	}
	static int N;
	static long Hatk;
	static Room[] rooms;

	private static boolean isPossible(long maxHp) {
		long curHp = maxHp;
		long atk = Hatk;

		for(Room room : rooms) {
			if(room.isMonster) {
				if (room.h % atk == 0) {
					curHp -= (room.h / atk - 1) * room.a;
				} else {
					curHp -= (room.h / atk) * room.a;
				}
				if(curHp <= 0) return false;
			}
			else {
				atk += room.a;
				curHp = Math.min(maxHp, curHp + room.h);
			}
		}
		return true;
	}

	private static long solve() {
		long lo = 1;
		long hi = (long) 1e18;

		long res = 0L;
		while (lo <= hi) {
			long mid = (hi + lo) / 2;
			if(isPossible(mid)) {
				hi = mid - 1;
				res = mid;
			}
			else lo = mid + 1;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Hatk = Long.parseLong(st.nextToken());

		rooms = new Room[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			long h = Long.parseLong(st.nextToken());
			rooms[i] = new Room(t == 1 ? true : false, a, h);
		}

		System.out.println(solve());
	}
}
