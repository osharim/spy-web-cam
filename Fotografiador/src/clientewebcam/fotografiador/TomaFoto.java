package clientewebcam.fotografiador;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TomaFoto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_toma_foto);
	}

	public void capturar(View view) {
		ClienteWebcam cw = null;
		String host = ((EditText) findViewById(R.id.editText1)).getText()
				.toString();
		Bitmap imagen = null;
		try {
			cw = new ClienteWebcam(host, 2223);
		} catch (IOException e) {
			Toast.makeText(this, "No se pudo conectar al servidor",
					Toast.LENGTH_SHORT).show();
			return;
		}
		Toast.makeText(this, "Tomando fotografía\nDesde servidor",
				Toast.LENGTH_LONG).show();
		imagen = cw.tomarFoto();

		if (imagen == null) {
			return;
		}

		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setDrawingCacheEnabled(true);
		iv.setImageBitmap(imagen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_toma_foto, menu);
		return true;
	}

}
