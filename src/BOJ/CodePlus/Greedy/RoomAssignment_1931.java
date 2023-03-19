package BOJ.CodePlus.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class RoomAssignment_1931 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<Meeting> meetings;

    public static class Meeting implements Comparable<Meeting> {
        int start, end;
        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Meeting m) {
            int comp = Integer.compare(end, m.end);
            return comp == 0 ? Integer.compare(start, m.start) : comp;
        }
    }

    private static int solve() {
        int res = 0;

        int currTime = 0;
        for(Meeting mt : meetings) {
            if(currTime <= mt.start) {
                currTime = mt.end;
                res += 1;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        meetings = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(meetings);

        System.out.println(solve());
    }
}
