package Interface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Client extends JFrame{
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JTextArea campoChat;
    private javax.swing.JTextField entradaEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static final long serialVersionUID = 1L;
    private Socket socket;
    private OutputStream outputStream;
    private Writer writer;
    private BufferedWriter bufferedWriter;
    private JTextField IP;
    private JTextField porta;
    private JTextField nomeUsuario;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        Client init = new Client();
        init.conectar();
        init.escutar();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoChat = new javax.swing.JTextArea();
        botaoSair = new javax.swing.JButton();
        entradaEnviar = new javax.swing.JTextField();
        botaoEnviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(600, 400));

        jPanel1.setBackground(new java.awt.Color(70, 70, 70));
        entradaEnviar.setBackground(new java.awt.Color(175, 175, 175));
        campoChat.setEditable(false);
        campoChat.setColumns(20);
        campoChat.setRows(5);
        campoChat.setBackground(new java.awt.Color(175, 175, 175));
        jScrollPane1.setViewportView(campoChat);

        botaoSair.setText("Sair");
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        botaoEnviar.setText("Enviar");
        botaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/Imagem/Logo.png")));
        jLabel2.setMaximumSize(new java.awt.Dimension(512, 512));
        jLabel2.setMinimumSize(new java.awt.Dimension(512, 512));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(entradaEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEnviar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botaoSair))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entradaEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEnviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoSair)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Usuario - " + nomeUsuario.getText());
    }

    private void botaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (evt.getActionCommand().equals(botaoEnviar.getActionCommand()))
                enviarMensagem(entradaEnviar.getText());
        } catch (IOException newEvent) {
            newEvent.printStackTrace();
        }
    }

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try{
            if (evt.getActionCommand().equals(botaoSair.getActionCommand()))
            sair();
        } catch (IOException newEvent) {
            newEvent.printStackTrace();
        }
    }
    
    public Client() {
        
        menuInicial();
        initComponents();
        setVisible(true);
    }

    public boolean menuInicial() {
        JLabel lblMessage = new JLabel("Verificar!");
        IP = new JTextField("127.0.0.1");
        porta = new JTextField("9000");
        nomeUsuario = new JTextField("Usuario");
        Object[] texts = {lblMessage, IP, porta, nomeUsuario};
        JOptionPane.showMessageDialog(null, texts);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        return true;
    }

    public void conectar() throws IOException {

        socket = new Socket(IP.getText(), Integer.parseInt(porta.getText()));
        outputStream = socket.getOutputStream();
        writer = new OutputStreamWriter(outputStream);
        bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(nomeUsuario.getText() + "\r\n");
        bufferedWriter.flush();
    }

    public void escutar() throws IOException {

        InputStream in = socket.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader bfr = new BufferedReader(inr);
        String msg = "";

        while (!"Sair".equalsIgnoreCase(msg)) {
            if (bfr.ready()) {
                msg = bfr.readLine();
                if (msg.equals("Sair")) {
                    campoChat.append(nomeUsuario.getText() + " Desconectado! \r\n");
                } else {
                    campoChat.append(msg + "\r\n");
                }
            }
        }
    }

    public void enviarMensagem(String msg) throws IOException {

        if (msg.equals("Sair")) {
            bufferedWriter.write("Desconectado \r\n");
            campoChat.append("Desconectado \r\n");
        } else {
            bufferedWriter.write(msg + "\r\n");
            campoChat.append(nomeUsuario.getText() + " diz: " + entradaEnviar.getText() + "\r\n");
        }
        bufferedWriter.flush();
        entradaEnviar.setText("");
    }

    public void sair() throws IOException {

        enviarMensagem("Sair");
        bufferedWriter.close();
        writer.close();
        outputStream.close();
        socket.close();
    }
}
