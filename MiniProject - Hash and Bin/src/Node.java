public class Node {
    String word;
    int freq;
    Node left;
    Node right;

    public Node(String w) {
        word = w;
        freq = 1;
        left = null;
        right = null;
    }
}