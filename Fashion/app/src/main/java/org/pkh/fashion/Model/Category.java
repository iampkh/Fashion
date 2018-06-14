package org.pkh.fashion.Model;

import org.pkh.fashion.utils.FashionUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Category class, Base for all type of category.
 * Note:This is implements Serializable inorder to sharing object betwee activities using bundle.
 */
public class Category implements Serializable{
    String mCategoryName;
    String mCategoryUrl;
    List<Product> mItemList;
    boolean mIsParent = false;
    FashionUtil.CATEGORY type;

    public void setCategoryType(FashionUtil.CATEGORY type){
        this.type = type;
    }

    public FashionUtil.CATEGORY getType() {
        return type;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getCategoryUrl() {
        return mCategoryUrl;
    }

    public void setCategoryUrl(String mCategoryUrl) {
        this.mCategoryUrl = mCategoryUrl;
    }

    public void setIsParent(boolean isParent){
        mIsParent = isParent;
    }
    public boolean isParent(){
        return mIsParent;
    }
    public List<Product> getItemList() {
        return mItemList;
    }

    public void setItemList(List<Product> mItemList) {
        this.mItemList = mItemList;
    }
    public void addItem(Product item) {
        if(mItemList != null) {
            this.mItemList.add(item);
        }
    }

}
