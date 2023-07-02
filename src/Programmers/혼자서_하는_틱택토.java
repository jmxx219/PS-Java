package Programmers;

// 40m(Pass)
public class 혼자서_하는_틱택토 {
    // 선공: O, 후공: X
    private int firstCnt;
    private int secondCnt;
    private boolean firstOver;
    private boolean secondOver;
    private static final int N = 3;

    private void setGame(String[] board) {
        char[][] map = new char[N][N];
        for(int i = 0; i < N; i++) {
            map[i] = board[i].toCharArray();
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 'O') firstCnt += 1;
                else if(map[i][j] == 'X') secondCnt += 1;
            }
        }

        for(int i = 0; i < N; i++) {
            if(map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                if(map[i][0] == 'O') firstOver = true;
                else if(map[i][0] == 'X') secondOver = true;
            }
        }

        for(int j = 0; j < N; j++) {
            if(map[0][j] == map[1][j] && map[1][j] == map[2][j]) {
                if(map[0][j] == 'O') firstOver = true;
                else if(map[0][j] == 'X') secondOver = true;
            }
        }

        if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            if(map[0][0] == 'O') firstOver = true;
            else if(map[0][0] == 'X') secondOver = true;
        }

        if(map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            if(map[0][2] == 'O') firstOver = true;
            else if(map[0][2] == 'X') secondOver = true;
        }
    }

    public int solution(String[] board) {
        firstCnt = 0;
        secondCnt = 0;
        firstOver = false;
        secondOver = false;

        setGame(board);

        if(firstCnt < secondCnt || firstCnt - secondCnt > 1) return 0;
        if(firstCnt > secondCnt) {
            if(secondOver) return 0;
        }
        else {
            if(firstOver) return 0;
        }

        return 1;
    }
}