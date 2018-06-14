package org.pkh.fashion.main.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.pkh.fashion.Interface.IHomeView;
import org.pkh.fashion.Model.Category;
import org.pkh.fashion.R;
import org.pkh.fashion.main.BaseFashionActivity;
import org.pkh.fashion.main.DummyActivity;
import org.pkh.fashion.utils.Logger;
import org.pkh.fashion.utils.FashionUtil;

import java.util.ArrayList;

public class FashionHomeActivity extends BaseFashionActivity
        implements IHomeView,View.OnClickListener{

    ViewPager mViewPagerCategory;
    FloatingActionButton mCameraBtn;

    CategoryAdapter mCategoryAdapter;
    HomePresenter mHomeBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.icon_launcher);
        setSupportActionBar(toolbar);
        //initiating view objects
        mViewPagerCategory = (ViewPager) findViewById(R.id.categoryPager);
        mCameraBtn = (FloatingActionButton) findViewById(R.id.fab);
        mHomeBasePresenter = new HomePresenter();

        //retreiving data from the bundle.
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(FashionUtil.KEY_BUNDLE_EXTRA_MAINACTIVITY);
        ArrayList<Category> categoryList =
                (ArrayList<Category>) bundle.getSerializable(FashionUtil.KEY_GET_CATEGORY_LIST);
        mCategoryAdapter = new CategoryAdapter(getSupportFragmentManager() ,
                categoryList, mHomeBasePresenter);
         Logger.logD("MercariHomeActivity arrayList size="+ categoryList.size());
    }



    /**
     * This part initializes view, so calling method
     * should be from main thread
     */
    protected void init(){
        if(mCameraBtn !=  null) {
            mCameraBtn.setOnClickListener(this);
        }
        if(mHomeBasePresenter != null){
            mHomeBasePresenter.init();
        }
        if(mViewPagerCategory!= null) {
            mViewPagerCategory.setAdapter(mCategoryAdapter);
        }

    }

    /**
     * nullify the objects created for this activity.
     */
    protected void destroy(){
        if(mCategoryAdapter != null) {
            mCategoryAdapter.destroy();
        }
        if (mHomeBasePresenter != null) {
            mHomeBasePresenter.destroy();
        }
        mCategoryAdapter = null;
        mCameraBtn = null;
        mViewPagerCategory = null;
        mHomeBasePresenter = null;
    }

    /**
     * Intent builder to launch this activity
     * @param context
     * @return
     */
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context,FashionHomeActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),DummyActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChange(FashionUtil.ORIENTATION orientation) {
        if(mHomeBasePresenter != null) {
            mHomeBasePresenter.orientationChange(orientation);
        }
    }


    class CategoryAdapter extends FragmentPagerAdapter{
        ArrayList<Category> mCategoryList = null;
        HomePresenter mBasePresenter = null;

        public CategoryAdapter(FragmentManager fm, ArrayList<Category> categoriesList,
                               HomePresenter basePresenter ) {
            super(fm);
            mCategoryList = categoriesList;
            mBasePresenter =basePresenter;
        }

        @Override
        public int getCount() {
            if(mCategoryList != null) {
                Logger.logD("Home :size ="+mCategoryList.size());
                /*for(int i=0;i<mCategoryList.size();i++){
                    Logger.logD("Home :size ="+mCategoryList.get(i));
                }*/
                return mCategoryList.size();
            }
            return 0;
        }

        public void destroy() {
            mCategoryList = null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(mCategoryList != null && position < mCategoryList.size()){
                return mCategoryList.get(position).getCategoryName();
            }
            return "PAGE:";
        }

        @Override
        public Fragment getItem(int position) {
            Category category = new Category();

            if(mCategoryList != null && mCategoryList.size()>position) {
                Logger.logD("Home :position ="+position);
                category = mCategoryList.get(position);
            }
            CategoryFragment fragment = CategoryFragment.newInstance(category);
            mBasePresenter.addView(fragment);
            return fragment;
        }
    }
}
