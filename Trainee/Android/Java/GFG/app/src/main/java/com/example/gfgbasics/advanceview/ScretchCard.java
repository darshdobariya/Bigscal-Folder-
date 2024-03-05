package com.example.gfgbasics.advanceview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfgbasics.R;

public class ScretchCard extends AppCompatActivity {

    private ImageView imageView;
    private float floatX, floatY;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint = new Paint();

    private Integer intCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scretch_card);

        imageView = findViewById(R.id.scratch_img);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        floatX = event.getX();
        floatY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            clearScratchCard();
        }
        return super.onTouchEvent(event);
    }

    private void clearScratchCard(){
        if (bitmap == null){
            bitmap = Bitmap.createBitmap(imageView.getWidth(),imageView.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            canvas.drawColor(0xFFAAAAAA);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        intCount++;
        canvas.drawCircle(floatX, floatY-250, 60, paint);
        if (intCount > 500){
            imageView.setImageBitmap(null);
        }else {
            imageView.setImageBitmap(bitmap);
        }
    }
}