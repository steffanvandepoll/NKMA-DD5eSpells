package com.nakamagaming.dd5espells.helpers;

/**
 * Created by Steffan on 7-5-2015.
 */
public enum ClassType {
    BARD("bard"),
    CLERIC("cleric"),
    DRUID("druid"),
    PALADIN("paladin"),
    RANGER("ranger"),
    SORCERER("sorcerer"),
    WARLOCK("warlock"),
    WIZARD("wizard")
    ;
    private final String classType;

    ClassType(final String classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return classType;
    }
}
