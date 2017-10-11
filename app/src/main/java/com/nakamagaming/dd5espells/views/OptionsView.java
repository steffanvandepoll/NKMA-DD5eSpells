package com.nakamagaming.dd5espells.views;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

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

    private SeekBar minLevelBar;
    private SeekBar maxLevelBar;

    private TextView minLvlTxt;
    private TextView maxLvlTxt;

    public OptionsView(View view, IFilterChangeListener listener) {
        //initiate filterable list.
        mFilteredClasses = new ArrayList<>();
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

        minLevelBar = (SeekBar)view.findViewById(R.id.seekBar_min_lvl);
        minLevelBar.setOnSeekBarChangeListener(new OnLevelSeekBarChangeListener(true));

        maxLevelBar = (SeekBar)view.findViewById(R.id.seekBar_max_lvl);
        maxLevelBar.setOnSeekBarChangeListener(new OnLevelSeekBarChangeListener(false));

        minLvlTxt = (TextView)view.findViewById(R.id.textView_min_lvl);
        maxLvlTxt = (TextView)view.findViewById(R.id.textView_max_lvl);
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

    private class OnLevelSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private boolean isMin;

        public OnLevelSeekBarChangeListener(boolean isMin){
            this.isMin = isMin;
        }

        @Override
        public void onProgressChanged(SeekBar bar, int value, boolean fromUser){
            if(isMin) {
                mListener.onMinLevelChanged(value);
                minLvlTxt.setText(String.valueOf(value));

                if(value > maxLevelBar.getProgress())
                    maxLevelBar.setProgress(value);
            }
            else {
                mListener.onMaxLevelChanged(value);
                maxLvlTxt.setText(String.valueOf(value));

                if(value < minLevelBar.getProgress())
                    minLevelBar.setProgress(value);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar bar){
            // do nothing.
        }

        @Override
        public void onStopTrackingTouch(SeekBar bar){
            // do nothing.
        }
    }
}
