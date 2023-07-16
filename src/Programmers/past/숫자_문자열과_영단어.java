package Programmers.past;

// Pass
class 숫자_문자열과_영단어 {

    private final String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int solve(String s, int index) {
        char c = s.charAt(index);

        switch(c) {
            case 'z':
                return 0;
            case 'o':
                return 1;
            case 'e':
                return 8;
            case 'n':
                return 9;
            case 't':
                if(s.charAt(index + 1) == 'w') return 2;
                return 3;
            case 'f':
                if(s.charAt(index + 1) == 'o') return 4;
                return 5;
            case 's':
                if(s.charAt(index + 1) == 'i') return 6;
                return 7;
        }
        return -1;
    }

    public int solution(String s) {

        String res = "";

        for(int i = 0; i < s.length(); i++) {

            if(Character.isDigit(s.charAt(i))) res += s.charAt(i);
            else {
                int x = solve(s, i);
                res += String.valueOf(x);
                i += words[x].length() - 1;
                // System.out.println(i);
                // break;
            }
        }

        return Integer.parseInt(res);
    }
}
