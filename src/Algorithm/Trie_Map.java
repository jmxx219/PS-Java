package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Trie_Map {
    public static Node rootNode;

    public static class Node {
        public Map<Character, Node> childNode;
        public boolean endOfWord;

        public Node() {
            childNode = new HashMap<Character, Node>();
        }
    }

    public static void insert(String str) {
        Node node = rootNode;
        for(int i = 0; i < str.length(); i++) {
            node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
        }
        node.endOfWord = true;
    }


    public static boolean find(String str) {
        Node node = rootNode;

        for(int i=0; i<str.length(); i++) {
            node = node.childNode.getOrDefault(str.charAt(i), null);
            if(node == null) return false;
        }

        return node.endOfWord;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

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
