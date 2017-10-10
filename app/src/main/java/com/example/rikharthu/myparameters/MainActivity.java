package com.example.rikharthu.myparameters;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends WearableActivity {

    private static int WIDTH = 200;
    private static int HEIGHT = 200;
    private ImageView mQrImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQrImageView = findViewById(R.id.qr_code_image);

        // Enables Always-on
        setAmbientEnabled();
        PreferenceUtils.get(this).setBodySize(Sizes.L);
        PreferenceUtils.get(this).setFootSize(45.5f);
        PreferenceUtils.get(this).setGender(Sizes.MALE);

        new Handler().postDelayed(() -> {
            ((Animatable) mQrImageView.getDrawable()).start();
        }, 3000);

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WIDTH = mQrImageView.getWidth();
                HEIGHT = mQrImageView.getHeight();
                final BodyParams params = PreferenceUtils.get(MainActivity.this).getBodyParams();
                mQrImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        mQrImageView.setImageBitmap(encodeQrAsBitmap(new Gson().toJson(params)));
                    }
                });
            }
        }).start();
        */
    }

    Bitmap encodeQrAsBitmap(String data) {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(data,
                    BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        int width = 4;

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }
}
