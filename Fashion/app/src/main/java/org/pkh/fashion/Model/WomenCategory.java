package org.pkh.fashion.Model;

import org.pkh.fashion.utils.FashionUtil;
import org.pkh.fashion.Model.Category;

import java.util.List;
/**
 * WoMenCategory class, Base for all type of category.
 * Any specific category based data should be implemented here
 */
public class WomenCategory extends Category {
    @Override
    public FashionUtil.CATEGORY getType() {
        return FashionUtil.CATEGORY.WOMEN;
    }
}
