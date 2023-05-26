package Compression;
import java.io.*;

public class App {
    public static void main(String[] args) {
        String filePath = "D://yeu lai tu dau//java-2022-09//Huff//src//Compression//test.txt";

        String str = readFromFile(filePath);
        //System.out.println("Source: " + str);
        HuffmanCoding huffmanCoding = new HuffmanCoding().process(str);
        String encoded = huffmanCoding.encode();
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + huffmanCoding.decode(encoded));

        writeToFile(encoded, "output.txt");
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
