package Programmers;

import java.util.*;

import java.util.stream.Collectors;

public class 외톨이알파벳 {
    public static String solution(String input_string) {
        Set<String> res = new HashSet<>();
        Set<String> set = new HashSet<>();

        int i = 0;
        while(i < input_string.length()){
            int j = i + 1;
            while(j < input_string.length()
                    && input_string.charAt(i) == input_string.charAt(j)) {
                j += 1;
            }
            String x = String.valueOf(input_string.charAt(i));
            if(set.contains(x)) res.add(x);
            else set.add(x);
            i = j;
        }

        if(res.isEmpty()) return "N";

        List<String> list = new ArrayList<>(res);
        Collections.sort(list);
        return list.stream().collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(solution("e"));
        System.out.println(solution("eeddee"));
        System.out.println(solution("eeedee"));
    }
}
