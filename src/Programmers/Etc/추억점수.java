package Programmers.Etc;

import java.util.HashMap;
import java.util.Map;

public class 추억점수 {
    public Map<String, Integer> score;

    private void setMap(String[] name, int[] yearning) {
        for(int i = 0; i < name.length; i++) {
            score.put(name[i], yearning[i]);
        }
    }

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        score = new HashMap<>();
        setMap(name, yearning);

        int[] answer = new int[photo.length];

        for(int i = 0; i < photo.length; i++) {
            int tmp = 0;
            for(String p : photo[i]) {
                tmp += score.getOrDefault(p, 0);
            }
            answer[i] = tmp;
        }

        return answer;
    }
}
