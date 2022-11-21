import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MacroPass1 {
    public static void main(String[] args) throws Exception {

        // To print the output into a file
        FileOutputStream file = new FileOutputStream("output.txt");
        System.setOut(new PrintStream(file));

        // To read the input file
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner scanner = new Scanner(fis);

        List<String> ALP = new ArrayList<>();
        List<String> MDT = new ArrayList<>();
        List<List<String>> MNT = new ArrayList<>();
        List<String> ALAList = new ArrayList<>();
        Map<String, Integer> ALA = new HashMap<>();

        // System.out.println("hello");
        int alaCounter = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // System.out.println(line);
            List<String> currLine = new ArrayList<>();

            if (line.contains("MACRO")) {
                // macro = true;
                boolean macroName = false;
                while (scanner.hasNextLine()) {
                    String temp = scanner.nextLine().trim();
                    String[] arr = temp.split(" ");
                    if (macroName == false) {
                        // System.out.println(temp + arr.length);
                        MNT.add(Arrays.asList(Integer.toString(MDT.size() + 1), arr[0]));
                        for (String str : arr) {
                            if (str.contains("&")) {
                                ALA.put(str, alaCounter++);
                                ALAList.add(str);

                            }
                        }
                    }
                    String mdtline = "";
                    if (macroName == true) {
                        for (String str : arr) {
                            if (str.contains("&")) {
                                mdtline += " #" + ALA.get(str);
                            } else {
                                mdtline += " " + str;
                            }
                        }
                        MDT.add(mdtline);
                    } else {
                        MDT.add(temp);
                    }
                    macroName = true;
                    if (temp.contains("MEND"))
                        break;
                }
            } else {
                ALP.add(line);
            }
        }

        System.out.println("=======MNT Table=====");
        for (List<String> str : MNT) {
            System.out.println(str);
        }

        System.out.println("=======MDT Table=====");
        for (String str : MDT) {
            System.out.println(str);
        }

        System.out.println("=======ALP Table=====");
        for (String str : ALP) {
            System.out.println(str);
        }

        System.out.println("=======ALA Table=====");
        // for(String str : ALAList){
        // System.out.println(str);
        // }

        for (int i = 0; i < ALAList.size(); i++) {
            System.out.println(i + 1 + " " + ALAList.get(i));
        }
    }
}