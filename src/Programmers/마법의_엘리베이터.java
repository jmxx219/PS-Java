package Programmers;

public class 마법의_엘리베이터 {
    public int go(int storey) {
        if(storey <= 1) return storey;

        int divide = storey / 10;
        int remain = storey % 10;

        int down = remain + go(divide);
        int up = (10 - remain) + go(divide + 1);

        return Math.min(up, down);
    }

    public int solution(int storey) {
        return go(storey);
    }
}
