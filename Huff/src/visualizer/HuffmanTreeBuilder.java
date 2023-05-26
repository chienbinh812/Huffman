package visualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTreeBuilder {
    public HuffmanTree buildHuffmanTree(String inputText) {

        Map<Character, Integer> frequencyMap = calculateFrequency(inputText);

        // tạo một hàng đợi ưu tiên (priority queue) và truyền tần số tính được
        PriorityQueue<Node> priorityQueue = createPriorityQueue(frequencyMap);
        Node root = buildTree(priorityQueue);

        // Construct the Huffman tree
        return new HuffmanTree(root);
    }

    private Map<Character, Integer> calculateFrequency(String inputText) { // tính toán tần số xuất hiện của các kí tự trong văn bản đầu vào và trả về Map chứa tần số của các kí tự.
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : inputText.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }
    //tạo và trả về một priority queue chứa các nút dựa trên tần số của các kí tự.
    private PriorityQueue<Node> createPriorityQueue(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> n1.getWeight() - n2.getWeight());
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }
        return priorityQueue;
    }
    // xây dựng cây Huffman từ hàng đợi ưu tiên của các nút, sau khi hoàn thành vòng lặp, phương thức trả về nút gốc của cây Huffman
    private Node buildTree(PriorityQueue<Node> priorityQueue) {
        while (priorityQueue.size() > 1) {
            Node leftChild = priorityQueue.poll();
            Node rightChild = priorityQueue.poll();
            int combinedWeight = leftChild.getWeight() + rightChild.getWeight();
            Node parent = new Node('\0', combinedWeight);
            parent.setLeftChild(leftChild);
            parent.setRightChild(rightChild);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }
}
