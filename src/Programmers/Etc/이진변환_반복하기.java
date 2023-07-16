package Programmers.Etc;

// Pass(10m)
class 이진변환_반복하기 {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int cnt = 0;
        int zero = 0;

        while(true) {
            if(s.length() == 1 && s.charAt(0) == '1') break;
            int one = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '1') one += 1;
            }
            cnt += 1;
            zero += (s.length() - one);
            s = Integer.toBinaryString(one);
        }

        answer[0] = cnt;
        answer[1] = zero;

        return answer;
    }
}