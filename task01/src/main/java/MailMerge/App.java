package MailMerge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        // take in command line arguments
        String csvFile = args[0];
        String templateFile = args[1];

        try {
            // read csv file line by line
            BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));

            // take first line (column names) as variable names
            String header = csvReader.readLine();
            String[] headerVariables = header.split(",");
            Map<String, String> headerAndData = new HashMap<>();

            // take subsequent lines as data points one by one
            String data = "";
            while (true) {
                data = csvReader.readLine();
                if (data == null) {
                    break;
                }
                String[] datas = data.split(",");
                // create hashmap for each line in csv
                for (int i = 0; i < datas.length; i++) {
                    headerAndData.put(headerVariables[i], datas[i]);
                }

                // read template file
                BufferedReader templateReader = new BufferedReader(new FileReader(templateFile));

                // read template and replace variable names with data values
                String tempLine = "";
                while (true) {
                    tempLine = templateReader.readLine();
                    if (tempLine == null) {
                        break;
                    }
                    // check for variables
                    String[] tempLineSplit = tempLine.split("__");
                    if (tempLineSplit.length > 1) {
                        // have variables to be replaced (variables are always odd index)
                        for (int i = 1; i < tempLineSplit.length; i += 2) {
                            tempLineSplit[i] = headerAndData.get(tempLineSplit[i]);
                        }
                        // concatenate tempLineSplit back into a string
                        tempLine = "";
                        for (String string : tempLineSplit) {
                            tempLine += string;
                        }
                    }
                    // print out template line by line + line break
                    System.out.println(tempLine.replaceAll("\\\\n", "\n") + "\n");
                }
                templateReader.close();
            }
            // once no more csv lines, can close buffer readers
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
