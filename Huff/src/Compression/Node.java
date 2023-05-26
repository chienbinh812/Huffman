package Compression;

public class Node {
    private int weight;
    private char character;
    private Node left;
    private Node right;

    public Node(int weight, char character) {
        this.weight = weight;
        this.character = character;
        this.left = null;
        this.right = null;
    }

    public Node(int weight, Node left, Node right) {
        this.weight = weight;
        this.character = '\0';
        this.left = left;
        this.right = right;
    }

    public int getWeight() { return weight; }
    public char getCharacter() { return character; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }

    public boolean isLeaf() {
        return left == null && right == null && character != '\0';
    }
}
