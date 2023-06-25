package Programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 호텔대실 {

    public int getTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        return hour * 60 + minute;
    }

    public int solution(String[][] book_time) {

        Arrays.sort(book_time, new Comparator<String[]>() {
            @Override
            public int compare(String[] h1, String[] h2) {
                return h1[0].compareTo(h2[0]);
            }
        });

        int answer = -1;
        int[] rooms = new int[book_time.length];
        Arrays.fill(rooms, -1);

        for(String[] book: book_time) {
            int bookStart = getTime(book[0]);
            int bookEnd = getTime(book[1]);
            for(int i = 0; i < rooms.length; i++) {
                if(rooms[i] == -1 || rooms[i] <= bookStart) {
                    if(i > answer) answer = i;
                    rooms[i] = bookEnd + 10;
                    break;
                }
            }
        }

        return answer + 1;
    }
}