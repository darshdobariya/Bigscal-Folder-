package com.example.gfgbasics.database.storage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gfgbasics.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageActivity extends AppCompatActivity {

    private final int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    Button btnPublic1, btnPublic2, btnPrivate1, btnPrivate2, btnView1, btnView2;
    EditText edtOne, edtTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        btnPublic1 = findViewById(R.id.btnPublic);
        btnPublic2 = findViewById(R.id.btnPublicTwo);
        btnPrivate1 = findViewById(R.id.btnPrivate);
        btnPrivate2 = findViewById(R.id.btnPrivateTwo);
        btnView1 = findViewById(R.id.btnViewOne);
        btnView2 = findViewById(R.id.btnViewTwo);

        edtOne = findViewById(R.id.edtOne);
        edtTwo = findViewById(R.id.edtTwo);

        btnPublic1.setOnClickListener(v->{ btnPublicOne();});
        btnPublic2.setOnClickListener(v->{ btnPublicTwo();});
        btnPrivate1.setOnClickListener(v->{ btnPrivateOne();});
        btnPrivate2.setOnClickListener(v->{ btnPrivateTwo();});
        btnView1.setOnClickListener(v->{ btnViewOne();});
        btnView2.setOnClickListener(v->{ btnViewTwo();});

    }

    private void btnViewTwo() {

    }

    private void btnViewOne() {

    }

    private void btnPrivateTwo() {
        File folder = getExternalFilesDir("GFGBAsics/firstProgramme");

        // gft.txt is the file that is saved privately
        File file = new File(folder, "gfg.txt");
        String data = getdata(file);
        if (data != null) {
            edtTwo.setText(data);
        } else {
            edtTwo.setText("No Data Found");
        }
    }

    private void btnPrivateOne() {
        String editTextData = edtOne.getText().toString();

        // Creating folder with name GeeksForGeeks
        File folder = getExternalFilesDir("GFGBAsics/firstProgramme");

        // Creating file with name gfg.txt
        File file = new File(folder, "gfg.txt");
        writeTextData(file, editTextData);
        edtOne.setText("");
    }

    private void btnPublicTwo() {
        // Accessing the saved data from the downloads folder
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        // geeksData represent the file data that is saved publicly
        File file = new File(folder, "geeksData.txt");
        String data = getdata(file);
        if (data != null) {
            edtTwo.setText(data);
        } else {
            edtTwo.setText("No Data Found");
        }
    }

    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void btnPublicOne() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
        String editTextData = edtOne.getText().toString();

        // getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
        // We can use following directories: MUSIC, PODCASTS, ALARMS, RINGTONES, NOTIFICATIONS, PICTURES, MOVIES
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        // Storing the data in file with name as geeksData.txt
        File file = new File(folder, "geeksData.txt");
        writeTextData(file, editTextData);
        edtOne.setText("");
    }

    private void writeTextData(File file, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}