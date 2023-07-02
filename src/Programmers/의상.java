package Programmers;
import java.util.*;

// 40m(Pass)
public class 의상 {
    private Cloth[] names;
    private String[] picked;
    private int answer;

    public class Cloth {
        String name;
        int kind;
        public Cloth(String name, int kind) {
            this.name = name;
            this.kind = kind;
        }
    }

    private void solve(int start, int index, boolean[] visited) {
        if(index > 0) {
            answer += 1;
            if(index == picked.length) return;
        }

        for(int i = start; i < names.length; i++) {
            if(!visited[names[i].kind]) {
                visited[names[i].kind] = true;
                picked[index] = names[i].name;
                solve(i + 1, index + 1, visited);
                visited[names[i].kind] = false;
            }
        }

    }

    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        names = new Cloth[clothes.length];
        for(int i = 0; i < clothes.length; i++) {
            if(!map.containsKey(clothes[i][1])) {
                map.put(clothes[i][1], map.size());
            }
            names[i] = new Cloth(clothes[i][0], map.get(clothes[i][1]));
        }

        answer = 0;
        picked = new String[map.size()];
        solve(0, 0, new boolean[map.size()]);

        return answer;
    }
}