package com.nakamagaming.dd5espells.interfaces;

import com.nakamagaming.dd5espells.helpers.ClassType;

import java.util.ArrayList;

/**
 * Created by Steffan on 09-May-15.
 */
public interface IFilterChangeListener {
    void onFilterChanged(ArrayList<ClassType> classList);
    void onMinLevelChanged(int minLevel);
    void onMaxLevelChanged(int maxLevel);
}
