package com.example.user.recyclerviewdemoapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.recyclerviewdemoapplication.Model.Row;
import com.example.user.recyclerviewdemoapplication.R;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.List;

/**
 * Created on 07-03-2019.
 * @author Priyanka Gole
 * Adapter for displaying list items
 */

public class DisplayListAdapter extends RecyclerView.Adapter<DisplayListAdapter.BlogAdapterViewHolder> {

    private List<Row> rowList;
    private Context context;

    public static class BlogAdapterViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txt_title;
        public final JustifiedTextView txt_content;
        public final ImageView image;


        public BlogAdapterViewHolder(View v) {
            super(v);
            mView = v;

            txt_title = (TextView) mView.findViewById(R.id.txt_title);
            txt_content = (JustifiedTextView) mView.findViewById(R.id.txt_content);
            image = (ImageView) mView.findViewById(R.id.image);
        }
    }

    public DisplayListAdapter(List<Row> modelList, Context context) {
        this.rowList = modelList;
        this.context = context;
    }

    @Override
    public BlogAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new BlogAdapterViewHolder(view);
    }

    public void setData(List<Row> modelList) {
        this.rowList=modelList;
    }

    @Override
    public void onBindViewHolder(BlogAdapterViewHolder holder, final int position) {
        holder.txt_title.setText(rowList.get(position).getTitle());
        String description =  rowList.get(position).getDescription();
        holder.txt_content.setText(description);
        String img=rowList.get(position).getImageHref();

        Glide.with(context).
                load(img)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into( holder.image);

        final Row mItem = rowList.get(position);
    }

    @Override
    public int getItemCount() {
        return rowList.size();
    }
}