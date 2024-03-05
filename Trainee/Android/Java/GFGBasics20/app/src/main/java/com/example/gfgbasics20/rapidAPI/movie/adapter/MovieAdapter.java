package com.example.gfgbasics20.rapidAPI.movie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gfgbasics20.R;
import com.example.gfgbasics20.rapidAPI.movie.Demo.Results;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Results> list;

    public MovieAdapter(Context context, List<Results> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Results results = list.get(position);

        String image = results.getImageurl().toString();
        image = image.substring(1, image.length() - 1);
        Glide.with(context)
                .load(image)
                .centerCrop()
                .error(R.drawable.carone)
                .into(holder.imgMovie);

        String log = results.getImageurl().toString();
        Log.e("Print All image link here : ", log);

        String titleYear = results.getTitle() + " (" + results.getReleased() + ")";
        holder.tvTitleYear.setText(titleYear);

        String imdb = String.valueOf(results.getImdbrating()) + "/10";
        holder.tvImdb.setText(imdb);

        holder.tvSynopsis.setText(results.getSynopsis());

        holder.cgGenre.removeAllViews();

        for (int i = 0; i < results.getGenre().size(); i++){
            Chip chip = new Chip(context);
            chip.setText(results.getGenre().get(i));
            holder.cgGenre.addView(chip);
        }

        MobileAds.initialize(context);
        loadRewardedVideoAd();

        holder.tvImdb.setOnClickListener(v->{
            showRewardedVideoAd();
        });
    }

    public void loadData(List<Results> results){
        list = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView tvTitleYear, tvImdb, tvSynopsis;
        ChipGroup cgGenre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMovie);
            tvTitleYear = itemView.findViewById(R.id.tvTitleYear);
            tvImdb = itemView.findViewById(R.id.tvImdb);
            tvSynopsis = itemView.findViewById(R.id.tvSynopsis);
            cgGenre = itemView.findViewById(R.id.cgGenre);
        }
    }
    private RewardedVideoAd AdMobrewardedVideoAd;
    private final String AdId = "ca-app-pub-3940256099942544/5224354917";

    void loadRewardedVideoAd()
    {
        AdMobrewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);

        // Rewarded Video Ad Listener
        AdMobrewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                    @Override
                    public void onRewardedVideoAdLoaded()
                    {
                        Toast.makeText(context, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRewardedVideoAdOpened()
                    {

                    }

                    @Override
                    public void onRewardedVideoStarted()
                    {

                    }

                    @Override
                    public void onRewardedVideoAdClosed()
                    {

                    }

                    @Override
                    public void onRewarded(RewardItem rewardItem)
                    {

                    }

                    @Override
                    public void
                    onRewardedVideoAdLeftApplication()
                    {

                    }

                    @Override
                    public void onRewardedVideoAdFailedToLoad(int i)
                    {

                    }

                    @Override
                    public void onRewardedVideoCompleted()
                    {

                    }
                });

        // Loading Rewarded Video Ad
        AdMobrewardedVideoAd.loadAd(
                AdId, new AdRequest.Builder().build());
    }

    public void showRewardedVideoAd()
    {
        if (AdMobrewardedVideoAd.isLoaded()) {
            AdMobrewardedVideoAd.show();
        }
        else {
            AdMobrewardedVideoAd.loadAd(
                    AdId, new AdRequest.Builder().build());
        }
    }
}
