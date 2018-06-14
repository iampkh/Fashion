package org.pkh.fashion.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import org.pkh.fashion.utils.FashionUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Base class for items for all categories
 * uses ,Builder pattern
 */
public class Product implements Serializable {
    private String mId = "-1";
    private String mItemName = "Item";
    private String mStatus;
    private int mLikesCount;
    private int mPrice;
    private Comments mComments;
    private boolean isSold = false;
    private DisplayImage mDispImage;

    public String getId() {
        return mId;
    }

    public Product() {
        mComments = new Comments();
        mDispImage = new DisplayImage();
    }

    public Product setId(String mId) {
        this.mId = mId;
        return this;
    }

    public Product setSold(boolean sold) {
        this.isSold = sold;
        return this;
    }

    public boolean isSold() {
        return isSold;
    }

    public String getItemName() {
        return mItemName;
    }

    public Product setItemName(String mItemName) {
        this.mItemName = mItemName;
        return this;
    }

    public String getStatus() {
        return mStatus;
    }

    public Product setStatus(String mStatus) {
        this.mStatus = mStatus;
        return this;
    }

    public int getLikesCount() {
        return mLikesCount;
    }

    public Product setLikesCount(int mLikesCount) {
        this.mLikesCount = mLikesCount;
        return this;
    }

    public int getPrice() {
        return mPrice;
    }

    public Product setPrice(int mPrice) {
        this.mPrice = mPrice;
        return this;
    }

    public Product setThumb(Drawable thumb) {
        this.mDispImage.setThumb(thumb);
        return this;
    }

    public Product setCommentCount(int count) {
        this.mComments.setCount(count);
        return this;
    }


    public Comments getComment() {
        return mComments;
    }

    public DisplayImage getDispImage() {
        return mDispImage;
    }

    public Product setDispImageThumUrl(String url){
        this.mDispImage.setThumbUrl(url);
        return this;
    }
    public class DisplayImage implements Serializable {
        private Drawable mThumb;
        private Drawable mDetailImage;
        private String mThumbUrl;

        public Drawable getThumb() {
            return mThumb;
        }

        public void setThumb(Drawable mThumb) {
            this.mThumb = mThumb;
        }

        public void setThumbUrl(String mThumbUrl) {
            this.mThumbUrl = mThumbUrl.replace(FashionUtil.HTTP+":",
                    FashionUtil.HTTPS+":");
        }

        public String getThumbUrl(){
            return mThumbUrl;
        }

        public Drawable getDetailImage() {
            return mDetailImage;
        }

        public void setDetailImage(Drawable mDetailImage) {
            this.mDetailImage = mDetailImage;
        }
    }

    public class Comments implements Serializable {
        int mCount;
        List<String> mCommentList;

        public int getCount() {
            return mCount;
        }

        public void setCount(int mCount) {
            this.mCount = mCount;
        }

        public List<String> getCommentList() {
            return mCommentList;
        }

        public void setCommentList(List<String> mCommentList) {
            this.mCommentList = mCommentList;
        }
    }
}