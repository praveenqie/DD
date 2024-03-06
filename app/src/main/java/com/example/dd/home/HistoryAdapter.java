package com.example.dd.home;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dd.R;
import com.example.dd.home.dto.HistoryItem;

import java.util.List;

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryItem> itemList;

    public HistoryAdapter(List<HistoryItem> itemList) {
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem item = itemList.get(position);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImageRes());

        //Scroll title right
        holder.textView.setSingleLine(true);
        holder.textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.textView.setMarqueeRepeatLimit(-1);
        holder.textView.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.historyImageView);
            textView = itemView.findViewById(R.id.historyTextView);
        }
    }
}
