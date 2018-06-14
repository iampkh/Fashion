package org.pkh.fashion.utils;

/**
 * Constants which are specific to project
 * are declared here
 */
public class FashionUtil {
    /**
     * Tag name to filter logs
     */
    public static final String TAG = FashionUtil.class.getSimpleName();
    /**
     * WebServer Url
     */
    private static String WEB_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/master.json";
    /**
     * Json key
     */
    public static final String CATEGORY_NAME = "name";
    public static final String JSON_URL_DATA = "data";
    public static final String CATEGORY_PARENT = "parent";
    public static final String CATEGORY_MEN = "Men";
    public static final String CATEGORY_WOMEN = "Woman";
    public static final String CATEGORY_ALL = "All";

    /**
     * Item constant keys
     */
    public static final String ITEM_ID = "id";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_STATUS = "status";
    public static final String ITEM_LIKE_COUNT= "num_likes";
    public static final String ITEM_COMMENT_COUNT = "num_comments";
    public static final String ITEM_PRICE = "price";
    public static final String ITEM_PHOTO_URL = "photo";
    public static final String ITEM_SOLD_OUT = "sold_out";

    /**
     * MercariHomeActivity constants
     */
    public static final String KEY_GET_CATEGORY_LIST = "category_list";
    public static final String KEY_BUNDLE_EXTRA_MAINACTIVITY = "bundle_extra";

    /**
     * Http constants
     */
    public static final String HTTP ="http";
    public static final String HTTPS ="https";

    /**
     * Enum for screens
     */
    public static enum SCREEN{
        SPLASH,HOME,DETAIL
    }
    /**
     * Enum for Category
     */
    public static enum CATEGORY{
        MEN,WOMEN,ALL
    }
    /**
     * Enum for Orientation
     */
    public static enum ORIENTATION{
        POTRAIT,LANDSCAPE
    }

    /**
     * Category Fragment constants for bundle
     */
    public static String KEY_CATEGORY_TITLE = "category_title";
    public static String KEY_CATEGORY = "category";

    /**
     * get json url for the product data
     * @return
     */
    public static String getURL(){
        return WEB_URL;
    }

    /**
     * As this project is created with java 7, later upgrade implement java 8 api
     * checkArgument
     * @param isValid
     * @param msg
     * @throws Exception
     */
    public static void checkArgument(boolean isValid, String msg ){
        //TODO : After java 8 update use checkArgument() api instead of manual imple.,
        if(!isValid){
            try {
                throw new Exception("Mercari Exception : "+msg,new Throwable());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
