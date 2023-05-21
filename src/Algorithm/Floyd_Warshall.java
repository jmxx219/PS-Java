package Algorithm;

// 모든 쌍의 최단경로
// O(V^3)
public class Floyd_Warshall {
    private static int V; // 노드 수
    private static int E; // 간선 수
    private static int[][] edge; // edge[시작노드][끝노드] = 가중치
    private static final int INF = 987654321;

    public static void floydWarshall() {
        for(int k = 1; k <= V; k++) { // 중간에 방문하는 정점(i -> k -> j)
            for(int i = 1; i <= V; i++) {
                for(int j = 1; j <= V; j++) {
                    edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        edge = new int[V + 1][V + 1];
        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                if(i != j) edge[i][j] = INF;
            }
        }

        floydWarshall();
    }
}
