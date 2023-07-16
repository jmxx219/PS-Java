package Programmers.Etc;

import java.util.*;

// Pass(40m)
class 귤고르기 {
    public HashMap<Integer, Integer> map;
    public List<Integer> fruits;

    public int solution(int k, int[] tangerine) {
        map = new HashMap<>();
        fruits = new ArrayList<>();

        for(int t : tangerine) {
            if(!map.containsKey(t)) {
                map.put(t, map.size());
                fruits.add(0);
            }
            int index = map.get(t);
            fruits.set(index, fruits.get(index) + 1);
        }


        Collections.sort(fruits, Collections.reverseOrder());

        int answer = 0;

        for(int x : fruits) {
            k -= x;
            answer += 1;
            if(k <= 0) break;
        }


        return answer;
    }
}