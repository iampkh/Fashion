package org.pkh.fashion.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.pkh.fashion.R;
import org.pkh.fashion.utils.FashionUtil;

public class DummyActivity extends BaseFashionActivity {



    @Override
    public void onConfigurationChange(FashionUtil.ORIENTATION orientation) {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info);
    }
}
