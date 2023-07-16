package Programmers.past;

import java.util.*;

//Pass
class 실패율 {
    static class Stage {
        public int num;
        public double fail;
        public Stage(int num, double fail) {
            this.num = num;
            this.fail = fail;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] stagePlayer = new int[N + 2];

        for(int i = 0; i < stages.length; i++) {
            stagePlayer[stages[i]] += 1;
        }

        Stage[] stage = new Stage[N];
        double player = stages.length;
        for(int i = 1; i <= N; i++) {
            double fail = stagePlayer[i] / player;
            if(Double.isNaN(fail)) fail = 0;
            stage[i - 1] = new Stage(i, fail);
            player -= stagePlayer[i];
        }

        Arrays.sort(stage, new Comparator<Stage>() {
            @Override
            public int compare(Stage s1, Stage s2) {
                return Double.compare(s2.fail, s1.fail);
            }
        });

        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            answer[i] = stage[i].num;
        }

        return answer;
    }
}