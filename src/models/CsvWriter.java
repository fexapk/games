package src.models;

public interface CsvWriter {
    /**
     * Obj builds a line where it's attributes are represented in comma separated values
     * @param separator (char)
     * @return (String)
     */
    String toCsv(char separator);
}
