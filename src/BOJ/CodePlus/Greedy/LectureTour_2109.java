package BOJ.CodePlus.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LectureTour_2109 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Lecture[] lecture;

    private static class Lecture implements Comparable<Lecture> {
        int p, d;

        public Lecture(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.d == o.d) return o.p - this.p;
            return o.d - this.d;
        }
    }
    public static int solve() {
        Arrays.sort(lecture);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int money = 0;
        int index = 0;
        for (int i = 10000; i >= 1; i--) {
            while (index < N && lecture[index].d == i) {
                pq.offer(-lecture[index].p);
                index += 1;
            }
            if(!pq.isEmpty()) money += -pq.poll();
        }

        return money;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        lecture = new Lecture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lecture[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(solve());
    }
}
