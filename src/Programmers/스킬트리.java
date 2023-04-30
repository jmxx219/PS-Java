package Programmers;

import java.util.Arrays;

public class 스킬트리 {
    public int learn(String skill, String tree) {
        for(int i = 0; i < tree.length(); i++) {
            if(skill.charAt(i) != tree.charAt(i)) return 0;
        }
        return 1;
    }

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String tree : skill_trees) {
            answer += learn(skill, tree.replaceAll("[^" + skill + "]", ""));
        }
        return answer;
    }

}
