package read.write.in.custom.file.format;

import java.io.*;

public class ReadfromFile {
    public static void main(String[] args) {

        String path = "/Users/rishika/Documents/Projects/5408/CustomFile/MyFormat.txt";
        String str = " ";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((str = br.readLine()) != null){
                String[] values = str.split(":");
                System.out.println(values[2]);
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e1){
            e1.printStackTrace();
        }
    }
}