import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Server extends Thread {
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket Socket;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public static void main(String []args) {

        try{
          
          JLabel lblMessage = new JLabel("Porta do Servidor:");
          JTextField txtPorta = new JTextField("9000");
          Object[] texts = {lblMessage, txtPorta };
          JOptionPane.showMessageDialog(null, texts);
          server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
          clientes = new ArrayList<BufferedWriter>();
          JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+
          txtPorta.getText());
      
           while(true){
             System.out.println("Aguardando conexão...");
             Socket conexao = server.accept();
             System.out.println("Cliente conectado...");
             Thread thread = new Server(conexao);
              thread.start();
          }
      
        }catch (Exception exception) {
      
          exception.printStackTrace();
        }
    }
      

    public Server(Socket socket) {
        this.Socket = socket;
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void run() {

        try {
            String mensagem;
            OutputStream outputStream = this.Socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            clientes.add(bufferedWriter);
            nome = mensagem = bufferedReader.readLine();

            while (!"Sair".equalsIgnoreCase(mensagem) && mensagem != null) {
                mensagem = bufferedReader.readLine();
                sendToAll(bufferedWriter, mensagem);
                System.out.println(mensagem);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void sendToAll(BufferedWriter bufferedWriterSaida, String mensagem) throws IOException {
        BufferedWriter bufferedWriterS;

        for (BufferedWriter bufferedWriter : clientes) {
            bufferedWriterS = (BufferedWriter) bufferedWriter;
            if (!(bufferedWriterSaida == bufferedWriterS)) {
                bufferedWriter.write(nome + " -> " + mensagem + "\r\n");
                bufferedWriter.flush();
            }
        }
    }
}
