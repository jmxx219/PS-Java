package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 파일정리_20291 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] file = br.readLine().split("\\.");
            map.put(file[1], map.getOrDefault(file[1], 0) + 1);
        }

        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        for(String file: keyList) {
            System.out.println(file + " " + map.get(file));
        }
    }
}
