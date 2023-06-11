package Algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * Trie(Prefix Tree): 한 노드는 어떤 문자열의 접두사
 * 숫자 비교: O(1)
 * 문자열 비교: O(길이)
 */
public class Trie_List {
    private static final int ALPABETS = 26;
    private static List<Node> trie;
    private static int root;

    public static class Node {
        int[] children;
        boolean valid; // false: 중간 과정

        public Node() {
            children = new int[ALPABETS];
            Arrays.fill(children, -1);
        }
    }

    private static int init() {
        Node x = new Node();
        trie.add(x);
        return trie.size() - 1; // index 리턴
    }

    /**
     * @param node: 탐색하고 있는 노드의 인덱스
     * @param s: 추가하고 있는 문자열
     * @param index: s의 index 추가
     */
    private static void add(int node, String s, int index) {
        if(index == s.length()) {
            trie.get(node).valid = true;
            return;
        }

        int c = s.charAt(index) - 'a';
        if(trie.get(node).children[c] == -1) {
            int next = init();
            trie.get(node).children[c] = next;
        }

        int child = trie.get(node).children[c];
        add(child, s,  index + 1);
    }

    private static boolean search(int node, String s, int index) {
        if(node == -1) return false;
        if(index == s.length()) return trie.get(node).valid;
        int c = s.charAt(index) - 'a';
        int child = trie.get(node).children[c];
        return search(child, s,  index + 1);
    }

}
