package com.nakamagaming.dd5espells.views;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.nakamagaming.dd5espells.R;
import com.nakamagaming.dd5espells.helpers.ClassType;
import com.nakamagaming.dd5espells.interfaces.IFilterChangeListener;

import java.util.ArrayList;

/**
 * Created by Steffan on 09-May-15.
 */
public class OptionsView {

    private ArrayList<ClassType> mFilteredClasses;
    private IFilterChangeListener mListener;

    public OptionsView(View view, IFilterChangeListener listener) {
        //initiate filterable list.
        mFilteredClasses = new ArrayList<ClassType>();
        mFilteredClasses.add(ClassType.BARD);
        mFilteredClasses.add(ClassType.CLERIC);
        mFilteredClasses.add(ClassType.DRUID);
        mFilteredClasses.add(ClassType.PALADIN);
        mFilteredClasses.add(ClassType.RANGER);
        mFilteredClasses.add(ClassType.SORCERER);
        mFilteredClasses.add(ClassType.WARLOCK);
        mFilteredClasses.add(ClassType.WIZARD);

        mListener = listener;

        CheckBox bardCB = (CheckBox) view.findViewById(R.id.checkbox_filter_bard);
        bardCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.BARD));

        CheckBox clericCB = (CheckBox) view.findViewById(R.id.checkbox_filter_cleric);
        clericCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.CLERIC));

        CheckBox druidCB = (CheckBox) view.findViewById(R.id.checkbox_filter_druid);
        druidCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.DRUID));

        CheckBox paladinCB = (CheckBox) view.findViewById(R.id.checkbox_filter_paladin);
        paladinCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.PALADIN));

        CheckBox wizardCB = (CheckBox) view.findViewById(R.id.checkbox_filter_wizard);
        wizardCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.WIZARD));

        CheckBox rangerCB = (CheckBox) view.findViewById(R.id.checkbox_filter_ranger);
        rangerCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.RANGER));

        CheckBox sorcererCB = (CheckBox) view.findViewById(R.id.checkbox_filter_sorcerer);
        sorcererCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.SORCERER));

        CheckBox warlockCB = (CheckBox) view.findViewById(R.id.checkbox_filter_warlock);
        warlockCB.setOnCheckedChangeListener(new OnClassTypeCheckedChangeListener(ClassType.WARLOCK));
    }

    private class OnClassTypeCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private ClassType mClassType;

        public OnClassTypeCheckedChangeListener(ClassType classType) {
            mClassType = classType;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isChecked && mFilteredClasses.contains(mClassType)) {
                mFilteredClasses.remove(mClassType);
            } else if (isChecked && !mFilteredClasses.contains(mClassType)) {
                mFilteredClasses.add(mClassType);
            }
            mListener.onFilterChanged(mFilteredClasses);
        }
    }
}
