package BOJ.CodePlus.Sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Stable sort: 상대적인 위치가 유지되는 정렬
 * - merge sort, bubble sort
 * - 정렬할 때 기존 순서를 유지하는 방법도 있음
 */
public class AgeSort_10814 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Person[] person;

    public static class Person implements Comparable<Person> {
        int age;
        String name;
        int order;

        public Person(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }

        @Override
        public int compareTo(Person o) {
            if(this.age == o.age) return this.order - o.order;
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        person = new Person[N];
        int order = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            person[i] = new Person(Integer.parseInt(st.nextToken()), st.nextToken(), order++);
        }

        Arrays.sort(person);

        for (int i = 0; i < N; i++) {
            System.out.println(person[i].age + " " + person[i].name);
        }
    }
}
