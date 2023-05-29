package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 문자열 검색
// pi[i] = P의 i까지 부분 문자열에서 prefix == suffix 될 수 있는 부분 문자열 중에서 가장 긴 것의 길이
public class KMP {
    public static char[] S; // txt
    public static char[] P; // pattern

    public static int[] getPartialMatchNative(){
        int[] pi = new int[P.length];
        for (int begin = 1; begin < P.length; begin++) {
            for (int i = 0; i + begin < P.length; i++) {
                if(P[begin + i] != P[i]) break;
                pi[begin + i] = Math.max(pi[begin + i], i + 1);
            }
        }
        return pi;
    }
    
    public static int[] getPartialMatch(){
        int[] pi = new int[P.length];

        int begin = 1, matched = 0;
        while(begin + matched < P.length){
            if(P[begin + matched] == P[matched]){
//                matched += 1;
//                pi[begin + matched - 1] = matched;
                pi[begin + matched] = matched + 1;
                matched += 1;
            }
            else{
                if(matched == 0) begin += 1;
                else{
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return pi;
    }

    public static List<Integer> kmpSearch(){
        List<Integer> res = new ArrayList<>();
        int[] pi = getPartialMatch();

        int begin = 0, matched = 0;
        while(begin <= S.length - P.length){
            if(matched < P.length && S[begin + matched] == P[matched]){
                matched += 1;
                if(matched == P.length) res.add(begin);
            }
            else{
                if(matched == 0) begin++;
                else{
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return res;
    }

    public static List<Integer> kmpSearch2(){
        List<Integer> res = new ArrayList<>();
        int[] pi = getPartialMatch();

        int matched = 0;
        for (int i = 0; i < S.length; i++) { // i = begin + matched
            while (matched > 0 &&  S[i] != P[matched]) {
                matched = pi[matched - 1];
            }
            if(S[i] == P[matched]) {
                matched += 1;
                if(matched == P.length) {
                    res.add(i - P.length + 1);
                    matched = pi[matched - 1];
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "ABCABDABCABCABEF";
        String p = "AABAAA";
        S = s.toCharArray();
        P = p.toCharArray();

        List<Integer> res = kmpSearch();
        res.stream().forEach(index -> System.out.print(index + " "));
    }

}
