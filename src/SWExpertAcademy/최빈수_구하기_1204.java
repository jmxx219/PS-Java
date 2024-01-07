package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최빈수_구하기_1204 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] score = new int[101];
            int res=-1;
            int resScore = -1;

            st = new StringTokenizer(br.readLine());
            for(int j=0; j < 1000; j++) {
                int x = Integer.parseInt(st.nextToken());
                score[x] += 1;
            }

            for(int j = 0; j <= 100; j++) {
                if(resScore <= score[j]) {
                    resScore = score[j];
                    res = j;
                }
            }
            System.out.println("#" + (i + 1) + " " + res);

        }
    }
}
