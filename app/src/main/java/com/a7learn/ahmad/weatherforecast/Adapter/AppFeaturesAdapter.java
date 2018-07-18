package com.a7learn.ahmad.weatherforecast.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a7learn.ahmad.weatherforecast.Model.AppFeature;
import com.a7learn.ahmad.weatherforecast.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mody Boss on 5/18/2018.
 */
public class AppFeaturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<AppFeature> appFeatures = new ArrayList<>();
    private Typeface iranSansFont;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_DEFAULT_ITEM = 1;

    public AppFeaturesAdapter(Context context) {
        this.context = context;
        iranSansFont = Typeface.createFromAsset(context.getAssets(), "fonts/iranian_sans.ttf");

    }

    public void setAppFeatures(List<AppFeature> appFeatures) {
        this.appFeatures = appFeatures;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                View view = LayoutInflater.from(context).inflate(R.layout.layout_app_feature_banner, parent, false);
                return new AppFeatureBanner(view, iranSansFont);
            case VIEW_TYPE_DEFAULT_ITEM:
                return new AppFeatureViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.layout_app_feature, parent, false), iranSansFont);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AppFeatureViewHolder) {
            AppFeatureViewHolder viewHolder = (AppFeatureViewHolder) holder;
            viewHolder.bindAppFeature(appFeatures.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return appFeatures.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_DEFAULT_ITEM;
        }
    }

    public static class AppFeatureViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView chapterTitle;

        public AppFeatureViewHolder(View itemView, Typeface iranSansFont) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.feature_image_view);
            chapterTitle = (TextView) itemView.findViewById(R.id.feature_title);
            chapterTitle.setTypeface(iranSansFont);
        }

        public void bindAppFeature(final AppFeature appFeature) {
            Picasso.with(itemView.getContext()).load(appFeature.getFeatureImage()).into(imageView);
            chapterTitle.setText(appFeature.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(),
                            appFeature.getDestinationActivity()));
                }
            });
        }
    }

    public static class AppFeatureBanner extends RecyclerView.ViewHolder {
        private TextView appFeaturesLabel;

        public AppFeatureBanner(View itemView, Typeface typeface) {
            super(itemView);
            appFeaturesLabel = (TextView) itemView.findViewById(R.id.label_app_features_list);
            appFeaturesLabel.setTypeface(typeface);
        }
    }

}