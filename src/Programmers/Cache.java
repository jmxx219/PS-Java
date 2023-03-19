package Programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cache {

    public static int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;

        Map<String, Integer> cache = new HashMap<>();
        int totalTime = 0;

        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
            if(cache.containsKey(cities[i])) {
                totalTime += 1;
            }
            else {
                if(cache.size() >= cacheSize) {
                    String tmpCity = "";
                    int tmpTime = Integer.MAX_VALUE;
                    for(Entry<String, Integer> c : cache.entrySet()) {
                        if(tmpTime > c.getValue()) {
                            tmpTime = c.getValue();
                            tmpCity = c.getKey();
                        }
                    }
                    cache.remove(tmpCity);
                }
                totalTime += 5;
            }
            cache.put(cities[i], i);
        }

        return totalTime;
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize, cities));

        cacheSize = 0;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize, cities));

        cacheSize = 1;
        cities = new String[]{"Jeju", "Jeju", "Jeju"};
        System.out.println(solution(cacheSize, cities));

        cacheSize = 2;
        cities = new String[]{"Jeju", "Seoul", "Pangyo", "Seoul", "Seoul", "Jeju", "Pangyo"};
        System.out.println(solution(cacheSize, cities));
    }
}

