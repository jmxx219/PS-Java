package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // EOF까지 입력
        while(sc.hasNextInt()){ }
        
        // Stream -> String 변환
        Stack<Integer> stack = new Stack<>();
        stack.stream().map(Objects::toString).collect(Collectors.joining());

        // string <-> char[] 변환
        String str = "";
        char[] charArr = str.toCharArray();
        String st = Arrays.toString(charArr);

        // string -> List<Character>
        List<Character> op = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        // int[] 배열 출력
        int[] memo = new int[3];
        Arrays.stream(memo).mapToObj(Objects::toString).collect(Collectors.joining(" "));
    }
}
