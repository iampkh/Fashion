package org.pkh.fashion.Model;

import org.pkh.fashion.utils.FashionUtil;

import java.util.List;
/**
 * AllCategory class, Base for all type of category.
 * Any specific category based data should be implemented here
 */
public class AllCategory extends Category {
    @Override
    public FashionUtil.CATEGORY getType() {
        return FashionUtil.CATEGORY.ALL;
    }
}
