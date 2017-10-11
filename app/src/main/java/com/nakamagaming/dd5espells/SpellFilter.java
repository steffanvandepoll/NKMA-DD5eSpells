package com.nakamagaming.dd5espells;

import com.nakamagaming.dd5espells.helpers.ClassType;

import java.util.ArrayList;

/**
 * Created by Thijs on 12/10/2017.
 */

public class SpellFilter {

    private String text;
    private int minLevel;
    private int maxLevel;
    private ArrayList<ClassType> classes = new ArrayList<ClassType>();

    public SpellFilter(){
        classes.add(ClassType.BARD);
        classes.add(ClassType.CLERIC);
        classes.add(ClassType.DRUID);
        classes.add(ClassType.PALADIN);
        classes.add(ClassType.RANGER);
        classes.add(ClassType.SORCERER);
        classes.add(ClassType.WARLOCK);
        classes.add(ClassType.WIZARD);

        minLevel = 0;
        maxLevel = 9;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public ArrayList<ClassType> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassType> classes) {
        this.classes = classes;
    }
}
