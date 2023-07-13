package Programmers.past;

public class 이모티콘_할인행사 {

    static int[] answer = new int[2];
    static int[] saleRate;

    public static void compute(int[][] users, int[] emoticons) {
        int[] purchase = new int[users.length];

        int service = 0;
        int saleAmount = 0;
        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < emoticons.length; j++) {
                if(saleRate[j] >= users[i][0]) {
                    purchase[i] += (emoticons[j] - emoticons[j] * saleRate[j] / 100);
                }
            }

            if(purchase[i] >= users[i][1]) {
                service += 1;
            }
            else {
                saleAmount += purchase[i];
            }
        }

        if(service == answer[0]) {
            answer[1] = Math.max(answer[1], saleAmount);
        }
        if(service > answer[0]) {
            answer[0] = service;
            answer[1] = saleAmount;
        }
    }

    public static void solve(int index, int[][] users, int[] emoticons) {
        if(index == emoticons.length) {
            compute(users, emoticons);
            return;
        }
        for(int i = 1; i <= 4; i++) {
            saleRate[index] = i * 10;
            solve(index + 1, users, emoticons);
        }
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        saleRate = new int[emoticons.length];
        solve(0, users, emoticons);
        return answer;
    }

    public static void main(String[] args) {
        int[][] user = new int[][] {{40, 10000}, {25, 10000}};
        int[] emoticons = new int[] {7000, 9000};
        solution(user, emoticons);

        user = new int[][] {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        emoticons = new int[] {1300, 1500, 1600, 4900};
        solution(user, emoticons);
    }
}
