package mo.org.cpttm.cm386.imageroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap targetBitmap;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.butterfly);
        //targetBitmap = resizeBitmap(bitmap);
        targetBitmap = showBitmap(bitmap, 3);
        imageView.setImageBitmap(targetBitmap);

        Button clockwiseButton = (Button) findViewById(R.id.clockwiseButton);
        clockwiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doRotate(1, 90);
                targetBitmap = showBitmap(bitmap, 2);
                imageView.setImageBitmap(targetBitmap);
            }

        });
        Button antiClockwiseButton = (Button) findViewById(R.id.anticlockwiseButton);
        antiClockwiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doRotate(-1, 90);

                targetBitmap = showBitmap(bitmap, 4);
                imageView.setImageBitmap(targetBitmap);
            }
        });


    }

    private void doRotate(int direction, int degree) {
        Matrix m = new Matrix();
        m.postRotate( direction * degree );
        targetBitmap = Bitmap.createBitmap(targetBitmap, 0, 0, targetBitmap.getWidth(), targetBitmap.getHeight(), m, true);
        imageView.setImageBitmap(targetBitmap);
    }


    private Bitmap resizeBitmap(Bitmap bitmap) {
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);

        int screenWidth = screenSize.x;
        int screenHeight = screenSize.y;

        if (imageWidth > screenWidth || imageHeight > screenHeight) {

            float ratioWidth = imageWidth / screenWidth;
            float ratioHeight = imageHeight / screenHeight;

            float usedRatio = (ratioWidth > ratioHeight) ? ratioWidth : ratioHeight;

            int newImageWidth = (int) (imageWidth / usedRatio / 2) ;
            int newImageHeight = (int) (imageHeight / usedRatio / 2);

            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newImageWidth, newImageHeight, true);

            return newBitmap;

        }else{
            return bitmap;
        }
    }

    private Bitmap showBitmap(Bitmap bitmap, int scale) {
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        //Toast.makeText(this, "imageWidth="+imageWidth, Toast.LENGTH_LONG).show();
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);

        int screenWidth = screenSize.x;
        int screenHeight = screenSize.y;
        //Toast.makeText(this, "screenWidth="+screenWidth, Toast.LENGTH_LONG).show();

        int newImageWidth =  screenSize.x / scale;

        float usedRatio = (float) imageWidth / newImageWidth;
        //Toast.makeText(this, "usedRatio="+usedRatio, Toast.LENGTH_LONG).show();

        int newImageHeight = (int) (imageHeight / usedRatio );

        //Toast.makeText(this, "newImageWidth="+newImageWidth, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "newImageHeight="+newImageHeight, Toast.LENGTH_LONG).show();
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newImageWidth, newImageHeight, true);

        return newBitmap;
    }
}
