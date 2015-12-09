import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by cpg on 12/6/15.
 *
 * Writes to a log file.
 */
public class Log {
    private BufferedWriter writer;
    private String filename;

    public Log(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename));
        this.filename = filename;
    }

    public void delete() {
        File file = new File(this.filename);
        file.delete();
    }

    /**
     * Displays a string ending with a newline.
     * @param line
     * @throws IOException
     */
    public void displayThisLine(String line) throws IOException {
        this.writer.write(String.format("%s\r\n", line));
    }

    /**
     * Displays an empty line.
     * @throws IOException
     */
    public void displayThisLine() throws IOException {
        this.displayThisLine("");
    }

    /**
     * Displays a string with no newline.
     * @param string
     * @throws IOException
     */
    public void displayThis(String string) throws IOException {
        this.writer.write(string);
    }

    /**
     * Displays a decorative divider for readability.
     * @throws IOException
     */
    public void displayDivider() throws IOException {
        this.displayThisLine("#   #   #   #   #   #   #   #   #   #   #   #");
    }

    public void finishUp() throws IOException {
        writer.close();
    }
}
