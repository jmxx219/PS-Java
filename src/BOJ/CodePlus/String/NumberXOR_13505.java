package BOJ.CodePlus.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class NumberXOR_13505 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Node rootNode;
    private static String[] numBinary;

    private static class Node {
        public Map<Character, Node> childNode;
        public boolean endOfWord;

        public Node() {
            this.childNode = new HashMap<>();
        }
    }

    private static void insert(String str) {
        Node curr = rootNode;
        for (int i = 0; i < str.length(); i++) {
            curr = curr.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
        }
        curr.endOfWord = true;
    }

    private static void find2(Node curr, String str, int index, StringBuilder sb) {
        if(index == str.length()) return;

        char xor = str.charAt(index) == '0' ? '1' : '0';

        if(curr.childNode.containsKey(xor)) {
            find2(curr.childNode.get(xor), str, index + 1, sb.append(xor));
        }
        else {
            find2(curr.childNode.get(str.charAt(index)), str, index + 1, sb.append(str.charAt(index)));
        }
    }

    public static Integer find(String str) {
        Node node = rootNode;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            char xor = (str.charAt(i) == '0') ? '1' : '0';
            if(!node.childNode.containsKey(xor)) xor = (xor == '0') ? '1' : '0';

            node = node.childNode.get(xor);
            sb.append(xor);
        }

        return Integer.parseInt(String.valueOf(sb), 2);
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int max = 0;
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if(max < nums[i]) max = nums[i];
        }
        int max_len = Integer.toBinaryString(max).length();

        rootNode = new Node();
        numBinary = new String[N];
        for (int i = 0; i < N; i++) {
            String b = Integer.toBinaryString(nums[i]);
            numBinary[i] = "0".repeat(max_len - b.length()) + b;
            insert(numBinary[i]);
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            find2(rootNode, numBinary[i], 0, sb);
            int x = Integer.parseInt(String.valueOf(sb), 2);
            if(res < (nums[i] ^ x)) res = (nums[i] ^ x);
        }

//        for (int i = 0; i < N; i++) {
//            int x = find(numBinary[i]);
//            if(res < (nums[i] ^ x)) res = (nums[i] ^ x);
//        }

        System.out.println(res);
    }
}
