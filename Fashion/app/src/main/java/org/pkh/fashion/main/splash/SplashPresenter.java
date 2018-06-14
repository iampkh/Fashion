package org.pkh.fashion.main.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.pkh.fashion.Interface.IServerInteractor;
import org.pkh.fashion.Interface.ISplashPresenter;
import org.pkh.fashion.Interface.ISplashView;
import org.pkh.fashion.Model.AllCategory;
import org.pkh.fashion.Model.Category;
import org.pkh.fashion.Model.MenCategory;
import org.pkh.fashion.Model.Product;
import org.pkh.fashion.Model.WomenCategory;
import org.pkh.fashion.utils.HttpHelper;
import org.pkh.fashion.utils.Logger;
import org.pkh.fashion.utils.FashionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class for Splash activity
 * all the background logic should be done here
 * Update the UI using callback mechanism(Interface)
 */
public class SplashPresenter implements ISplashPresenter, IServerInteractor.OnLoadedJsonListener{

    private ISplashView mSplashView;
    private Context mContext;

    /**
     * Request queue to get the json data
     */
    private RequestQueue mRequestQueue;

    private StringRequest mStringRequest;

    /**
     * constructor of Presenter,
     * This class handles all business logic for Splash screen and controls the splash screen.
     * @param splashView : Interface object which is interpretor to the activity.
     */
    SplashPresenter(ISplashView splashView) {
        //ToDO after converting app to Java 8 compatible, use checkArgument() api to check object null.
        this.mSplashView = splashView;
        this.mContext = splashView instanceof Activity ?(Context)splashView : null;
        Logger.logD("Context =" +mContext);
    }

    /**
     * get the network status
     * @return
     */
    public boolean isNetworkConnecte() {
        if(mContext == null){
            return false;
        }
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void getDataFromServer() {
        if(mRequestQueue != null && mStringRequest != null) {
            mRequestQueue.add(mStringRequest);
            Logger.logD("getting data from server");
        }
    }

    @Override
    public void stopFetchingFromServer() {

    }

    @Override
    public void init() {
        Logger.logD("init context =" +mContext);
        if (mContext != null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
            mStringRequest = new StringRequest(Request.Method.GET, FashionUtil.getURL(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Logger.logD("Response received from server=");
                    if(response != null) {
                        new LoadModelAsync(SplashPresenter.this)
                                .execute(response.toString());
                        mRequestQueue.cancelAll(FashionUtil.CATEGORY_PARENT);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Logger.logE("Error :" + error.toString());
                }
            });
            mStringRequest.setTag(FashionUtil.CATEGORY_PARENT);//setting tag to cancel after request is finished
        }
    }

    @Override
    public void destroy() {
        mRequestQueue = null;
        mStringRequest = null;
    }

    @Override
    public void orientationChange(FashionUtil.ORIENTATION orientation) {

    }
    @Override
    public void onLoaded(List<Category> itemCategory) {
        //ToDO As default Home activity is created, Need to handle for all the activity
        if(mSplashView != null){
            mSplashView.stopLoadingProgress();
            //preparing the intent data.
            Intent intent = mSplashView.getNextActivity();
            Bundle args = new Bundle();
            args.putSerializable(FashionUtil.KEY_GET_CATEGORY_LIST, (Serializable) itemCategory);
            intent.putExtra(FashionUtil.KEY_BUNDLE_EXTRA_MAINACTIVITY,args);
            mSplashView.moveToNextActivity(intent);
        }
    }

    @Override
    public void onLoading(int progress) {
        if(mSplashView != null){
            mSplashView.setLoadingProgress(progress);
        }
    }

    class LoadModelAsync extends AsyncTask<String,Integer,List<Category>>{
        /**
         * Http helper to read json from server(Manual parsing)
         */
        HttpHelper mHttpHelper = new HttpHelper();
        /**
         * Total number of products, useful to show progress status.
         */
        private int mTotlProductCount;
        /**
         * No.Of item fetched from server
         */
        int mParsedCount = 0;
        /**
         * listener to update the current parsing status.
         */
        private IServerInteractor.OnLoadedJsonListener mJsonListener;

        LoadModelAsync(IServerInteractor.OnLoadedJsonListener jsonListener){
            mJsonListener = jsonListener;
        }

        /**
         * set the product count
         * @param total : product count
         */
        private void setTotalProductCount(int total) {
            mTotlProductCount = total;
        }

        private List<Product> getProductList(JSONArray itemDetailJson){
            if(itemDetailJson == null) {
                return null;
            }
            List<Product> itemList = new ArrayList<>();
            for(int j=0;j < itemDetailJson.length();j++){

                //updating progress
                publishProgress((int) ((mParsedCount / (float) mTotlProductCount) * 100));
                try {
                    JSONObject obj = itemDetailJson.getJSONObject(j);
                    String id = obj.getString(FashionUtil.ITEM_ID);
                    String name = obj.getString(FashionUtil.ITEM_NAME);
                    String status = obj.getString(FashionUtil.ITEM_STATUS);
                    int likes = obj.getInt(FashionUtil.ITEM_LIKE_COUNT);
                    int comments = obj.getInt(FashionUtil.ITEM_COMMENT_COUNT);
                    int price = obj.getInt(FashionUtil.ITEM_PRICE);
                    String imageUrl = obj.getString(FashionUtil.ITEM_PHOTO_URL);


                    Product item = new Product()
                            .setId(id)
                            .setItemName(name)
                            .setStatus(status)
                            .setCommentCount(comments)
                            .setPrice(price)
                            .setDispImageThumUrl(imageUrl)
                            .setSold(FashionUtil.ITEM_SOLD_OUT.equals(status))
                            .setLikesCount(likes);

                    itemList.add(item);
                    mParsedCount++;//for updating progress bar
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return itemList;
        }
        @Override
        protected List<Category> doInBackground(String... jsonStringArray) {
            List<Category> mListCategory = null;
            String jsonFormatedString = jsonStringArray.length >0 ? jsonStringArray[0]:"";

            Logger.logD("Json processing started");
            if(jsonFormatedString.isEmpty()){return null;}

            mListCategory = new ArrayList<>();
            List<CategoryJson>  mJsonList = getJsonMap(jsonFormatedString);
            Logger.logI("Response received from server");
            //reading all the product and creating List of Category object.
            for( int i=0;i < mJsonList.size();i++){
                CategoryJson categoryJsonObj = mJsonList.get(i);
                JSONArray itemDetailJson = categoryJsonObj.mJsonArray;
                //Creating category data
                Category category;
                switch (categoryJsonObj.mCategoryName){
                    case FashionUtil.CATEGORY_MEN:
                        category = new MenCategory();
                        category.setCategoryType(FashionUtil.CATEGORY.MEN);
                        break;
                    case FashionUtil.CATEGORY_WOMEN:
                        category = new WomenCategory();
                        category.setCategoryType(FashionUtil.CATEGORY.WOMEN);
                        break;
                    case FashionUtil.CATEGORY_ALL:
                        category = new AllCategory();
                        category.setCategoryType(FashionUtil.CATEGORY.ALL);
                        break;
                    default:
                        category = new AllCategory();
                        category.setCategoryType(FashionUtil.CATEGORY.ALL);
                        break;

                }

                category.setCategoryName(categoryJsonObj.mCategoryName);
                category.setCategoryUrl(categoryJsonObj.url);

                List<Product> itemList = getProductList(itemDetailJson);
                category.setItemList(itemList);
                mListCategory.add(category);
            }
            return mListCategory;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(mJsonListener != null){
                mJsonListener.onLoading(values[0]);
                Logger.logD("onProgressUpdate progress ="+values[0]);
            }

        }

        @Override
        protected void onPostExecute(List<Category> aVoid) {
            super.onPostExecute(aVoid);
            if(mJsonListener != null){
                mJsonListener.onLoaded(aVoid);;
            }
        }

        private List<CategoryJson> getJsonMap(String jsonString){
            int totalProductCount = 0;
            List<CategoryJson> masterJsonList = null;
            try {
                masterJsonList = new ArrayList<>();
                JSONArray categoryJsonList = new JSONArray(jsonString);
                Logger.logD("getJsonMap categoryJsonList");
                if(categoryJsonList != null) {
                    int length = categoryJsonList.length();
                    for(int i=0; i < length; i++) {
                        JSONObject itemJsonObj = categoryJsonList.getJSONObject(i);
                        if(itemJsonObj != null) {
                            String itemCategory = itemJsonObj.getString(FashionUtil.CATEGORY_NAME);
                            String itemJson = getJsonFromServer(itemJsonObj.
                                    getString(FashionUtil.JSON_URL_DATA));

                            CategoryJson categoryJson = new CategoryJson();
                            categoryJson.mCategoryName = itemCategory;
                            categoryJson.url = itemJson;
                            categoryJson.mJsonArray = new JSONArray(itemJson);
                            //mandatory to show progress.
                            totalProductCount += categoryJson.mJsonArray.length();
                            masterJsonList.add(categoryJson);
                            Logger.logD("getJsonMap totalProduc ="+totalProductCount);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            setTotalProductCount(totalProductCount);
            return masterJsonList;
        }

        /**
         * Support method to get json string from URL,
         * Manual processing of reading stream from the server
         * Thus should not called in main thread
         * @param url
         * @return
         */
        private String getJsonFromServer(String url){
            return mHttpHelper.getDataFromServer(url);
        }
    }

    /**
     * Support class for reading data from web server
     */
    class CategoryJson {
        String mCategoryName;
        JSONArray mJsonArray;
        String url;
    }
}
