package Programmers.Etc;

import java.util.*;

// Pass(20m)
class H_Index {
    public int solution(int[] citations) {
        int N = citations.length;

        Arrays.sort(citations);

        int answer = 0;
        for(int i = 0; i < N; i++){
            if (citations[i] >= N - i) {
                answer = N - i;
                break;
            }
        }

        return answer;
    }
}
