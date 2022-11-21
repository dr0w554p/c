import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) throws Exception {
        // To print the output into a file
        FileOutputStream file = new FileOutputStream("output.txt");
        System.setOut(new PrintStream(file));

        //To read the input file
        FileInputStream MNTtable = new FileInputStream("MNT.txt");
        Scanner MNTreader = new Scanner(MNTtable);

        FileInputStream MDTtable = new FileInputStream("MDT.txt");
        Scanner MDTreader = new Scanner(MDTtable);

        FileInputStream ALPtable = new FileInputStream("ALP.txt");
        Scanner ALPreader = new Scanner(ALPtable);

        List<String> MDT = new ArrayList<>();
        Map<String,Integer> MNT = new HashMap();
        List<String> result = new ArrayList<>();
        List<String> arguments = new ArrayList<>();

        //filling up the tables
        //MNT Table
        while(MNTreader.hasNext()){
            String tempLine = MNTreader.nextLine();
            String[] tempArr = tempLine.split(" ");
            MNT.put(tempArr[1],Integer.valueOf(tempArr[0]));
        }

        //filling up the MDT table
        while(MDTreader.hasNext()){
            MDT.add(MDTreader.nextLine().trim());
        }

        while(ALPreader.hasNext()){
            String line = ALPreader.nextLine().trim();
            String[] arr = line.split(" ");
            if(MNT.containsKey(arr[0])){
                //do something
                int argCounter = 1;
                int counter = MNT.get(arr[0]);
                while(!MDT.get(counter).equals("MEND")){
                    String MDTLine = MDT.get(counter);
                    String[] MDTarr = MDTLine.split(" ");
                    String MDTtemp = "+ ";

                    for(String str: MDTarr){
                        if(str.contains("#")){
                            //do something
                            MDTtemp += arr[argCounter++]+" ";
                        }else{
                            MDTtemp += str+" ";
                        }
                    }

                    System.out.println(MDTtemp);
                    result.add(MDTtemp);
                    counter++;
                }

                for(int i=1;i<arr.length;i++){
                    arguments.add(arr[i]);
                }
            }else{
                System.out.println(line);
                result.add(line);
            }

        }

    }
}