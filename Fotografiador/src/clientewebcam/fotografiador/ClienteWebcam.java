package clientewebcam.fotografiador;

import java.io.IOException;
import java.net.Socket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 
 * @author angelverde
 */
public class ClienteWebcam {

	private Socket conexion;

	public ClienteWebcam(String host, int puerto) throws IOException {
		conexion = new Socket(host, puerto);
	}

	public Bitmap tomarFoto() {
		try {
			return BitmapFactory.decodeStream(conexion.getInputStream());			
		} catch (IOException ioe) {
			return null;
		}
	}

}
