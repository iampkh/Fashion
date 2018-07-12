package com.skilltest.fashion.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skilltest.fashion.R;
import com.skilltest.fashion.network.model.Item;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListHolder> {

    private List<Item> mItemList;
    private RecyclerViewWrapper.OnItemClickListner mOnItemClickListner;

    private ItemListAdapter(){}
    public ItemListAdapter(RecyclerViewWrapper.OnItemClickListner listener){
        mOnItemClickListner = listener;
    }

    @Override
    public ItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cardView = layoutInflater.inflate(R.layout.listitem_view, parent, false);
        return new ItemListHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final ItemListHolder holder, final int position) {
        if(mItemList != null && getItemCount() > position){
            Item item = mItemList.get(position);
            holder.name.setText(item.getName());
            holder.price.setText(""+item.getPrice());
            holder.comments.setText(""+item.getCommentsCount());
            holder.likes.setText(""+item.getLikesCount());
            String thumbUrl = item.getImageUrl();

            //Picasso library implementation to fetch image from url
            Picasso.with(holder.itemImage.getContext())
                    .load(thumbUrl)
                    .into(holder.itemImage, new Callback() {
                        @Override
                        public void onSuccess() {holder.progressBar.setVisibility(GONE);}
                        @Override
                        public void onError() { }
                    });

            if(!item.isSold()) {
                holder.soldOutImage.setVisibility(View.INVISIBLE);
            }

            //TODO: implementation need to change to lambda expression
            holder.itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListner != null){
                        mOnItemClickListner.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList !=null ? mItemList.size():0;
    }

    public void setItemList(List<Item> items){
        mItemList = items;
        notifyDataSetChanged();
    }
    public List<Item> getItemList(){
        return mItemList;
    }


    class ItemListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.price)protected TextView price;
        @BindView(R.id.like_count)protected TextView likes;
        @BindView(R.id.comment_count)protected TextView comments;
        @BindView(R.id.name)protected TextView name;

        @BindView(R.id.item_thumb)protected ImageView itemImage;
        @BindView(R.id.sold_out)protected  ImageView soldOutImage;

        @BindView(R.id.progressBar)protected ProgressBar progressBar;

        public ItemListHolder(View itemView) {
            super(itemView);
            if(itemView != null) {
                ButterKnife.bind(this,itemView);
            }
        }
    }

}