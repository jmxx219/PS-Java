package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// F
public class 코딩테스트_공부_dijkstra {
    private static List<Problem> pL;
    private static int INF = 987654321;
    private static int maxAlp;
    private static int maxCop;

    public static class Problem implements Comparable<Problem> {
        int alpReq;
        int copReq;
        int alpRwd;
        int copRwd;
        int cost;

        public Problem(int alpReq, int copReq, int alpRwd, int copRwd, int cost) {
            this.alpReq = alpReq;
            this.copReq = copReq;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.cost = cost;
        }

        @Override
        public int compareTo(Problem p) {
            return this.cost - p.cost;
        }
    }

    private static class Point implements Comparable<Point> {
        int alp, cop;
        int cost;

        public Point(int alp, int cop, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point p) {
            return this.cost - p.cost;
        }
    }

    private static int dijkstra(Point start) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[][] dist = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], INF);
        }

        pq.add(start);
        dist[start.alp][start.cop] = 0;
        while (!pq.isEmpty()) {
            Point here = pq.poll();
            int alp = here.alp;
            int cop = here.cop;

            if(dist[alp][cop] < here.cost) continue;
            if(alp >= maxAlp && cop >= maxCop) break;

            for(Problem p : pL) {
                if(p.alpReq <= alp && p.copReq <= cop) {
                    int nextAlp = Math.min(maxAlp, alp + p.alpRwd);
                    int nextCop = Math.min(maxCop, cop + p.copRwd);
                    int nextCost = dist[alp][cop] + p.cost;
                    if(dist[nextAlp][nextCop] > nextCost) {
                        dist[nextAlp][nextCop] = nextCost;
                        pq.add(new Point(nextAlp, nextCop, nextCost));
                    }
                }
            }

        }

        return dist[maxAlp][maxCop];
    }

    public static int solution(int alp, int cop, int[][] problems) {
        maxAlp = -1;
        maxCop = -1;
        pL = new ArrayList<>();
        pL.add(new Problem(0, 0, 1, 0, 1));
        pL.add(new Problem(0, 0, 0, 1, 1));
        for (int[] problem : problems) {
            if(problem[0] > maxAlp) maxAlp = problem[0];
            if(problem[1] > maxCop) maxCop = problem[1];
            pL.add(new Problem(problem[0], problem[1], problem[2], problem[3], problem[4]));
        }

        Collections.sort(pL);

        Point start = new Point(Math.min(alp, maxAlp), Math.min(cop, maxCop), 0);
        return dijkstra(start);
    }

    public static void main(String[] args) {
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
        System.out.println(solution(10, 10, problems));

        int[][] problems2 = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
        System.out.println(solution(0, 0, problems2));
    }
}
