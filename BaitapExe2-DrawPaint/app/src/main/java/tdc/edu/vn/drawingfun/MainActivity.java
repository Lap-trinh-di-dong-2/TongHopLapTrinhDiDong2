package tdc.edu.vn.drawingfun;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    long tgTruoc;
    int i = 1;

    DrawView drawingView1;
    ImageButton btnPen, btnErase, btnSave;
    ImageView ivHinh,ivHinh1,ivHinh2,ivHinh3,ivHinh4,ivHinh5;
    Button btnRed, btnGreen, btnBlue, btnYellow, btnRadian, btnBlack, btnPurple, btnOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {

        ivHinh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView1.setBackgroundResource(R.drawable.xe1);
            }
        });
        ivHinh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView1.setBackgroundResource(R.drawable.xe2);
            }
        });
        ivHinh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView1.setBackgroundResource(R.drawable.xe3);
            }
        });
        ivHinh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView1.setBackgroundResource(R.drawable.xe6);
            }
        });
        ivHinh5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView1.setBackgroundResource(R.drawable.xe7);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bạn muốn lưu hình ảnh?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        drawingView1.setDrawingCacheEnabled(true);
                        String imageSaved = MediaStore.Images.Media.insertImage(getContentResolver(), drawingView1.getDrawingCache(), UUID.randomUUID().toString() + ".png", "drawing");
                        if (imageSaved != null) {
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Opp, Image not saved", Toast.LENGTH_LONG).show();
                        }
                        drawingView1.destroyDrawingCache();
//                        finish();
//                        startActivity(getIntent());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DrawView drawView = new DrawView();
//                changeColor()
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Green", Toast.LENGTH_SHORT).show();
            }
        });
        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Purple", Toast.LENGTH_SHORT).show();
            }
        });
        btnRadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Radian", Toast.LENGTH_SHORT).show();
            }
        });
        btnOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Orange", Toast.LENGTH_SHORT).show();
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Blue", Toast.LENGTH_SHORT).show();
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Red", Toast.LENGTH_SHORT).show();
            }
        });
        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Black", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setControl() {

        drawingView1 = findViewById(R.id.drawing1);
        btnPen = findViewById(R.id.btnPen);
        btnErase = findViewById(R.id.btnErase);
        btnSave = findViewById(R.id.btnSave);
        btnBlack = findViewById(R.id.btnBlack);
        btnRed = findViewById(R.id.btnred);
        btnBlue = findViewById(R.id.btnblue);
        btnOrange = findViewById(R.id.btnOrange);
        btnRadian = findViewById(R.id.btnRadian);
        btnPurple = findViewById(R.id.btnPurple);
        btnGreen = findViewById(R.id.btngreen);
        btnYellow = findViewById(R.id.btnyellow);


        ivHinh1 = findViewById(R.id.tvHinh1);
        ivHinh2 = findViewById(R.id.tvHinh2);
        ivHinh3 = findViewById(R.id.tvHinh3);
        ivHinh4 = findViewById(R.id.tvHinh4);
        ivHinh5 = findViewById(R.id.tvHinh5);
    }

}