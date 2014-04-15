package com.homelysoft.thenewboston;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Photo extends Activity implements View.OnClickListener {
	private static final int CAMERA_DATA = 0;
	ImageButton ib;
	Button b;
	ImageView iv;
	Intent i;
	Bitmap bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		bridgeXML();
		setOnClickListeners();
		InputStream inputStream=getResources().openRawResource(R.drawable.splash);
		bitmap=BitmapFactory.decodeStream(inputStream);
	}

	private void bridgeXML() {
		ib = (ImageButton) findViewById(R.id.ib_camera);
		iv = (ImageView) findViewById(R.id.iv_returned_picked);
		b = (Button) findViewById(R.id.b_select_wallpaper);
	}

	private void setOnClickListeners() {
		ib.setOnClickListener(this);
		b.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.b_select_wallpaper:
			try {
				WallpaperManager wallpapermanager = WallpaperManager
						.getInstance(this);
				wallpapermanager.setBitmap(bitmap);
			} catch (IOException e) {
				Toast.makeText(Photo.this, "Ooops, couldn't set the wallpaper",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			break;
		case R.id.ib_camera:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, CAMERA_DATA);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == CAMERA_DATA) {
				Bundle extras = data.getExtras();
				bitmap=(Bitmap) extras.get("data");
				iv.setImageBitmap(bitmap);
			}
		}
	}

}
