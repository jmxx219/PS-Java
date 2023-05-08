package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {
    public Map<String, Integer> dict;
    public int dictIndex;
    List<Integer> res;

    public void solve(String msg) {
        for(int i = 0; i < msg.length(); i++) {
            int j = i;
            String s = "";
            for(; j < msg.length(); j++) {
                s = msg.substring(i, j + 1);
                if(!dict.containsKey(s)) break;
            }
            res.add(dict.get(msg.substring(i, j)));
            dict.put(s, dictIndex++);
            i = j - 1;
        }
    }

    public int[] solution(String msg) {
        res = new ArrayList<>();
        dict = new HashMap<>();

        dictIndex = 0;
        for(; dictIndex < 26; dictIndex++) {
            String a = String.valueOf((char)('A' + dictIndex));
            dict.put(a, dictIndex + 1);
        }
        dictIndex += 1;

        solve(msg);
        return res.stream().mapToInt(i -> i).toArray();
    }
}
