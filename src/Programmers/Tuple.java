package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 튜플
public class Tuple {
    public static int[] solution(String s) {
        List<List<Integer>> tuple = new ArrayList<>();

        String ss = s.replaceAll("},", " ").replaceAll("[{}]", "");
        String[] st = ss.split(" ");
        for (int i = 0; i < st.length; i++) {
            List<Integer> a = new ArrayList<>();
            for(String x : st[i].split(",")) {
                a.add(Integer.parseInt(x));
            }
            tuple.add(a);
        }

        Collections.sort(tuple, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.size() - o2.size();
            }
        });


        Set<Integer> set = new HashSet<>();
        int[] answer = new int[tuple.get(tuple.size() - 1).size()];
        int index = 0;
        for (int i = 0; i < tuple.size(); i++) {
            for (int j = 0; j < tuple.get(i).size(); j++) {
                int x = tuple.get(i).get(j);
                if(!set.contains(x)) {
                    answer[index] = x;
                    index += 1;
                    set.add(x);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        for(int x : solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")) {
            System.out.print(x + " ");
        }
        System.out.println();

        for(int x : solution("{{20,111},{111}}")) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
