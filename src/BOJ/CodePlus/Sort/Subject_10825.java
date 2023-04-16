package BOJ.CodePlus.Sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Subject_10825 {
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Subject[] subject;

    public static class Subject implements Comparable<Subject> {
        String name;
        int korean;
        int english;
        int math;

        public Subject(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Subject o) {
            if(this.korean == o.korean) {
                if(this.english == o.english) {
                    if(this.math == o.math) return this.name.compareTo(o.name);
                    return o.math - this.math;
                }
                return this.english - o.english;
            }
            return o.korean - this.korean;
        }
    }

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        subject = new Subject[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            subject[i] = new Subject(st.nextToken(), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(subject);

        for (int i = 0; i < N; i++) {
            System.out.println(subject[i].name);
        }
    }
}
