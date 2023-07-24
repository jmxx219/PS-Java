package Programmers.past;

import java.util.*;

// F
class 수식최대화 {
    private static final char[][] combinations = {{ '+', '-', '*' }, { '+', '*', '-' }, { '-', '*', '+' }, { '-', '+', '*' }, { '*', '-', '+' }, { '*', '+', '-' } };
    private List<Long> numList;
    private List<Character> opList;


    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private static long calc(long n1, long n2, char op) {
        if(op == '+') return n1 + n2;
        else if(op == '-') return n1 - n2;
        return n1 * n2;
    }

    private Long solve(char[] comb) {
        List<Long> num = new ArrayList<Long>();
        num.addAll(numList);

        List<Character> op = new ArrayList<>();
        op.addAll(opList);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < op.size(); j++) {
                if(comb[i] != op.get(j)) continue;

                long res = calc(num.get(j), num.get(j + 1), comb[i]);
                num.set(j, res);
                num.remove(j + 1);
                op.remove(j);
                j -= 1;
            }
        }

        return Math.abs(num.get(0));
    }

    public long solution(String expression) {
        numList = new ArrayList<>();
        opList = new ArrayList<>();

        String number = "";
        for(char c : expression.toCharArray()) {
            if (isOperator(c)) {
                numList.add(Long.parseLong(number));
                number = "";
                opList.add(c);
            }
            else number += c;
        }
        numList.add(Long.parseLong(number));

        long answer = 0;
        for (int i = 0; i < 6; i++) {
            answer = Math.max(answer, solve(combinations[i]));
        }
        return answer;
    }
}