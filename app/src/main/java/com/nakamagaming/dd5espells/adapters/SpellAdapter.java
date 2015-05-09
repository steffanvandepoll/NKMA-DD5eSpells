package com.nakamagaming.dd5espells.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nakamagaming.dd5espells.R;
import com.nakamagaming.dd5espells.Spell;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Steffan on 03-May-15.
 */
public class SpellAdapter extends BaseAdapter {
    private ArrayList<Spell> mSpells;
    private int mResource;
    private LayoutInflater mInflater;

    public SpellAdapter(ArrayList<Spell> spells, Context context){
        mResource =  R.layout.spell_list_item;
        mSpells = spells;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mSpells.size();
    }

    @Override
    public Spell getItem(int position) {
        return mSpells.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        vi = mInflater.inflate(mResource, null);
        Spell spell = mSpells.get(position);

        TextView nameView = (TextView)vi.findViewById(R.id.spell_name);
        nameView.setText(spell.getName());

        TextView levelView = (TextView)vi.findViewById(R.id.spell_level);
        levelView.setText("lvl :" + String.valueOf(spell.getLevel()));

        //set icons
        vi.findViewById(R.id.class_icon_bard).setVisibility(spell.isUsableByBard() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_cleric).setVisibility(spell.isUsableByCleric() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_druid).setVisibility(spell.isUsableByDruid() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_paladin).setVisibility(spell.isUsableByPaladin() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_ranger).setVisibility(spell.isUsableByRanger() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_sorcerer).setVisibility(spell.isUsableBySorcerer() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_warlock).setVisibility(spell.isUsableByWarlock() ? View.VISIBLE : View.GONE);
        vi.findViewById(R.id.class_icon_wizard).setVisibility(spell.isUsableByWizard() ? View.VISIBLE : View.GONE);

        return vi;
    }
}
