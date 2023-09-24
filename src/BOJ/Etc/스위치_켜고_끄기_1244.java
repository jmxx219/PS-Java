package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치_켜고_끄기_1244 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 스위치 수
    private static int M; // 학생수
    private static boolean[] switchOn; // 스위치 on/off

    // 1: 켜져있음, 0: 꺼져있음
    // 남학생(1) -> 자기가 받은 수의 배수인 스위치 번호 상태를 바꿈(반대로)
    // 여핵생(2) -> 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로,
    //         좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간(개수는 항상 홀수)의 스위치 상태를 모두 바꿈

    private static void woman(int index) {
        switchOn[index] = !switchOn[index];

        int left = index - 1;
        int right = index + 1;
        while (left >= 1 && right <= N) {
            if(switchOn[left] == switchOn[right]) {
                switchOn[left] = !switchOn[left];
                switchOn[right] = !switchOn[right];
            }
            else break;
            left -= 1;
            right += 1;
        }
    }

    private static void man(int index) {
        for (int i = index; i <= N; i += index) {
            switchOn[i] = !switchOn[i];
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        switchOn = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int sw = Integer.parseInt(st.nextToken());
            if(sw == 1) switchOn[i] = true;
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            if(student == 1) man(index);
            else woman(index);
        }

        for (int i = 1; i <= N; i++) {
            if (switchOn[i]) System.out.print(1 + " ");
            else System.out.print(0 + " ");
            if(i % 20 == 0) System.out.println();
        }
        System.out.println();
    }
}
