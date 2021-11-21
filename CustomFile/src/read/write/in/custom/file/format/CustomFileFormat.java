package read.write.in.custom.file.format;

import java.io.File;
import java.io.PrintWriter;

public class CustomFileFormat {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter(new File("/Users/rishika/Documents/Projects/5408/CustomFile/MyFormat.txt"));
            StringBuilder sb = new StringBuilder();

            sb.append("ID");
            sb.append(" : ");
            sb.append("Name");
            sb.append(" : ");
            sb.append("Age");
            sb.append(" : ");
            sb.append("Gender");
            sb.append(" : ");
            sb.append("Class");
            sb.append(" : ");
            sb.append("School");
            sb.append("\r\n");

            sb.append("123");
            sb.append(" : ");
            sb.append("Rishika");
            sb.append(" : ");
            sb.append("14");
            sb.append(" : ");
            sb.append("F");
            sb.append(" : ");
            sb.append("Second");
            sb.append(" : ");
            sb.append("Convent");
            sb.append("\r\n");


            sb.append("234");
            sb.append(" : ");
            sb.append("Pallavi");
            sb.append(" : ");
            sb.append("15");
            sb.append(" : ");
            sb.append("F");
            sb.append(" : ");
            sb.append("Fourth");
            sb.append(" : ");
            sb.append("Convent");
            sb.append("\r\n");

            sb.append("345");
            sb.append(" : ");
            sb.append("Jai");
            sb.append(" : ");
            sb.append("13");
            sb.append(" : ");
            sb.append("F");
            sb.append(" : ");
            sb.append("Sixth");
            sb.append(" : ");
            sb.append("DPS");
            sb.append("\r\n");

            sb.append("456");
            sb.append(" : ");
            sb.append("Monica");
            sb.append(" : ");
            sb.append("16");
            sb.append(" : ");
            sb.append("F");
            sb.append(" : ");
            sb.append("Fifth");
            sb.append(" : ");
            sb.append("DPS");
            sb.append("\r\n");

            pw.write(sb.toString());
            pw.close();
        } catch (Exception e) {

        }

    }


}
