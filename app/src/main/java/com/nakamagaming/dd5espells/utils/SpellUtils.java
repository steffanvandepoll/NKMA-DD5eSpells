package com.nakamagaming.dd5espells.utils;

import com.nakamagaming.dd5espells.Spell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Steffan on 08-May-15.
 */
public class SpellUtils {
    public static ArrayList<Spell> sortByName(ArrayList<Spell> list){
        Collections.sort(list, new Comparator<Spell>() {
            @Override
            public int compare(Spell s1, Spell s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
        return list;
    }
}
