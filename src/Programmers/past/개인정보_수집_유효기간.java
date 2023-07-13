package Programmers.past;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 개인정보_수집_유효기간 {
    public Map<String, Integer> termMap;
    public int Y;
    public int M;
    public int D;

    public boolean isOk(int year, int month, int day) {
        if(year < Y) return false;
        else if(year == Y && month < M) return false;
        else if(year == Y && month == M && day < D) return false;

        return true;
    }

    public int[] solve(String[] privacies) {
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < privacies.length; i++) {
            String[] p = privacies[i].split(" ");
            int validTerm = termMap.get(p[1]);
            int year = Integer.parseInt(p[0].substring(0, 4));
            int month = Integer.parseInt(p[0].substring(5, 7));
            int day = Integer.parseInt(p[0].substring(8, 10));

            month += validTerm;

            if(month % 12 != 0) {
                year += month / 12;
                month %= 12;
            }
            else {
                while(month != 12) {
                    year += 1;
                    month -= 12;
                }
            }

            day -= 1;
            if(day == 0) {
                month -= 1;
                day = 28;
            }

            if(month == 0) {
                year -= 1;
                month = 12;
            }

            if(!isOk(year, month, day)) res.add(i + 1);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        termMap = new HashMap<>();

        for(String term : terms) {
            String[] t = term.split(" ");
            termMap.put(t[0], Integer.parseInt(t[1]));
        }

        Y = Integer.parseInt(today.substring(0, 4));
        M = Integer.parseInt(today.substring(5, 7));
        D = Integer.parseInt(today.substring(8, 10));

        return solve(privacies);
    }
}