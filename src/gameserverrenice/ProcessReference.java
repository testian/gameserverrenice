/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameserverrenice;
import java.io.IOException;
/**
 *
 * @author testi
 */
public class ProcessReference {
private String command;

    public ProcessReference(String command) {
        this.command = command;
    }
    public void setPriority(int priority) throws IOException {
    if (priority < -20) priority = -20;
    else if (priority > 19) priority = 19;
    System.out.println("Setting priority: " + command + " " + priority);
    Runtime.getRuntime().exec(new String[] {command, String.valueOf(priority)});
    }

}
