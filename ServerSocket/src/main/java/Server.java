import java.io.*;
import java.net.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inputClient;
    DataOutputStream outputClient;
    int tot;
    
     public Socket attendi() {
        try
        {
            System.out.println("1: Server in esecuzione...");
            server = new ServerSocket(7280);
            client = server.accept();
            server.close();
            inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connesione!");
            System.exit(1);
        }
        return client;
    }
     
    public void comunica() {
        try
        {
            while(true) {
                System.out.println("3: Benvenuto client, inserisci il primo valore. Attendo...");
                stringaRicevuta = inputClient.readLine();
                int operando1 = Integer.decode(stringaRicevuta);
                System.out.println("3: Inserisci il secondo valore. Attendo...");
                stringaRicevuta = inputClient.readLine();
                int operando2 = Integer.decode(stringaRicevuta);
                System.out.println("3: Scrivi S(per somma), D(per divisione), M(per sottrazione), X(per moltiplicazione). Attendo...");
                stringaRicevuta = inputClient.readLine();
                switch(stringaRicevuta) {
                    case "S": 
                        tot = operando1 + operando2;
                        System.out.println("Il risultato della somma tra " + operando1 + " e " + operando2 + " è: " + (operando1+operando2));
                        break;
                    case "D": 
                        tot = operando1 / operando2;
                        System.out.println("Il risultato della divisione tra " + operando1 + " e " + operando2 + " è: " + (operando1/operando2));
                        break;
                    case "M": 
                        tot = operando1 - operando2;
                        System.out.println("Il risultato della sottrazione tra " + operando1 + " e " + operando2 + " è: " + (operando1-operando2));
                        break;
                    case "X": 
                        tot = operando1 * operando2;
                        System.out.println("Il risultato della Moltiplicazione tra " + operando1 + " e " + operando2 + " è: " + (operando1*operando2));
                        break;
                    default: 
                        break;
                }
                break;
            }
            
            outputClient.writeBytes(String.valueOf(tot) + '\n');
            System.out.println("9, SERVER: fine elaborazione... arrivederci");
            client.close();
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String args[]) { 
        Server server = new Server();
        server.attendi();
        server.comunica();
    }
}
