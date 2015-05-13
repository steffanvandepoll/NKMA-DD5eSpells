package com.nakamagaming.dd5espells.helpers;

/**
 * Created by Steffan on 7-5-2015.
 */
public enum ClassType {
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard")
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
