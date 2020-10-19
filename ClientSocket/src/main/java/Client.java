import java.io.*;
import java.net.*;

public class Client {
    int portaServer = 7280;
   Socket mioSocket;
   BufferedReader tastiera;
   String stringaUtente;
   String stringaServer;
   DataOutputStream outputServer; 
   BufferedReader inputServer;
   
   public Socket connetti() {
       System.out.println("2: Client in esecuzione...");
       try
       {
           tastiera = new BufferedReader(new InputStreamReader(System.in));
           mioSocket = new Socket(InetAddress.getLocalHost(), portaServer);
           outputServer = new DataOutputStream(mioSocket.getOutputStream());
           inputServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
       }
       catch(Exception ex) 
       {
           System.out.println(ex.getMessage());
           System.out.println("Errore durante la connessione");
           System.exit(1);
       }
       return mioSocket;
   }
   
   public void comunica() {
       try
       {
           System.out.println("4: Inserisci il primo valore da trasmettere al server");
           stringaUtente = tastiera.readLine();
           
           System.out.println("5: Invio il valore al server e attendo...");
           outputServer.writeBytes(stringaUtente + '\n');
           
           System.out.println("4.1: Inserisci il secondo valore da trasmettere al server");
           stringaUtente = tastiera.readLine();
           
           System.out.println("5.1: Invio il valore al server e attendo...");
           outputServer.writeBytes(stringaUtente + '\n');
           
           System.out.println("4.2: Inserisci l'operatore da trasmettere al server");
           stringaUtente = tastiera.readLine();
           
           System.out.println("5.2: Invio il valore al server e attendo...");
           outputServer.writeBytes(stringaUtente + '\n');
           
           stringaServer = inputServer.readLine();
           System.out.println("8: Risposta dal server" + '\n' + stringaServer);
           
           System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
           mioSocket.close();
       }
       catch(Exception ex) 
       {
           System.out.println(ex.getMessage());
           System.out.println("Errore durante la comunicazione col server!");
           System.exit(1);
       }
   }
   
   public static void main(String args[]) {
       Client cliente = new Client();
       cliente.connetti();
       cliente.comunica();
   }
}
