package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CandidateKey {
    static int colSize = 0;
    static int studentSize = 0;
    static boolean[] selectedAttr;

    public static boolean isMinimal(int i, List<Integer> candidateKey) {
        for(int key : candidateKey) {
            if((i & key) == key) return false;
        }
        return true;
    }

    public static boolean isUnique(String[][] relation) {
        Set<String> tuple = new HashSet<>();

        for (int i = 0; i < studentSize; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < colSize; j++) {
                if(!selectedAttr[j]) continue;
                sb.append(relation[i][j]);
            }
            tuple.add(sb.toString());
        }

        return tuple.size() == studentSize;
    }

    public static int findSet(String[][] relation) {
        List<Integer> candidateKey = new ArrayList<>();
        for (int i = 1; i < (1 << colSize); i++) {
            selectedAttr = new boolean[colSize];
            for (int j = 0; j < colSize; j++) {
                if((i & (1 << j)) != 0) selectedAttr[j] = true;
            }
            for (int j = 0; j < colSize; j++) {
                if(!selectedAttr[j]) continue;
                System.out.print(j + " ");
            }

            if(isUnique(relation) && isMinimal(i, candidateKey)) {
                candidateKey.add(i);
            }
        }
        return candidateKey.size();
    }


    public static int solution(String[][] relation) {
        studentSize = relation.length;
        colSize = relation[0].length;
        return findSet(relation);
    }

    public static void main(String[] args) {
        String[][] relation = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };
        System.out.println(solution(relation));
    }
}
