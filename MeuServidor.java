import java.io.*;
import java.net.*;

public class MeuServidor {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                    OutputStream out = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(out);

                    // Escreve o cabeçalho HTTP
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html");
                    writer.println("\r\n");

                    // Escreve o conteúdo HTML
                    writer.println("<html><body>");
                    writer.println("<h1>Minha primeira página de teste de site</h1>");
                    writer.println("<img src=\"https://www.artstation.com/artwork/wJYPAg\">");
                    writer.println("<img src=\"https://www.example.com/image.gif\">");
                    writer.println("</body></html>");

                    // Encerra a conexão
                    writer.flush();
                    out.close();
                    in.close();
                    clientSocket.close();
                }
            }
        }
    }
}

