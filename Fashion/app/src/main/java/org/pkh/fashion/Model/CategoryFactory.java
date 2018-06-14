package org.pkh.fashion.Model;

import org.pkh.fashion.utils.FashionUtil;

public class CategoryFactory {
    public static Category getCategory(String category){
        switch (category){
            case FashionUtil.CATEGORY_MEN:
                return new MenCategory();
            case FashionUtil.CATEGORY_ALL:
                return new AllCategory();
            case FashionUtil.CATEGORY_WOMEN:
                return new WomenCategory();
            default:
                    return new Category();
        }
    }
}
