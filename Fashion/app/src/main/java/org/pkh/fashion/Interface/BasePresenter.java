package org.pkh.fashion.Interface;

import org.pkh.fashion.utils.FashionUtil;

public interface BasePresenter {
    public void init();
    public void destroy();
    public void orientationChange(FashionUtil.ORIENTATION orientation);

}
