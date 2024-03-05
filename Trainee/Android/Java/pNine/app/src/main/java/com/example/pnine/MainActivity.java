package com.example.pnine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    /*What we do?
    -- Single Activity app with two fragments - Home & browser fragment
    -- On the Home fragment add an input field where the user can enter a URL and a button to open URL inside the app.
    -- Once the user enters a valid URL and clicks the button, slide up the Browser fragment from the bottom of the screen
    -- This fragment will show the content of the URL within a web view.
    -- The fragment will also have a button to close it.
    -- Add a menu in the action bar, which will have two options- copy and share the link
    -- Copy option should copy the link to the clipboard
    -- The share option should allow users to share links in other applications
    -- The state of the fragment should be preserved on configuration changes such as screen rotation.
    Intent*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.frmLayout, fragment).addToBackStack(null).commit();

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url", "");
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String name = sharedPreferences.getString("url", "");

        if (name.trim().equals("")){
            Toast.makeText(this, "Empty URL", Toast.LENGTH_SHORT).show();
        }else{
            if (id == R.id.copy) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("url", name);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();

                return true;
            }if (id == R.id.paste){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, name);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}