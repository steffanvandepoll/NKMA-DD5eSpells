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
            Spell spell = extras.getParcelable(Spell.ID_SPELL);

            TextView nameTv = (TextView) this.findViewById(R.id.spell_name);
            TextView levelTv = (TextView) this.findViewById(R.id.spell_level);
            TextView descriptionTv = (TextView) this.findViewById(R.id.spell_description);

            nameTv.setText(spell.getName());
            levelTv.setText(String.valueOf(spell.getLevel()));
            descriptionTv.setText(spell.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
}
