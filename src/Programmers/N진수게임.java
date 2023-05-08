package Programmers;

class N진수게임 {

    public String solution(int n, int t, int m, int p) {
        int num = 0;
        int numLen = 0;
        StringBuilder sb = new StringBuilder();
        while(numLen <= t * m) {
            String strNum = Integer.toString(num, n);
            sb.append(strNum);
            num += 1;
            numLen += strNum.length();
        }

        StringBuilder res = new StringBuilder();
        int index = p - 1;
        for(int i = 0; i < t; i++) {
            res.append(sb.charAt(index));
            index += m;
        }

        return res.toString().toUpperCase();
    }
}