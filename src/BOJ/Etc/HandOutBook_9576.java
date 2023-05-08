package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class HandOutBook_9576 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T; // 책
    private static int N; // 책
    private static int M; // 학생
    private static List<Student> students;

    public static class Student implements Comparable<Student> {
        int student;
        int a, b;

        public Student(int student, int a, int b) {
            this.student = student;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Student o) {
            if(this.b == o.b) return this.a - o.a;
            return this.b - o.b;
        }
    }

    public static int solve() {
        Collections.sort(students);
        boolean[] isBooked = new boolean[N + 1];
        int cnt = 0;

        for(Student s : students) {
            for (int i = s.a; i <= s.b; i++) {
                if(!isBooked[i]) {
                    isBooked[i] = true;
                    cnt += 1;
                    break;
                }
            }
        }

        return cnt;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            students = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // a
                int b = Integer.parseInt(st.nextToken()); // b
                students.add(new Student(i, a, b));
            }

            System.out.println(solve());
        }
    }
}
