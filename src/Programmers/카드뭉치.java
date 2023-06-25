package Programmers;

public class 카드뭉치 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < cards1.length && j < cards2.length && k < goal.length) {
            if(goal[k].equals(cards1[i])) {
                i += 1;
                k += 1;
            }
            else if(goal[k].equals(cards2[j])) {
                j += 1;
                k += 1;
            }
            else break;
        }

        while(i < cards1.length && k < goal.length && goal[k].equals(cards1[i])) {
            i += 1;
            k += 1;
        }

        while(j < cards2.length && k < goal.length && goal[k].equals(cards2[j])) {
            j += 1;
            k += 1;
        }

        return k == goal.length ? "Yes" : "No";
    }
}