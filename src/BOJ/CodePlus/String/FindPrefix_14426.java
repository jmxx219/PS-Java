package BOJ.CodePlus.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FindPrefix_14426 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static Node rootNode;

    private static class Node {
        public Map<Character, Node> childNode;
        public boolean endOfNode;

        public Node() {
            this.childNode = new HashMap<>();
        }
    }

    private static void insert(String str) {
        Node curr = rootNode;
        for (int i = 0; i < str.length(); i++) {
            curr = curr.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
        }
        curr.endOfNode = true;
    }

    private static boolean find(String str) {
        Node curr = rootNode;
        for (int i = 0; i < str.length(); i++) {
            curr = curr.childNode.getOrDefault(str.charAt(i), null);
            if(curr == null) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rootNode = new Node();
        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }

        int res = 0;
        for (int i = 0; i < M; i++) {
            if(find(br.readLine())) res += 1;
        }

        System.out.println(res);
    }
}
