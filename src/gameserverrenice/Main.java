/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameserverrenice;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import quakestat.QuakeStat;
/**
 *
 * @author testi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuakeStat.SetQuakeStatCommand("./quakestat");
        try {
        BufferedReader reader = new BufferedReader(new FileReader("renice.list"));

        String line;
        while ((line = reader.readLine()) != null) {
        try {
            ServerInstance si = new ServerInstance(line);
            si.renice();
            }
        catch (IllegalArgumentException ex){System.err.println(ex.getMessage());}
        }

        } catch (IOException ex){System.err.println(ex);}
    }

}
