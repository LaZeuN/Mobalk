package com.example.androidmedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MediaContentAdapter extends RecyclerView.Adapter<MediaContentAdapter.ViewHolder> implements Filterable {

    private ArrayList<MediaContent> mMediaContentsData;
    private ArrayList<MediaContent> mMediaContentsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    MediaContentAdapter(Context context, ArrayList<MediaContent> contentsData) {
        this.mMediaContentsData = contentsData;
        this.mMediaContentsDataAll = contentsData;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_content, parent, false));
    }

    @Override
    public void onBindViewHolder(MediaContentAdapter.ViewHolder holder, int position) {
        MediaContent currentContent = mMediaContentsData.get(position);

        holder.bindTo(currentContent);

        if (holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mMediaContentsData.size();
    }

    @Override
    public Filter getFilter() {
        return mediaFilter;
    }

    private Filter mediaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<MediaContent> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0) {
                results.count = mMediaContentsDataAll.size();
                results.values = mMediaContentsDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (MediaContent content : mMediaContentsDataAll) {
                    if (content.getSubject().toLowerCase().contains(filterPattern)) {
                        filteredList.add(content);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            mMediaContentsData = (ArrayList) filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends  RecyclerView.ViewHolder {

        private ImageView mContent;
        private TextView mIdentifier;
        private TextView mBasedOn;
        private TextView mStatus;
        private TextView mType;
        private TextView mModality;
        private TextView mView;
        private TextView mSubject;
        private TextView mEncounter;
        private TextView mCreatedDateTime;
        private TextView mCreatedPeriod;
        private TextView mIssued;
        private TextView mOperator;
        private TextView mReasonCode;
        private TextView mBodySite;
        private TextView mDeviceName;
        private TextView mDevice;
        private TextView mHeight;
        private TextView mWidth;
        private TextView mFrames;
        private TextView mDuration;
        private TextView mNote;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mContent = itemView.findViewById(R.id.contentImage);
            mIdentifier = itemView.findViewById(R.id.contentIdentifier);
            mBasedOn = itemView.findViewById(R.id.contentBasedOn);
            mStatus = itemView.findViewById(R.id.contentStatus);
            mType = itemView.findViewById(R.id.contentType);
            mModality = itemView.findViewById(R.id.contentModality);
            mView = itemView.findViewById(R.id.contentView);
            mSubject = itemView.findViewById(R.id.contentSubject);
            mEncounter = itemView.findViewById(R.id.contentEncounter);
            mCreatedDateTime = itemView.findViewById(R.id.contentCreatedDateTime);
            mCreatedPeriod = itemView.findViewById(R.id.contentCreatedPeriod);
            mIssued = itemView.findViewById(R.id.contentIssued);
            mOperator = itemView.findViewById(R.id.contentOperator);
            mReasonCode = itemView.findViewById(R.id.contentReasonCode);
            mBodySite = itemView.findViewById(R.id.contentBodySite);
            mDeviceName = itemView.findViewById(R.id.contentDeviceName);
            mDevice = itemView.findViewById(R.id.contentDevice);
            mHeight = itemView.findViewById(R.id.contentHeight);
            mWidth = itemView.findViewById(R.id.contentWidth);
            mFrames = itemView.findViewById(R.id.contentFrames);
            mDuration = itemView.findViewById(R.id.contentDuration);
            mNote = itemView.findViewById(R.id.contentNote);
        }


        public void bindTo(MediaContent currentContent) {

            Glide.with(mContext).load(currentContent.getContent()).into(mContent);

            mIdentifier.setText(currentContent.getIdentifier());
            mBasedOn.setText(currentContent.getBasedOn());
            mStatus.setText(currentContent.getStatus());
            mType.setText(currentContent.getType());
            mModality.setText(currentContent.getModality());
            mView.setText(currentContent.getView());
            mSubject.setText(currentContent.getSubject());
            mEncounter.setText(currentContent.getEncounter());
            mCreatedDateTime.setText(currentContent.getCreatedDateTime());
            mCreatedPeriod.setText(currentContent.getCreatedPeriod());
            mIssued.setText(currentContent.getIssued());
            mOperator.setText(currentContent.getOperator());
            mReasonCode.setText(currentContent.getReasonCode());
            mBodySite.setText(currentContent.getBodySite());
            mDeviceName.setText(currentContent.getDeviceName());
            mDevice.setText(currentContent.getDevice());
            mHeight.setText(currentContent.getHeight());
            mWidth.setText(currentContent.getWidth());
            mFrames.setText(currentContent.getFrames());
            mDuration.setText(currentContent.getDuration());
            mNote.setText(currentContent.getNote());

            itemView.findViewById(R.id.delete).setOnClickListener(view -> ((MediaListActivity)mContext).deleteContent(currentContent));
        }
    }

}
