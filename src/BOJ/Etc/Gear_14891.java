package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gear_14891 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] gear; // N: 0, S: 1
    private static int K;
    private static final int[] score = {1, 2, 4, 8};

    public static void rotate(int index, int direction) {
        if(direction == 1) { // 시계: 1,
            int last = gear[index][7];
            for (int i = 6; i >= 0; i--) {
                gear[index][i + 1] = gear[index][i];
            }
            gear[index][0] = last;
        }
        else {  // 반시계: -1
            int first = gear[index][0];
            for (int i = 1; i <= 7; i++) {
                gear[index][i - 1] = gear[index][i];
            }
            gear[index][7] = first;
        }
    }

    public static void solve(int curr, int direction) {
        int[] rotateGear = new int[4];
        Arrays.fill(rotateGear, -2);

        rotateGear[curr] = direction;
        int left = curr == 0 ? -1 : curr - 1;
        int right = curr == 3 ? -1 : curr + 1;

        int tmp = curr;
        while (left != -1 && gear[curr][6] != gear[left][2]) {
            rotateGear[left] = rotateGear[curr] * -1;
            curr = left;
            left = (curr == 0) ? -1 : curr - 1;
        }

        curr = tmp;
        while (right != -1 && gear[curr][2] != gear[right][6]) {
            rotateGear[right] = rotateGear[curr] * -1;
            curr = right;
            right = (curr == 3) ? -1 : curr + 1;
        }

        for (int i = 0; i < 4; i++) {
            if(rotateGear[i] != -2) rotate(i, rotateGear[i]);
        }
    }

    public static int calcScore() {
        int res = 0;

        for (int i = 0; i < 4; i++) {
            if(gear[i][0] == 1) res += score[i];
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        gear = new int[4][8];
        for (int i = 0; i < 4; i++) {
            gear[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            solve(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }

        System.out.println(calcScore());
    }
}
