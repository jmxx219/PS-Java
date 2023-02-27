package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Emoticon_14226 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int S;
    private static final int MAX = 1000;

    public static class Emoticon {
        public static int emo;
        public static int clip;

        public Emoticon(int emo, int clip) {
            this.emo = emo;
            this.clip = clip;
        }
    }

    private static void bfs() {
        Queue<Emoticon> queue = new LinkedList<>();
        int[][] time = new int[MAX + 1][MAX + 1];

        for (int i = 0; i < MAX + 1; i++) {
            Arrays.fill(time[i], -1);
        }

        queue.add(new Emoticon(1, 0));
        time[1][0] = 0;

        while (!queue.isEmpty()) {
            Emoticon here = queue.poll();
            int emo = here.emo;
            int clip = here.clip;

            if(emo == S) {
                System.out.println(time[emo][clip]);
                return;
            }

            if(time[emo][emo] == -1) {
                queue.add(new Emoticon(emo, emo));
                time[emo][emo] = time[emo][clip] + 1;
            }

            int nextEmo = emo + clip;
            if(clip > 0 && nextEmo <= MAX && time[nextEmo][clip] == -1) {
                queue.add(new Emoticon(nextEmo, clip));
                time[nextEmo][clip] = time[emo][clip] + 1;
            }

            nextEmo = emo - 1;
            if(nextEmo >= 0 && time[nextEmo][clip] == -1) {
                queue.add(new Emoticon(nextEmo, clip));
                time[nextEmo][clip] = time[emo][clip] + 1;
            }
        }
    }

    private static void bfs2() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] time = new int[MAX + 1][MAX + 1];

        for (int i = 0; i < MAX + 1; i++) {
            Arrays.fill(time[i], -1);
        }

        queue.add(new int[]{1, 0});
        time[1][0] = 0;

        while (!queue.isEmpty()) {
            int[] here = queue.poll();
            int emo = here[0];
            int clip = here[1];

            if(emo == S) {
                System.out.println(time[emo][clip]);
                return;
            }

            if(time[emo][emo] == -1) {
                queue.add(new int[]{emo, emo});
                time[emo][emo] = time[emo][clip] + 1;
            }

            int nextEmo = emo + clip;
            if(clip > 0 && nextEmo <= MAX && time[nextEmo][clip] == -1) {
                queue.add(new int[]{nextEmo, clip});
                time[nextEmo][clip] = time[emo][clip] + 1;
            }

            nextEmo = emo - 1;
            if(nextEmo >= 0 && time[nextEmo][clip] == -1) {
                queue.add(new int[]{nextEmo, clip});
                time[nextEmo][clip] = time[emo][clip] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());

        bfs();
    }
}
