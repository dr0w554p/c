import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class lab1 {
    public static void main(String[] args) throws Exception {
        int LC = 0, SC = 0;
        HashMap<String, String> map_is = new HashMap<>();
        HashMap<String, String> map_dl = new HashMap<>();
        HashMap<String, String> map_ad = new HashMap<>();
        HashMap<String, String> map_reg = new HashMap<>();
        LinkedHashMap<String, String> map_symbol = new LinkedHashMap<String, String>();
        map_is.put("STOP", "00");
        map_is.put("ADD", "01");
        map_is.put("SUB", "02");
        map_is.put("MULT", "03");
        map_is.put("MOVER", "04");
        map_is.put("MOVEM", "05");
        map_is.put("COMP", "06");
        map_is.put("BC", "07");
        map_is.put("DIV", "08");
        map_is.put("READ", "09");
        map_is.put("PRINT", "10");
        map_dl.put("DC", "01");
        map_dl.put("DS", "02");
        map_ad.put("START", "01");
        map_ad.put("END", "02");
        map_ad.put("ORIGIN", "03");
        map_ad.put("EQU", "04");
        map_ad.put("LTORG", "05");
        map_reg.put("AREG", "1");
        map_reg.put("BREG", "2");
        map_reg.put("CREG", "3");
        map_reg.put("DREG", "4");
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter writer = new FileWriter("output.txt", true);
        boolean st_found = false;
        boolean ds_found = false;
        boolean dc_found = false;
        boolean noi = true;
        String st, start = "START", ds = "DS", dc = "DC";
        while ((st = br.readLine()) != null) {
            // Tokenising 
            String[] arrOfStr = st.split("[ ]");
            // Iterating Over Tokens 
            if (noi) {
                writer.write(" ");
                noi = false;
            } else {
                writer.write(" " + LC + " ");
            }
            for (int j = 0; j < arrOfStr.length; j++) {
                boolean flag = false;
                if (st_found) {
                    LC = Integer.parseInt(arrOfStr[j]);
                    writer.write("( C ," + LC + " )");
                    st_found = false;
                    LC--;
                    continue;
                }
                if (ds_found) {
                    int curr = Integer.parseInt(arrOfStr[j]);
                    writer.write("( C ," + curr + " )");
                    ds_found = false;
                    LC += curr;
                    LC--;
                    continue;
                }
                if (dc_found) {
                    int curr = Integer.parseInt(arrOfStr[j]);
                    writer.write("( C ," + curr + " )");
                    dc_found = false;
                    continue;
                }
                // Checking the Token inside each map 
                for (Entry<String, String> entry :
                        map_symbol.entrySet()) {
                    if (entry.getKey().equals(arrOfStr[j])) {
                        String s = String.valueOf(LC);
                        if (j == 0) {
                            // do nothing 
                        } else {
                            writer.write("( S ," + s + " )");
                        }
                        map_symbol.put(arrOfStr[j], s);
                        flag = true;
                        writer.write(" ");
                        break;
                    }
                }
                for (Entry<String, String> entry :
                        map_is.entrySet()) {
                    if (entry.getKey().equals(arrOfStr[j])) {
                        writer.write("( IS ," + entry.getValue() +
                                " )");
                        flag = true;
                        writer.write(" ");
                        break;
                    }
                }
                for (Entry<String, String> entry :
                        map_dl.entrySet()) {
                    if (entry.getKey().equals(arrOfStr[j])) {
                        writer.write("( DL ," + entry.getValue() +
                                " )");
                        flag = true;
                        writer.write(" ");
                        if (arrOfStr[j].equals(ds)) {
                            ds_found = true;
                            continue;
                        }
                        if (arrOfStr[j].equals(dc)) {
                            dc_found = true;
                            continue;
                        }
                        break;
                    }
                }
                for (Entry<String, String> entry :
                        map_ad.entrySet()) {
                    if (entry.getKey().equals(arrOfStr[j])) {
                        writer.write("( AD ," + entry.getValue() +
                                " )");
                        flag = true;
                        writer.write(" ");
                        if (arrOfStr[j].equals(start)) {
                            st_found = true;
                            continue;
                        }
                        break;
                    }
                }
                for (Entry<String, String> entry :
                        map_reg.entrySet()) {
                    if (entry.getKey().equals(arrOfStr[j])) {
                        writer.write("( RG ," + entry.getValue() +
                                " )");
                        flag = true;
                        writer.write(" ");
                        break;
                    }
                }
                // This means did not found in any 
                // map...then...add in the symbol table
                if (!flag) {
                    String s = String.valueOf(SC++);
                    map_symbol.put(arrOfStr[j], s);
                    if (j != 0) {
                        writer.write("( S ," + SC + " )");
                        writer.write(" ");
                    }
                }
            }
            writer.write("\n");
            LC++;
        }
        writer.close();
        map_symbol.entrySet().forEach(entry -> {
                System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
