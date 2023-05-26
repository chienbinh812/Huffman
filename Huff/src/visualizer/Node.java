package visualizer;

public class Node {
    private char character; // Biểu diễn kí tự của nút.
    private int weight; // trọng số của nút.
    private Node leftChild; // trỏ điến nút con trái của nút hiện tại
    private Node rightChild; //trỏ đến nút con phải của nút hiện tại

    public Node(char character, int weight) {
        this.character = character;
        this.weight = weight;
        this.leftChild = null;
        this.rightChild = null;
    }

    public char getCharacter() {
        return character;
    }

    public int getWeight() {
        return weight;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeaf() { //Kiểm tra xem nút hiện tại có phải là nút lá hay không.
        return leftChild == null && rightChild == null;
    }
}
