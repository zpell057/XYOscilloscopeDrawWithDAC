import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
    /**
     * Writing String to file
     * @param fileName filename to be written
     * @param content String written into the file
     */
    public static void writeToFile(String fileName, String content) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
