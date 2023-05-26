package Compression;
import java.io.*;

public class App {
    public static void main(String[] args) {
        String filePath = "D://yeu lai tu dau//java-2022-09//Huffman//Huff//src//Compression//test.txt";

        String str = readFromFile(filePath);
        //System.out.println("Source: " + str);
        HuffmanCoding huffmanCoding = new HuffmanCoding().process(str);
        String encoded = huffmanCoding.encode();
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + huffmanCoding.decode(encoded));

        writeToFile(encoded, "output.txt");

        // Giải mã từ file "output.txt"
        String encodedData = readFromFile("output.txt");
        String decodedData = huffmanCoding.decode(encodedData);

        // Ghi dữ liệu giải mã vào file "initial.txt"
        writeToFile(decodedData, "initial.txt");
        System.out.println("Decoded data written to file: initial.txt");
    }

    private static String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static void writeToFile(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("Data written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
