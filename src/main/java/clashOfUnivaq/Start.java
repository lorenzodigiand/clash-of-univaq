package clashOfUnivaq;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.output.TeeOutputStream;

import clashOfUnivaq.datamodel.match.Menu;

public class Start {

    public static void main(String[] args){
        try {
            FileOutputStream fos = new FileOutputStream("log.txt");
            TeeOutputStream tos = new TeeOutputStream(System.out, fos);
            PrintStream ps = new PrintStream(tos);
            // TeeOutputStream diverts System.out to log.txt file, so that 
            // everything printed on the terminal is also written onto the file
            System.setOut(ps);

            Menu.start();

            ps.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}