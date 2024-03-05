package com.example.androidtest.mediafragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidtest.R;

public class VideoFragment extends Fragment {

    VideoView videoView;
    MediaController mediaController;
    private static int stopPosition = 8000;

    Button btnPLay, btnPause;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        videoView = view.findViewById(R.id.videoView);
        btnPause = view.findViewById(R.id.btnPause);
        btnPLay = view.findViewById(R.id.btnPlay);

        if (mediaController == null) {
            mediaController = new MediaController(getActivity());
            mediaController.setAnchorView(videoView);
        }

        videoView.setMediaController(mediaController);

        videoView.start();

        videoView.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.sound_of_salar));
        videoView.requestFocus();

        videoView.setOnCompletionListener(
                mediaPlayer -> Toast.makeText(getActivity(), "Video Completed", Toast.LENGTH_LONG).show());

        videoView.setOnErrorListener(
                (mediaPlayer, i, i1) -> {
                    Toast.makeText(getActivity(), "An Error Occurred \" +\n" + "\"While Playing Video !!!", Toast.LENGTH_SHORT).show();
                    return false;
                });

        btnPLay.setOnClickListener(v->{
            videoView.seekTo(stopPosition);
            videoView.start();
        });

        btnPause.setOnClickListener(v->{
            stopPosition = videoView.getCurrentPosition();
            Toast.makeText(getActivity(), String.valueOf(stopPosition), Toast.LENGTH_SHORT).show();
            videoView.pause();
        });
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("888888888888", " Frag 1 start");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("888888888888888", "Frag 1 Pause");
        if(videoView.isPlaying()){
            Log.e("11111111111111", String.valueOf(stopPosition));
            stopPosition = videoView.getCurrentPosition();
            Toast.makeText(getActivity(), String.valueOf(stopPosition), Toast.LENGTH_SHORT).show();
            videoView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("888888888888", "Frag 1 resume");
        videoView.seekTo(stopPosition);
        videoView.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("888888888888", " Frag 1 stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("888888888888", " Frag 1 destroy");
        videoView.stopPlayback();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}