package Programmers;

import java.util.*;

// 20m(Pass)
public class 롤케이크_자르기 {
    public int solution(int[] topping) {
        int[] left = new int[topping.length];
        int[] right = new int[topping.length];

        Set<Integer> lSet = new HashSet<>();
        Set<Integer> rSet = new HashSet<>();

        for(int i = 0; i < topping.length; i++) {
            if(!lSet.contains(topping[i])) {
                left[i] = (i == 0) ? 1 : left[i - 1] + 1;
                lSet.add(topping[i]);
            }
            else left[i] = left[i - 1];
        }

        for(int i = topping.length - 1; i >= 0; i--) {
            if(!rSet.contains(topping[i])) {
                right[i] = (i == topping.length - 1) ? 1 : right[i + 1] + 1;
                rSet.add(topping[i]);
            }
            else right[i] = right[i + 1];
        }

        int answer = 0;
        for(int i = 0; i < topping.length - 1; i++) {
            if(left[i] == right[i + 1]) answer += 1;
        }

        return answer;
    }
}