package visualizer;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private HuffmanTree huffmanTree;

    public DrawingPanel() {
        setPreferredSize(new Dimension(800, 600));
    }

    public void setHuffmanTree(HuffmanTree huffmanTree) {
        this.huffmanTree = huffmanTree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (huffmanTree != null) {
            drawTree(g, huffmanTree.getRoot(), getWidth() / 2, 50, getWidth() / 2);
        }
    }

    private void drawTree(Graphics g, Node node, int x, int y, int levelWidth) {
        if (node == null) {
            return;
        }

        int nodeRadius = 25;
        int nodeDiameter = 2 * nodeRadius;

        // Tùy chỉnh màu sắc cho node
        if (node.isLeaf()) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.CYAN);
        }

        g.fillOval(x - nodeRadius, y - nodeRadius, nodeDiameter, nodeDiameter);
        g.setColor(Color.BLACK);
        g.drawOval(x - nodeRadius, y - nodeRadius, nodeDiameter, nodeDiameter);

        // Vẽ giá trị ký tự và trọng số
        g.setColor(Color.BLACK);
        g.drawString(Character.toString(node.getCharacter()), x - 5, y + 5);
        g.drawString(Integer.toString(node.getWeight()), x - 5, y + 20);

        if (node.getLeftChild() != null) {
            int leftX = x - levelWidth / 2;
            int leftY = y + 70;
            g.setColor(Color.BLACK);
            g.drawLine(x, y + nodeRadius, leftX, leftY - nodeRadius);
            drawTree(g, node.getLeftChild(), leftX, leftY, levelWidth / 2);
        }

        if (node.getRightChild() != null) {
            int rightX = x + levelWidth / 2;
            int rightY = y + 70;
            g.setColor(Color.BLACK);
            g.drawLine(x, y + nodeRadius, rightX, rightY - nodeRadius);
            drawTree(g, node.getRightChild(), rightX, rightY, levelWidth / 2);
        }
    }
}
