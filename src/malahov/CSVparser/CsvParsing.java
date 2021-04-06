package malahov.CSVparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CsvParsing {

    private static final String CSV_FILE = "assets/CSV.txt";

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter number of column:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String string = reader.readLine();
            CsvParsing object = new CsvParsing();
            System.out.println(object.extractColumn(CSV_FILE, Integer.parseInt(string)));
        }
    }

    /**
     * This method opens the file and finds the columns and returns an
     * ArrayList with the values of this column.
     *
     * @param filename    the path to the file.
     * @param columnIndex column number starting from 0.
     * @return ArrayList which included value of a given column.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String nextLine;
            nextLine = reader.readLine();
            while (nextLine != null) {
                result.add(fieldsIn(nextLine).get(columnIndex));
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method takes a line from the file
     * and returns an ArrayList with all the fields from the line.
     *
     * @param line the line read from the file.
     * @return ArrayList with the values of given string.
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder string = new StringBuilder();
        String[] lineElements = line.split("");
        int checker = 0;
        for (int i = 0; i < lineElements.length; i++) {
            if (lineElements[i].equals("\"")) {
                if (checker == 0) {
                    checker = 1;
                } else {
                    checker = 0;
                }
            } else if (checker == 0) {
                if (lineElements[i].equals(",")) {
                    arrayList.add(string.toString());
                    string = new StringBuilder();
                    continue;
                }
            }
            string.append(lineElements[i]);
            if (i == lineElements.length - 1) {
                arrayList.add(string.toString());
                string = new StringBuilder();
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (String s : arrayList) {
            if (s.startsWith("\"") && s.endsWith("\"")) {
                String s2 = s.replace("\"\"", "\"");
                result.add(s2.substring(1, s2.length() - 1));
            } else {
                result.add(s);
            }
        }
        return result;
    }
}
