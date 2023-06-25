package Programmers;

public class 연속된_부분수열의_합 {

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int left = 0, right = 0;
        int pSum = sequence[0];
        int size = sequence.length + 1;

        while(left <= right && right < sequence.length) {
            if(pSum < k && right + 1 < sequence.length) {
                right += 1;
                pSum += sequence[right];
                continue;
            }
            else if(pSum == k) {
                int nextSize = right - left + 1;
                if(size > nextSize) {
                    size = nextSize;
                    answer[0] = left;
                    answer[1] = right;
                }
                else if(size == nextSize && answer[0] > left) {
                    answer[0] = left;
                    answer[1] = right;
                }
            }
            pSum -= sequence[left];
            left += 1;
        }


        return answer;
    }
}
