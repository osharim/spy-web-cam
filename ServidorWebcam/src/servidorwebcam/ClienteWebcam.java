/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebcam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author angelverde
 */
public class ClienteWebcam {

    private Socket conexion;

    public ClienteWebcam(String host, int puerto) throws IOException {
        conexion = new Socket(host, puerto);
    }

    public BufferedImage tomarFoto(){
        try {
            return ImageIO.read(conexion.getInputStream());
        } catch (IOException ioe) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        ImageIO.write(new ClienteWebcam("localhost", 2223).tomarFoto(), "PNG", new File("cliente_"+(Math.round(Math.random()*100))+".png"));
    }
}
