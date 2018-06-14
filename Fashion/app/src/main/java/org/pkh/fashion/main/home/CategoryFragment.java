package org.pkh.fashion.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.pkh.fashion.Interface.BasePresenter;
import org.pkh.fashion.Interface.IBaseView;
import org.pkh.fashion.Model.Category;
import org.pkh.fashion.Model.Product;
import org.pkh.fashion.R;
import org.pkh.fashion.utils.Logger;
import org.pkh.fashion.utils.FashionUtil;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class CategoryFragment extends Fragment implements IBaseView{

    /**
     * category object with view info
     */
    private Category mCategory = null;

    private RecyclerView mRecyclerViewProduct = null;
    private GridLayoutManager mGridLayoutManager = null;
    private RecyclerViewDataAdapter mRecyclerViewAdapter = null;

    /**
     * create a new instance of this fragment,(Android rule dont override constructor)
     *
     * @param category :Category object
     * @return new instance of CategoryFragment
     */
    public static CategoryFragment newInstance(Category category){
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FashionUtil.KEY_CATEGORY,(Serializable)category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = (Category) getArguments().getSerializable(FashionUtil.KEY_CATEGORY);
        FashionUtil.checkArgument(mCategory != null,
                "Category objec is null");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_frgment, container, false);
        mRecyclerViewProduct = (RecyclerView)view.findViewById(R.id.product_list_recycle_view);
        //TextView tvLabel = (TextView) view.findViewById(R.id.sampText);
        if(mCategory != null) {
            String categoryName = mCategory.getCategoryName();
            Logger.logD("CategoryFrag text set ="+categoryName);
            mGridLayoutManager = new GridLayoutManager(this.getActivity().getApplicationContext(), 2);
            // Set layout manager.
            mRecyclerViewProduct.setLayoutManager(mGridLayoutManager);
            mRecyclerViewAdapter = new RecyclerViewDataAdapter(mCategory.getItemList());
            mRecyclerViewProduct.setAdapter(mRecyclerViewAdapter);

            mRecyclerViewAdapter.notifyDataSetChanged();

            //tvLabel.setText(categoryName);
        }
        return view;
    }

    @Override
    public void onConfigurationChange(FashionUtil.ORIENTATION orientation) {
        Logger.logD("onConfigurationChange frag="+orientation);
        switch (orientation) {
            case POTRAIT:
                mGridLayoutManager.setSpanCount(2);
                mRecyclerViewAdapter.notifyDataSetChanged();
                break;
            case LANDSCAPE:
                mGridLayoutManager.setSpanCount(3);
                mRecyclerViewAdapter.notifyDataSetChanged();
                break;
        }
    }

    class RecyclerViewDataAdapter extends RecyclerView.Adapter<ProductRecyclerViewHolder> {

        List<Product> mProductList = null;

        public RecyclerViewDataAdapter(List<Product> productList){
            mProductList = productList;
            Logger.logD("RecyclerViewDataAdapter = "+mProductList.size());
        }
        @Override
        public ProductRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            View cardView = layoutInflater.inflate(R.layout.item_info, parent, false);
            //initiating views
            final TextView price = (TextView)cardView.findViewById(R.id.price);
            final TextView likes = (TextView)cardView.findViewById(R.id.like_count);
            final TextView comments = (TextView)cardView.findViewById(R.id.comment_count);
            final TextView name = (TextView)cardView.findViewById(R.id.name);

            final ImageView itemImage = (ImageView)cardView.findViewById(R.id.item_thumb);
            final ImageView soldOutImage = (ImageView)cardView.findViewById(R.id.sold_out);

            ProductRecyclerViewHolder holder = new ProductRecyclerViewHolder(cardView);
            Logger.logD("RecyclerViewDataAdapter onCreateViewHolder = ");
            return holder;
        }

        @Override
        public void onBindViewHolder(ProductRecyclerViewHolder holder, int position) {
            if(mProductList != null) {
                Product item = mProductList.get(position);
                Logger.logD("onBindViewHolder ITEM = "+item);
                updateProductView(item,holder);
            }
        }

        private void updateProductView(Product item,ProductRecyclerViewHolder holder) {
            if (item != null && holder != null) {
                holder.name.setText(item.getItemName());
                holder.price.setText(""+item.getPrice());
                holder.comments.setText(""+item.getComment().getCount());
                holder.likes.setText(""+item.getLikesCount());

                //holder.itemImage.setImageDrawable();
                holder.itemImage.setImageResource(R.drawable.def_item);
                Product.DisplayImage displayImage = item.getDispImage();
                String thumbUrl = displayImage.getThumbUrl();
                Logger.logD("ImageUrl="+thumbUrl);
                Picasso.with(holder.itemImage.getContext())
                        .load(thumbUrl)
                        .into(holder.itemImage);


                if(!item.isSold()) {
                    holder.soldOutImage.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            if(mProductList != null) {
                Logger.logD("getItemCount = "+mProductList.size());
                return mProductList.size();
            }
            return 0;
        }
    }

    class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {
        protected TextView price,likes,comments,name;
        protected ImageView itemImage,soldOutImage;

        public ProductRecyclerViewHolder(View itemView) {
            super(itemView);
            if(itemView != null) {
                price = (TextView)itemView.findViewById(R.id.price);
                likes = (TextView)itemView.findViewById(R.id.like_count);
                comments = (TextView)itemView.findViewById(R.id.comment_count);
                name = (TextView)itemView.findViewById(R.id.name);

                itemImage = (ImageView)itemView.findViewById(R.id.item_thumb);
                soldOutImage = (ImageView)itemView.findViewById(R.id.sold_out);

            }
        }
    }

}
