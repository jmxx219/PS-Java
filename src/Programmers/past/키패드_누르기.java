package Programmers.past;

// Pass(15m)
class 키패드_누르기 {
    static final int[][] keypad = {
            {3, 1}, {0, 0}, {0, 1},{0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1},{2, 2}};

    public int calcDist(int[] left, int[] next) {
        int dist = 0;
        dist += Math.abs(left[0] - next[0]);
        dist += Math.abs(left[1] - next[1]);
        return dist;
    }
    public String solution(int[] numbers, String hand) {
        String answer = "";

        int[] left = {3, 0};
        int[] right = {3, 2};

        for(int n : numbers) {
            String tmp = "";
            if(n == 1 || n == 4 || n == 7) tmp = "L";
            else if(n == 3 || n == 6 || n == 9) tmp = "R";
            else {
                int leftDist = calcDist(left, keypad[n]);
                int rightDist = calcDist(right, keypad[n]);
                if(leftDist == rightDist) {
                    tmp = (hand.equals("right") ? "R" : "L");
                }
                else tmp = (leftDist > rightDist ? "R" : "L");
            }
            answer += tmp;
            if(tmp.equals("L")) left = keypad[n];
            else right = keypad[n];
        }

        return answer;
    }
}