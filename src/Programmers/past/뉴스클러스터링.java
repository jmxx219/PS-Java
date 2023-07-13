package Programmers.past;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 뉴스클러스터링 {
    static Map<String, Integer> map1;
    static Map<String, Integer> map2;
    static Set<String> all;

    public static List<String> strSplit(String str) {
        List<String> a = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String s = str.substring(i, i + 2);
            if(Character.isAlphabetic(s.charAt(0)) && Character.isAlphabetic(s.charAt(1))) {
                a.add(s);
            }
        }
        return a;
    }

    public static void makeSet(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> s1 = strSplit(str1);
        for(String ss : s1) {
            map1.put(ss, map1.getOrDefault(ss, 0) + 1);
        }

        List<String> s2 = strSplit(str2);
        for(String ss : s2) {
            map2.put(ss, map2.getOrDefault(ss, 0) + 1);
        }

        for(String s : s1) all.add(s);
        for(String s : s2) all.add(s);
    }

    public static int solution(String str1, String str2) {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        all = new HashSet<>();

        makeSet(str1, str2);

        int a1 = 0;
        for(String xx : all) {
            if(map1.containsKey(xx) && map2.containsKey(xx)) {
                a1 += Math.max(map1.get(xx), map2.get(xx));
            }
            else if(map1.containsKey(xx)) a1 += map1.get(xx);
            else if(map2.containsKey(xx)) a1 += map2.get(xx);
        }

        int b1 = 0;
        for(String xx : all) {
            if(map1.containsKey(xx) && map2.containsKey(xx)) {
                b1 += Math.min(map1.get(xx), map2.get(xx));
            }
        }

        if(a1 == 0 && b1 == 0) return 1 * 65536;

        double res = (b1 / (double) a1) * 65536;
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }
}
