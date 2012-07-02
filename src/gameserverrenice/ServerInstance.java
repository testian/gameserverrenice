/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameserverrenice;
import quakestat.ServerFetcherFactory;
import quakestat.ServerFetcher;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import quakestat.GameServer;
import java.io.IOException;
/**
 *
 * @author testi
 */
public class ServerInstance {
private String qstatName;
private int port;
private ProcessReference pr;

    public ServerInstance(String qstatName, int port, ProcessReference pr) {
        this.qstatName = qstatName;
        this.port = port;
        this.pr = pr;
    }
    public ServerInstance(String serverString) {
    String[] split = serverString.split(":");
    if (split.length != 3) {throw new IllegalArgumentException("serverString must contain 3 arguments separated by :");}
    qstatName = split[0];
    try {
    port = Integer.parseInt(split[1]); }
    catch (NumberFormatException ex) {throw new IllegalArgumentException(ex);}
    pr = new ProcessReference(split[2]);
    }

    public void renice() {
        try {
        ServerFetcher sf = new ServerFetcherFactory().createServerFetcher(qstatName, InetAddress.getLocalHost(), port);
        sf.setIncludePlayers(true);
        List<GameServer> sl = sf.getServers();
        int playerCount;

        if (sl.isEmpty()) {
            System.out.println("No server found");
            playerCount = 0;}
        else {
            GameServer server = sl.get(0);
            System.out.println("Found server: " + server.serverDescription());
            playerCount = server.getPlayerCount();
        }

        if (playerCount > 0) {
        if (playerCount > 11) playerCount = 11;
        pr.setPriority(-playerCount-9);
        }
        else {
        pr.setPriority(10);
        }
        } catch (UnknownHostException ex) {System.err.print(ex);}
        catch (IOException ex) {System.err.println(ex);}
    }

}
