package com.nakamagaming.dd5espells;

import android.os.Parcel;
import android.os.Parcelable;

import com.nakamagaming.dd5espells.helpers.ClassType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Steffan on 03-May-15.
 */
public class Spell implements Parcelable {

    public static final String ID_SPELL = "spell";
    public static final String TYPE_NAME = "name";
    public static final String TYPE_LEVEL = "level";
    public static final String TYPE_SCHOOL = "school";
    public static final String TYPE_RITUAL = "ritual";
    public static final String TYPE_CASTING_TIME = "castingtime";
    public static final String TYPE_RANGE = "range";
    public static final String TYPE_COMPONENTS = "components";
    public static final String TYPE_COMP_DESCRIPTION = "compdescr";
    public static final String TYPE_DURATION = "duration";
    public static final String TYPE_PAGE = "page";
    public static final String TYPE_DESCRIPTION = "description";

    private String mName;
    private String mDescription;
    private String mSchool;
    private String mCastingTime;
    private String mRange;
    private String mComponents;
    private String mComponentDescription;
    private String mDuration;

    private int mPage;
    private int mLevel;

    private Boolean mRitual;

    private boolean mUsableByBard = false;
    private boolean mUsableByCleric = false;
    private boolean mUsableByDruid = false;
    private boolean mUsableByPaladin = false;
    private boolean mUsableByWarlock = false;
    private boolean mUsableBySorcerer = false;
    private boolean mUsableByWizard = false;
    private boolean mUsableByRanger = false;

    public Spell() {

    }

    public void populate(JSONObject object) throws JSONException {
        //populate spell properties
        this.setName(object.optString(Spell.TYPE_NAME));
        this.setLevel(object.optInt(Spell.TYPE_LEVEL));
        this.setSchool(object.optString(Spell.TYPE_SCHOOL));
        this.setRitual(object.optBoolean(Spell.TYPE_RITUAL));
        this.setRange(object.optString(Spell.TYPE_RANGE));
        this.setComponents(object.optString(Spell.TYPE_COMPONENTS));
        this.setComponentDescription(object.optString(Spell.TYPE_COMP_DESCRIPTION));
        this.setCastingTime(object.optString(Spell.TYPE_CASTING_TIME));
        this.setDuration(object.optString(Spell.TYPE_DURATION));
        this.setPage(object.optInt(Spell.TYPE_PAGE));
        this.setDescription(object.optString(Spell.TYPE_DESCRIPTION));

        //populate available classtypes. todo - create a more generic way of handling this.
        this.setUsableByBard(object.optBoolean(ClassType.BARD.toString()));
        this.setUsableByCleric(object.optBoolean(ClassType.CLERIC.toString()));
        this.setUsableByDruid(object.optBoolean(ClassType.DRUID.toString()));
        this.setUsableByPaladin(object.optBoolean(ClassType.PALADIN.toString()));
        this.setUsableByRanger(object.optBoolean(ClassType.RANGER.toString()));
        this.setUsableBySorcerer(object.optBoolean(ClassType.SORCERER.toString()));
        this.setUsableByWarlock(object.optBoolean(ClassType.WARLOCK.toString()));
        this.setUsableByWizard(object.optBoolean(ClassType.WIZARD.toString()));
    }

    //make object parcable *note order matters.
    public Spell(Parcel in) {
        String[] data = new String[19];
        in.readStringArray(data);

        mName = data[0];
        mDescription = data[1];
        mSchool = data[2];
        mCastingTime = data[3];
        mRange = data[4];
        mComponents = data[5];
        mComponentDescription = data[6];
        mDuration = data[7];

        mPage = Integer.parseInt(data[8]);
        mLevel = Integer.parseInt(data[9]);

        mRitual = Boolean.parseBoolean(data[10]);

        mUsableByBard = Boolean.parseBoolean(data[11]);
        mUsableByCleric = Boolean.parseBoolean(data[12]);
        mUsableByDruid = Boolean.parseBoolean(data[13]);
        mUsableByPaladin = Boolean.parseBoolean(data[14]);
        mUsableByWarlock = Boolean.parseBoolean(data[15]);
        mUsableBySorcerer = Boolean.parseBoolean(data[16]);
        mUsableByWizard = Boolean.parseBoolean(data[17]);
        mUsableByRanger = Boolean.parseBoolean(data[18]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //write data to parcel - *note order matters
        dest.writeStringArray(new String[]{
                this.mName,
                this.mDescription,
                this.mSchool,
                this.mCastingTime,
                this.mRange,
                this.mComponents,
                this.mComponentDescription,
                this.mDuration,
                String.valueOf(this.mPage),
                String.valueOf(this.mLevel),
                String.valueOf(this.mRitual),
                String.valueOf(this.mUsableByBard),
                String.valueOf(this.mUsableByCleric),
                String.valueOf(this.mUsableByDruid),
                String.valueOf(this.mUsableByPaladin),
                String.valueOf(this.mUsableByWarlock),
                String.valueOf(this.mUsableBySorcerer),
                String.valueOf(this.mUsableByWizard),
                String.valueOf(this.mUsableByRanger)
        });
    }

    public static final Parcelable.Creator<Spell> CREATOR = new Parcelable.Creator<Spell>() {
        @Override
        public Spell createFromParcel(Parcel source) {
            return new Spell(source);
        }

        @Override
        public Spell[] newArray(int size) {
            return new Spell[size];
        }
    };

    /* getters and setters */
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getSchool() {
        return mSchool;
    }

    public void setSchool(String school) {
        this.mSchool = school;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        this.mLevel = level;
    }

    public Boolean getRitual() {
        return mRitual;
    }

    public void setRitual(Boolean ritual) {
        this.mRitual = ritual;
    }

    public String getCastingTime() {
        return mCastingTime;
    }

    public void setCastingTime(String castingTime) {
        this.mCastingTime = castingTime;
    }

    public String getRange() {
        return mRange;
    }

    public void setRange(String range) {
        this.mRange = range;
    }

    public String getComponents() {
        return mComponents;
    }

    public void setComponents(String components) {
        this.mComponents = components;
    }

    public String getComponentDescription() {
        return mComponentDescription;
    }

    public void setComponentDescription(String momponentDescription) {
        this.mComponentDescription = momponentDescription;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        this.mPage = page;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    public boolean isUsableByBard() {
        return mUsableByBard;
    }

    public void setUsableByBard(boolean usableByBard) {
        this.mUsableByBard = usableByBard;
    }

    public boolean isUsableByCleric() {
        return mUsableByCleric;
    }

    public void setUsableByCleric(boolean usableByCleric) {
        this.mUsableByCleric = usableByCleric;
    }

    public boolean isUsableByDruid() {
        return mUsableByDruid;
    }

    public void setUsableByDruid(boolean usableByDruid) {
        this.mUsableByDruid = usableByDruid;
    }

    public boolean isUsableByPaladin() {
        return mUsableByPaladin;
    }

    public void setUsableByPaladin(boolean usableByPaldin) {
        this.mUsableByPaladin = usableByPaldin;
    }

    public boolean isUsableByWarlock() {
        return mUsableByWarlock;
    }

    public void setUsableByWarlock(boolean usableByWarlock) {
        this.mUsableByWarlock = usableByWarlock;
    }

    public boolean isUsableBySorcerer() {
        return mUsableBySorcerer;
    }

    public void setUsableBySorcerer(boolean usableBySorcerer) {
        this.mUsableBySorcerer = usableBySorcerer;
    }

    public boolean isUsableByWizard() {
        return mUsableByWizard;
    }

    public void setUsableByWizard(boolean usableByWizard) {
        this.mUsableByWizard = usableByWizard;
    }

    public boolean isUsableByRanger() {
        return mUsableByRanger;
    }

    public void setUsableByRanger(boolean usableByRanger) {
        this.mUsableByRanger = usableByRanger;
    }
}
