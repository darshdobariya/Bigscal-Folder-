package com.example.pfour;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

// start activity depe
// mi issue

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_TAKE_GALLERY_VIDEO = 3;
    private Button btnPlay;
    private boolean isPlaying = false;
    private PlayerView playerView;
    private SimpleExoPlayer exoPlayer;
    private ActivityResultLauncher<Intent> galleryLauncher;

     /*What we do?
    -- Implement Video player
    -- This will be single Activity app
    -- App should have a view to show Video
    -- One button to play and pause video
    -- The player should follow the activity lifecycle, pause the player when the activity goes to the background and resume it when the activity returns to the foreground.
    -- The player should also release resources when the app is no longer alive.
    -- An application can play a video from local resource.
    -- Use Exoplayer or MediaPlayer to play media*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        Button btnSelect = findViewById(R.id.btnSelect);
        playerView = findViewById(R.id.player_view);

        btnSelect.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            galleryLauncher.launch(Intent.createChooser(intent, "Select Video"));
        });

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri selectedVideoUri = data.getData();
                            if (selectedVideoUri != null) {
                                initializePlayer(selectedVideoUri);
                                btnPlay.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

        btnPlay.setOnClickListener(v -> togglePlayer());
    }

    private void initializePlayer(Uri mediaUri) {
        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(mediaUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
    }

    private void togglePlayer() {
        btnPlay.setVisibility(View.VISIBLE);
        if (exoPlayer != null) {
            if (isPlaying) {
                exoPlayer.pause();
                btnPlay.setText(R.string.play);
            } else {
                exoPlayer.play();
                btnPlay.setText(R.string.pause);
            }
            isPlaying = !isPlaying;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        try (Cursor cursor = getContentResolver().query(uri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                return cursor.getString(columnIndex);
            }
        }
        return null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (exoPlayer != null){
            if (isPlaying){
                exoPlayer.pause();
                btnPlay.setText(R.string.play);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (exoPlayer != null){
            exoPlayer.play();
            btnPlay.setText(R.string.pause);
        }
    }
}