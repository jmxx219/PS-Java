package Programmers.past;

// Pass(35m)
class 합승_택시_요금 {
    private static final int INF = Integer.MAX_VALUE;
    private int[][] edge;

    public int floydWarshall(int n, int s, int a, int b) {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(edge[i][k] == INF) continue;
                for(int j = 1; j <= n; j++) {
                    if(edge[k][j] == INF) continue;
                    if(edge[i][j] > edge[i][k] + edge[k][j]) {
                        edge[i][j] = edge[i][k] + edge[k][j];
                    }
                }
            }
        }

        int answer = Math.min(INF, edge[s][a] + edge[s][b]);
        for(int i = 1; i <= n; i++) {
            if(i == s) continue;
            int cost = Math.min(INF, edge[s][i] + edge[i][a] + edge[i][b]);
            answer = Math.min(answer, cost);
        }

        return answer;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {

        edge = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j) edge[i][j] = INF;
            }
        }

        for(int[] fare : fares) {
            edge[fare[0]][fare[1]] = fare[2];
            edge[fare[1]][fare[0]] = fare[2];
        }

        return floydWarshall(n, s, a, b);
    }
}