package Programmers.past;

public class 문자열압축 {

    public static String[] splits(String s, int cnt) {
        String[] st = new String[s.length() / cnt  + (s.length() % cnt == 0 ? 0 : 1)];
        int i = 0;
        int index = 0;
        while (i + cnt < s.length()) {
            st[index] = s.substring(i, i + cnt);
            index += 1;
            i += cnt;
        }

        if(i < s.length()) st[index] = s.substring(i, s.length());

        return st;
    }

    public static int makeZip(String[] st) {
        String tmpSt = "";
        for (int i = 0; i < st.length; i++) {
            int cnt = 1;
            int j = i + 1;
            while (j < st.length && st[i].equals(st[j])) {
                cnt += 1;
                j += 1;
            }
            if(cnt != 1) tmpSt += String.valueOf(cnt);
            tmpSt += st[i];
            i = j - 1;
        }
        return tmpSt.length();
    }

    public static int solution(String s) {
        if(s.length() == 1) return 1;

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < s.length(); i++) {
            String[] st = splits(s, i);
            answer = Math.min(answer, makeZip(st));
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("a"));
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

}
