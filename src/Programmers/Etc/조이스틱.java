package Programmers.Etc;

import java.util.LinkedList;
import java.util.Queue;

// Pass(60m)
class 조이스틱 {
    public int N;

    public static class Word {
        public String word;
        public int cost;
        public int cursor;
        public Word(String word, int cost, int cursor) {
            this.word = word;
            this.cost = cost;
            this.cursor = cursor;
        }
    }

    public int solve(String start, String target) {
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(start, 0, 0));

        while(!queue.isEmpty()) {
            String here = queue.peek().word;
            int cost = queue.peek().cost;
            int cursor = queue.peek().cursor;
            queue.poll();

            int nextCost = cost;
            String next = here;

            if(here.charAt(cursor) != target.charAt(cursor)) {
                int cnt = Math.abs(target.charAt(cursor) - here.charAt(cursor));
                if(cnt > 26 - cnt) cnt = 26 - cnt;
                nextCost += cnt;

                char[] word = here.toCharArray();
                word[cursor] = target.charAt(cursor);

                next = String.valueOf(word);
            }

            if(next.equals(target)) return nextCost;

            int left = (cursor == 0) ? N - 1 : cursor - 1;
            int right = (cursor == N - 1) ? 0 : cursor + 1;
            queue.add(new Word(next, nextCost + 1, left));
            queue.add(new Word(next, nextCost + 1, right));
        }

        return 0;
    }

    public int solution(String name) {
        N = name.length();
        String start = "";
        for(int i = 0; i < N; i++) start += "A";

        return solve(start, name);
    }
}