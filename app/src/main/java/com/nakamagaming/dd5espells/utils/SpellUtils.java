package com.nakamagaming.dd5espells.utils;

import com.nakamagaming.dd5espells.Spell;
import com.nakamagaming.dd5espells.helpers.ClassType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Steffan on 08-May-15.
 */
public class SpellUtils {
    public static ArrayList<Spell> sortByName(ArrayList<Spell> list) {
        Collections.sort(list, new Comparator<Spell>() {
            @Override
            public int compare(Spell s1, Spell s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
        return list;
    }

    public static ArrayList<Spell> sortByLevel(ArrayList<Spell> list) {
        Collections.sort(list, new Comparator<Spell>() {
            @Override
            public int compare(Spell s1, Spell s2) {
                return String.valueOf(s1.getLevel()).compareToIgnoreCase(String.valueOf(s2.getLevel()));
            }
        });
        return list;
    }

    public static ArrayList<Spell> filterByName(ArrayList<Spell> list, String text) {
        ArrayList<Spell> result = new ArrayList<>();

        //return full list if we're not filtering anyway.
        if (text == null || text.isEmpty())
            return list;
        text=text.toLowerCase();
        for (Spell spell : list) {
            //name is case sensitive
            String name = spell.getName().toLowerCase();
            if (name.contains(text)) {
                result.add(spell);
            }
        }
        return result;
    }

    public static ArrayList<Spell> filterByClass(ArrayList<Spell> list, ArrayList<ClassType> classTypes) {
        ArrayList<Spell> result = new ArrayList<>();

        for (Spell spell : list) {
            for(ClassType classType : spell.getClassTypes()){
                if(classTypes.contains(classType))
                    result.add(spell);
                    break;
            }
        }

        return result;
    }
}
