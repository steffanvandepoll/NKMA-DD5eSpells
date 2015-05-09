package com.nakamagaming.dd5espells;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SpellActivity extends ActionBarActivity {

    Spell mSpell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the spell content view
        setContentView(R.layout.activity_spell);

        Bundle extras = getIntent().getExtras();
        extras.setClassLoader(Spell.class.getClassLoader());

        if (extras != null) {
            mSpell = extras.getParcelable(Spell.ID_SPELL);

            //get all textviews from the page.
            TextView nameTv         = (TextView)this.findViewById(R.id.spell_name);
            TextView pageTv         = (TextView)this.findViewById(R.id.spell_page);
            TextView levelTv        = (TextView)this.findViewById(R.id.spell_level);
            TextView descriptionTv  = (TextView)this.findViewById(R.id.spell_description);
            TextView castingTimeTv  = (TextView)this.findViewById(R.id.spell_castingTime);
            TextView rangeTv        = (TextView)this.findViewById(R.id.spell_range);
            TextView componentsTv   = (TextView)this.findViewById(R.id.spell_components);
            TextView durationTv     = (TextView)this.findViewById(R.id.spell_duration);

            nameTv.setText(mSpell.getName());
            pageTv.setText(String.format("PHB: %d", mSpell.getPage()));
            levelTv.setText(formatSpellLevel(mSpell));

            castingTimeTv.setText(mSpell.getCastingTime());
            rangeTv.setText(mSpell.getRange());

            if(mSpell.getComponentDescription().length() > 0)
                componentsTv.setText(String.format("%s (%s)", mSpell.getComponents(), mSpell.getComponentDescription()));
            else
                componentsTv.setText(mSpell.getComponents());

            durationTv.setText(mSpell.getDuration());

            descriptionTv.setText(mSpell.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spell, menu);

        if(mSpell != null) {
            menu.getItem(0).setVisible(mSpell.isUsableByBard());
            menu.getItem(1).setVisible(mSpell.isUsableByCleric());
            menu.getItem(2).setVisible(mSpell.isUsableByDruid());
            menu.getItem(3).setVisible(mSpell.isUsableByPaladin());
            menu.getItem(4).setVisible(mSpell.isUsableByRanger());
            menu.getItem(5).setVisible(mSpell.isUsableByWizard());
            menu.getItem(6).setVisible(mSpell.isUsableBySorcerer());
            menu.getItem(7).setVisible(mSpell.isUsableByWarlock());
        }

        mSpell.getName();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private String formatSpellLevel(Spell spell){
        String returner;
        String appendix;

        if(spell.getLevel() > 0) {
            switch (spell.getLevel()) {
                case 1:
                    appendix = "st-level";
                    break;
                case 2:
                    appendix = "nd-level";
                    break;
                case 3:
                    appendix = "rd-level";
                    break;
                default:
                    appendix = "th-level";
                    break;
            }
            returner = String.format("%d%s %s", spell.getLevel(), appendix, spell.getSchool());
        } else {
            returner = String.format("%s cantrip", spell.getSchool());
        }

        if(spell.getRitual())
            returner += " (Ritual)";

        return returner;
    }
}
