/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebcam;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author angelverde
 */
public class ServidorWebcam extends Thread {

    private ServerSocket s;
    private Webcam camara;
    private boolean ejecutando;

    public ServidorWebcam(int puerto) {
        try {
            s = new ServerSocket(puerto);
        } catch (IOException e) {
            System.out.println("No se pudo crear el servidor");
        }
        ejecutando = false;
        camara = Webcam.getDefault();
        camara.setViewSize(new Dimension(640, 480));
    }

    @Override
    public void run() {
        ejecutando = true;

        while (ejecutando) {
            try {
                Socket sc = s.accept();
                System.out.println("Cliente conectado");
                camara.open();
                BufferedImage foto = null;
                try {
                    System.out.println("Tomando foto");
                    foto = camara.getImage();
                } catch (Exception e) {
                    System.out.println("No se pudo capturar la imagen de la webcam :(");
                }
                System.out.println("Enviando foto");
                ImageIO.write(foto, "JPG", sc.getOutputStream());
                camara.close();
                sc.close();
                System.out.println("Cliente desconectado");
            } catch (IOException e) {
                System.out.println("Error al interactuar con el cliente");
            }

        }
    }

    public void detener() {
        ejecutando = false;
        try {
            s.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar conexiones");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        final int PUERTO = 2223;
        ServidorWebcam sw = new ServidorWebcam(PUERTO);
        System.out.println("Servidor iniciado\n");
        sw.start();
        System.out.println("Presione una tecla para detener el servidor");
        System.in.read();
        sw.detener();

    }
}
