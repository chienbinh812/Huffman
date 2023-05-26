package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HuffmanTreeVisualizer extends JFrame {

    private JButton startButton;
    private JButton loadButton;
    private DrawingPanel drawingPanel;
    private JTextArea outputTextArea;
    private JLabel statusLabel;
    private HuffmanTreeBuilder treeBuilder;

    public HuffmanTreeVisualizer() {
        setTitle("Huffman Tree Visualizer");
        setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        loadButton = new JButton("Load from File");
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);

        // Panel for drawing
        drawingPanel = new DrawingPanel();

        // Output text area
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        // Status label
        statusLabel = new JLabel("Waiting for input...");

        add(buttonPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(outputScrollPane, BorderLayout.EAST);
        add(statusLabel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = JOptionPane.showInputDialog("Enter input text:");
                if (inputText != null && !inputText.isEmpty()) {
                    startAnimation(inputText);
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    try {
                        String inputText = readFile(filePath);
                        if (inputText != null && !inputText.isEmpty()) {
                            startAnimation(inputText);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage(),
                                "File Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        treeBuilder = new HuffmanTreeBuilder();
    }

    private void startAnimation(String inputText) {
        statusLabel.setText("Building Huffman tree...");
        HuffmanTree tree = treeBuilder.buildHuffmanTree(inputText);
        drawingPanel.setHuffmanTree(tree);
        drawingPanel.repaint();
        generateOutput(tree.getRoot());
        outputTextArea.setText("Input text: " + inputText + "\n\n" + outputTextArea.getText());
        statusLabel.setText("Huffman tree built!");
    }

    private void generateOutput(Node node) {
        StringBuilder output = new StringBuilder();
        generateOutput(node, "", output);
        outputTextArea.setText(output.toString());
    }

    private void generateOutput(Node node, String code, StringBuilder output) {
        if (node.isLeaf()) {
            output.append(node.getCharacter()).append(": ").append(code).append("\n");
        } else {
            generateOutput(node.getLeftChild(), code + "0", output);
            generateOutput(node.getRightChild(), code + "1", output);
        }
    }

    private String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] encodedBytes = Files.readAllBytes(path);
        return new String(encodedBytes);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HuffmanTreeVisualizer visualizer = new HuffmanTreeVisualizer();
                visualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                visualizer.setSize(1000, 600);
                visualizer.setVisible(true);
            }
        });
    }
}
