package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RankSearch {
    static HashMap<String, List<Integer>> infoScore;
    static final int INFO_CNT  = 4;

    static int rankSearch(String query, int queryScore) {
        if(!infoScore.containsKey(query)) return 0;

        List<Integer> scores = infoScore.get(query);

        int start = 0, end = scores.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(scores.get(mid) < queryScore) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return scores.size() - start;
    }

    static void setInfoScore(String[] infoArr, String info, int index) {
        if(index == INFO_CNT) {
            if(!infoScore.containsKey(info)) {
                infoScore.put(info, new ArrayList<>());
            }
            infoScore.get(info).add(Integer.parseInt(infoArr[INFO_CNT]));

            return;
        }
        setInfoScore(infoArr, info + "-", index + 1);
        setInfoScore(infoArr, info + infoArr[index], index + 1);
    }

    static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        infoScore = new HashMap<>();
        for (int i = 0; i < info.length; i++) {
            setInfoScore(info[i].split(" "), "", 0);
        }

        for (String key : infoScore.keySet()) {
            Collections.sort(infoScore.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            String[] queryArr = query[i].replaceAll(" and " , "").split(" ");
            answer[i] = rankSearch(queryArr[0], Integer.parseInt(queryArr[1]));
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(solution(info, query)));
    }
}
