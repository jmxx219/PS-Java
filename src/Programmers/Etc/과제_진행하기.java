package Programmers.Etc;

import java.util.*;

// Pass
class 과제_진행하기 {
    private List<Plan> planList;
    private Stack<Plan> remain;
    private String[] answer;
    private int N;
    private int resIndex;

    public static class Plan {
        String name;
        int start;
        int playTime;
        public Plan(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
    }

    public int convertStartTime(String start) {
        int hour = Integer.parseInt(start.substring(0, 2));
        int minute = Integer.parseInt(start.substring(3, 5));
        int total = hour * 60 + minute;
        return total;
    }

    public int calc(Plan now, int currTime, int nIndex) {
        int planEndTime = currTime + now.playTime;

        int nextTime = (nIndex < N) ? planList.get(nIndex).start : Integer.MAX_VALUE;

        if(nIndex < N && planEndTime > nextTime) {
            int remainTime = planEndTime - nextTime;
            remain.add(new Plan(now.name, -1, remainTime));
            currTime = nextTime;
        }
        else {
            currTime = planEndTime;
            answer[resIndex++] = now.name;
        }
        return currTime;
    }

    public void solve() {
        int currTime = planList.get(0).start;
        remain = new Stack<>();
        int index = 0;

        while(true) {
            if(remain.isEmpty() && index >= N) break;

            if(index < N && currTime == planList.get(index).start) {
                currTime = calc(planList.get(index), currTime, index + 1);
                index += 1;
            }
            else if(index < N && remain.isEmpty()) {
                currTime = planList.get(index).start;
            }
            else if(!remain.isEmpty()) {
                currTime = calc(remain.pop(), currTime, index);
            }
        }

    }

    public String[] solution(String[][] plans) {
        planList = new ArrayList();

        for(String[] plan : plans) {
            planList.add(new Plan(plan[0], convertStartTime(plan[1]),
                    Integer.parseInt(plan[2])));
        }

        Collections.sort(planList, new Comparator<Plan>() {
            @Override
            public int compare(Plan p1, Plan p2) {
                return p1.start - p2.start;
            }
        });

        N = planList.size();
        answer = new String[N];
        resIndex = 0;
        solve();
        return answer;
    }
}
