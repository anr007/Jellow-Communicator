package com.dsource.idc.jellow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dsource.idc.jellow.Utility.SessionManager;

import java.io.File;

/**
 * Created by Sumeet on 19-04-2016.
 */
class LevelTwoAdapter extends android.support.v7.widget.RecyclerView.Adapter<LevelTwoAdapter.MyViewHolder> {
    private Context mContext;
    private SessionManager mSession;
    private String[] mThumbIds;
    private String[] belowText;
    private String path;

    LevelTwoAdapter(Context context, int levelTwoItemPos){
        mContext = context;
        mSession = new SessionManager(mContext);
        loadArraysFromResources(levelTwoItemPos);
        File en_dir = mContext.getDir(mSession.getLanguage(), Context.MODE_PRIVATE);
        path = en_dir.getAbsolutePath()+"/drawables";
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int GRID_1BY3 = 0;
        View rowView;
        if (mSession.getGridSize() == GRID_1BY3)
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_3_icons, parent, false);
        else
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_9_icons, parent, false);
        return new LevelTwoAdapter.MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final int MODE_PICTURE_ONLY = 1;
        if (mSession.getPictureViewMode() == MODE_PICTURE_ONLY)
            holder.menuItemBelowText.setVisibility(View.INVISIBLE);
        holder.menuItemBelowText.setText(belowText[position]);
        //holder.menuItemImage.setImageDrawable(mThumbIds.getDrawable(position));
        GlideApp.with(mContext)
                .load(path+"/"+mThumbIds[position]+".png")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false)
                .dontAnimate()
                .into(holder.menuItemImage);
        holder.menuItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ((LevelTwoActivity)mContext).tappedGridItemEvent(holder.menuItemLinearLayout, v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mThumbIds.length;
    }

    private void loadArraysFromResources(int levelTwoItemPos) {
        if (levelTwoItemPos == 0){
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoGreetFeelIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoGreetFeelAdapterText);
        } else if (levelTwoItemPos == 1){
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoDailyActIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoDailyActAdapterText);
        } else if (levelTwoItemPos == 2){
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoEatingIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoEatAdapterText);
        } else if (levelTwoItemPos == 3){
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoFunIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoFunAdapterText);
        } else if (levelTwoItemPos == 4) {
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoLearningIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoLearningAdapterText);
        } else if (levelTwoItemPos == 7) {
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoTimeIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoTimeWeatherAdapterText);
        } else if (levelTwoItemPos == 8) {
            mThumbIds = mContext.getResources().getStringArray(R.array.arrLevelTwoHelpIconAdapter);
            belowText = mContext.getResources().getStringArray(R.array.arrLevelTwoHelpAdapterText);
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout menuItemLinearLayout;
        private ImageView menuItemImage;
        private TextView menuItemBelowText;

        MyViewHolder(final View view) {
            super(view);
            menuItemImage = (ImageView) view.findViewById(R.id.icon1);
            menuItemLinearLayout = (LinearLayout) view.findViewById(R.id.linearlayout_icon1);
            menuItemBelowText = (TextView) view.findViewById(R.id.te1);
            Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/Mukta-Regular.ttf");
            menuItemBelowText.setTypeface(font);
            menuItemBelowText.setTextColor(Color.rgb(64, 64, 64));
        }
    }
}
