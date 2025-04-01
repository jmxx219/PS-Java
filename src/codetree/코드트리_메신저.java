package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 코드트리_메신저 {
	static class Room {
		int c;
		boolean alert;
		int authority;

		int parent;
		int leftChild;
		int rightChild;

		int[] effect;

		public Room(int c) {
			this.c = c;
			this.alert = true;
			this.parent = -1;
			this.leftChild = -1;
			this.rightChild = -1;
			this.effect = new int[DEPTH + 1];
		}

		public void toggleAlert() {
			this.alert = !this.alert;
		}
	}
	static int N;
	static int Q;
	static Room[] rooms;
	static final int DEPTH = 20;

	private static void update(int c) {
		while (c != 0) {
			Room curr = rooms[c];
			Arrays.fill(curr.effect, 0);
			for (int i = 0; i <= curr.authority; i++) {
				rooms[c].effect[i]++;
			}

			if(curr.leftChild != -1 && rooms[curr.leftChild].alert) {
				for (int i = 1; i <= DEPTH; i++) {
					curr.effect[i - 1] += rooms[curr.leftChild].effect[i];
				}
			}

			if(curr.rightChild != -1 && rooms[curr.rightChild].alert) {
				for (int i = 1; i <= DEPTH; i++) {
					curr.effect[i - 1] += rooms[curr.rightChild].effect[i];
				}
			}

			c = rooms[c].parent;
		}
	}

	private static void swapParent(int c1, int c2) {
		int p1 = rooms[c1].parent;
		int p2 = rooms[c2].parent;

		if(rooms[p1].leftChild == c1) {
			rooms[p1].leftChild = c2;
		} else if(rooms[p1].rightChild == c1) {
			rooms[p1].rightChild = c2;
		}

		if(rooms[p2].leftChild == c2) {
			rooms[p2].leftChild = c1;
		} else if(rooms[p2].rightChild == c2) {
			rooms[p2].rightChild = c1;
		}

		rooms[c1].parent = p2;
		rooms[c2].parent = p1;

		update(c1);
		update(c2);
	}

	private static void changeAuthority(int c, int power) {
		rooms[c].authority = power > DEPTH ? DEPTH : power;
		update(c);
	}

	private static void toggleNotification(int c) {
		rooms[c].toggleAlert();
		update(c);
	}

	private static void initEffect(int c) {
		if(c == -1) return;

		Room curr = rooms[c];
		for (int i = 0; i <= curr.authority; i++) {
			rooms[c].effect[i]++;
		}

		initEffect(curr.leftChild);
		initEffect(curr.rightChild);

		if(curr.leftChild != -1) {
			for (int i = 1; i <= DEPTH; i++) {
				curr.effect[i - 1] += rooms[curr.leftChild].effect[i];
			}
		}

		if(curr.rightChild != -1) {
			for (int i = 1; i <= DEPTH; i++) {
				curr.effect[i - 1] += rooms[curr.rightChild].effect[i];
			}
		}
	}

	private static void init(StringTokenizer st) {
		rooms = new Room[N + 1];
		for (int i = 0; i <= N; i++) {
			rooms[i] = new Room(i);
		}

		for (int i = 1; i <= N; i++) {
			int pIdx = Integer.parseInt(st.nextToken());
			rooms[i].parent = pIdx;
			if(rooms[pIdx].leftChild == -1) rooms[pIdx].leftChild = i;
			else rooms[pIdx].rightChild = i;
		}

		for (int i = 1; i <= N; i++) {
			rooms[i].authority = Integer.parseInt(st.nextToken());
			if(rooms[i].authority > DEPTH) rooms[i].authority = DEPTH;
		}

		initEffect(0);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		int c;
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			switch (command) {
				case 100:	// 사내 메신저 준비
					init(st);
					break;
				case 200:	// 알림망 설정 (ON/OFF)
					c = Integer.parseInt(st.nextToken());
					toggleNotification(c);
					break;
				case 300:	// 권한 세기 변경
					c = Integer.parseInt(st.nextToken());
					int power = Integer.parseInt(st.nextToken());
					changeAuthority(c, power);
					break;
				case 400:	// 부모 채팅방 교환
					int c1 = Integer.parseInt(st.nextToken());
					int c2 = Integer.parseInt(st.nextToken());
					swapParent(c1, c2);
					break;
				case 500:	// 알림을 받을 수 있는 채팅방 수 조회
					c = Integer.parseInt(st.nextToken());
					sb.append(rooms[c].effect[0] - 1).append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}
