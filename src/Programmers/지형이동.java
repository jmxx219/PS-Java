package Programmers;

import java.util.*;

// MST
public class 지형이동 {
    public int[][] group;
    public int[] dy = {1, -1, 0, 0};
    public int[] dx = {0, 0, 1, -1};
    public int H;
    public List<Edge> edges;

    public static class Edge implements Comparable<Edge>{
        public int u;
        public int v;
        public int cost;
        public Edge(int u, int v, int cost){
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }

    public boolean isRange(int y, int x) {
        return 0 <= y && y < group.length && 0 <= x && x < group.length;
    }

    public void dfs(int y, int x, int color, int[][] land) {
        group[y][x] = color;
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!isRange(ny, nx)) continue;
            if(group[ny][nx] == -1 && Math.abs(land[y][x] - land[ny][nx]) <= H) {
                dfs(ny, nx, color, land);
            }
        }
    }

    public int initGroup(int[][] land) {
        for(int i = 0; i < group.length; i++) {
            Arrays.fill(group[i], -1);
        }

        int color = 0;
        for(int i = 0; i < group.length; i++) {
            for(int j = 0; j < group.length; j++) {
                if(group[i][j] == -1) {
                    dfs(i, j, color, land);
                    color += 1;
                }
            }
        }
        return color;
    }

    public void setEdge(int[][] land) {
        for(int y = 0; y < group.length; y++) {
            for(int x = 0; x < group.length; x++) {
                for(int k = 0; k < 4; k++) {
                    int ny = y + dy[k];
                    int nx = x + dx[k];
                    if(!isRange(ny, nx) || group[y][x] == group[ny][nx]) continue; // 같은 그룹으면 패스
                    edges.add(new Edge(group[y][x], group[ny][nx], Math.abs(land[y][x] - land[ny][nx])));
                }
            }
        }
        Collections.sort(edges); // 간선 비용에 따라 오름차순 정렬
    }

    public int find(int x, int[] parent) { // 부모 찾기
        if(parent[x] == x) return x;
        parent[x] = find(parent[x], parent);
        return parent[x];
    }

    public void union(int u, int v, int[] parent) {
        int uP = find(u, parent);
        int vP = find(v, parent);
        if(uP < vP) parent[vP] = uP;
        else parent[uP] = vP;
    }

    public int mst(int[] parent) {
        int res = 0;

        for(Edge edge : edges) {
            int uP = find(edge.u, parent);
            int vP = find(edge.v, parent);
            if(uP != vP) { // 부모 같으면 사이클 형성
                res += edge.cost;
                union(edge.u, edge.v, parent);
            }
        }

        return res;
    }

    public int solution(int[][] land, int height) {
        H = height;

        group = new int[land.length][land.length];
        int groupCnt = initGroup(land);

        edges = new ArrayList<>();
        setEdge(land);

        int[] parent = new int[groupCnt];
        for (int i = 0; i < groupCnt; i++) {
            parent[i] = i;
        }

        return mst(parent);
    }
}
