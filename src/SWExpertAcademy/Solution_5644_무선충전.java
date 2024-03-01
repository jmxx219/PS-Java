package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {
    static final int N = 10;
    static int M; // 총 이동 시간
    static int K; // BC의 개수
    static int[] A; // A의 이동정보
    static int[] B; // B의 이동정보
    static int[][] performance;
    static int[] dy = {0, -1, 0, 1, 0}; // X, 상, 우, 하, 좌
    static int[] dx = {0, 0, 1, 0, -1};
    static List<List<Integer>> locationP;
 
    private static boolean inRange(int y, int x) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
 
    private static void inintBC(int y, int x, int c, int bc) {
        for(int i = y - c; i <= y + c ; i++) {
            for(int j = x - c; j <= x + c; j++) {
                if(!inRange(i, j)) continue;
                if(Math.abs(y - i) + Math.abs(x - j) <= c) {
                    locationP.get(i * 10 + j).add(bc);
                }
            }
        }
    }
     
    private static int charge(int y1, int x1, int y2, int x2) {
        int index1 = y1 * 10 + x1;
        int index2 = y2 * 10 + x2;
         
        int aBC1 = 0, aBC2 = 0;
        for(int b : locationP.get(index1)) {
            if(performance[b][3] > performance[aBC1][3]) {
            	aBC2 = aBC1;
                aBC1 = b;
            }
            else if(performance[b][3] > performance[aBC2][3]) {
				aBC2 = b;
			}
        }

        int bBC1 = 0, bBC2 = 0;
        for(int b : locationP.get(index2)) {
            if(performance[b][3] > performance[bBC1][3]) {
            	bBC2 = bBC1;
                bBC1 = b;
            }
            else if(performance[b][3] > performance[bBC2][3]) {
                bBC2 = b;
            }
        }

        if(aBC1 == bBC1) {
            if(performance[aBC2][3] <= performance[bBC2][3]) bBC1 = bBC2;
            else aBC1 = aBC2;
        }
 
        return performance[aBC1][3] + performance[bBC1][3];
    }
 
    private static int solve() {
        for(int i = 1; i <= K; i++) {
            inintBC(performance[i][0], performance[i][1], performance[i][2], i);
        }
         
        int y1 = 0, x1 = 0;
        int y2 = N - 1, x2 = N - 1;
         
        int sumP = charge(y1, x1, y2, x2);
         
        for(int i = 0; i < M; i++) {
            y1 += dy[A[i]];
            x1 += dx[A[i]];
            y2 += dy[B[i]];
            x2 += dx[B[i]];
             
            sumP += charge(y1, x1, y2, x2); // 충전
        }
         
        return sumP;
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
         
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
             
            performance = new int[K + 1][4];
            for(int i = 1; i <= K; i++ ) {
                st = new StringTokenizer(br.readLine());
                performance[i][1] = Integer.parseInt(st.nextToken()) - 1; // x
                performance[i][0] = Integer.parseInt(st.nextToken()) - 1; // y
                performance[i][2] = Integer.parseInt(st.nextToken());
                performance[i][3] = Integer.parseInt(st.nextToken());
            }
             
            locationP = new ArrayList<>();
            for(int i = 0; i < N * N; i++) locationP.add(new ArrayList<>());
            sb.append("#").append(t).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
