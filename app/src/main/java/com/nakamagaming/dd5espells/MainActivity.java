package com.nakamagaming.dd5espells;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.nakamagaming.dd5espells.adapters.SpellAdapter;
import com.nakamagaming.dd5espells.helpers.ClassType;
import com.nakamagaming.dd5espells.helpers.WebHelper;
import com.nakamagaming.dd5espells.interfaces.IFilterChangeListener;
import com.nakamagaming.dd5espells.utils.SpellUtils;
import com.nakamagaming.dd5espells.views.OptionsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements IFilterChangeListener{

    private final String mSpreadSheetID = "15ylLqPEwtpdwpKPp6HTvzlmaDogkBGe2w29b9RtxaiA";
    private ListView mSpellListView;
    private SearchView mSearchView;
    private DrawerLayout mDrawerLayout;

    private ArrayList<Spell> mFullList;
    private ArrayList<Spell> mFilteredList;
    private ArrayList<ClassType> mFilteredClasses;

    private ActionBarDrawerToggle mDrawerToggle;

    private SpellAdapter mAdapter;
    private OptionsView mOptionsView;

    /*
    TODO list
    - usability
        - class filter - make draw layout.

    - design
        - list item
            - replace colored lines with icons
        - spell details
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set nakama spash screen.
        setContentView(R.layout.activity_main);

        mFullList = new ArrayList<>();
        new GetJSONObjectTask().execute();
    }

    public void onSpellListLoaded(ArrayList<Spell> spells) {
        //here we should start the correct view

        //init vars
        mFullList = SpellUtils.sortByName(spells);
        mFilteredList = new ArrayList<>(mFullList);
        mAdapter = new SpellAdapter(mFullList, getApplicationContext());

        //set drawerlayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                mSpellListView.setEnabled(true);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSpellListView.setEnabled(false);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        catch (NullPointerException e){

        }

        mDrawerToggle.syncState();

        //set listView
        mSpellListView = (ListView) this.findViewById(R.id.spell_list);
        mSpellListView.setAdapter(mAdapter);
        mSpellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spell spell = mAdapter.getItem(position);
                Intent i = new Intent(getApplicationContext(), SpellActivity.class);
                i.putExtra(Spell.ID_SPELL, spell);

                startActivity(i);
            }
        });

        //set searchView
        mSearchView = (SearchView) this.findViewById(R.id.search_spell);
        mSearchView.setQueryHint("Spell Search...");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.setSpells(SpellUtils.filterByName(mFilteredList, query));
                mAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.setSpells(SpellUtils.filterByName(mFilteredList, newText));
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });

        //set optionsView
        mOptionsView = new OptionsView(findViewById(R.id.drawer_filter), this);
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

        switch(item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout != null) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                        mDrawerLayout.closeDrawer(Gravity.START);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.START);
                    }
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFilterChanged(ArrayList<ClassType> classList){
        mFilteredList = SpellUtils.filterByClass(mFullList, classList);
        if (mSearchView.getQuery() != null && mSearchView.getQuery().length() != 0) {
            mFilteredList = SpellUtils.filterByName(mFilteredList, mSearchView.getQuery().toString());
        }
        mAdapter.setSpells(mFilteredList);
        mAdapter.notifyDataSetChanged();
    }

    private class GetJSONObjectTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            WebHelper helper = new WebHelper();
            try {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                //if connected to internet. Download the spreadsheet Disabled for now
                /*if (networkInfo != null && networkInfo.isConnected()) {
                    return helper.getGoogleSpreadsheetByID(mSpreadSheetID);
                }*/

                //if not use the local spreadsheet.
                return helper.getSpreadsheetFromJSONResource(getResources().openRawResource(R.raw.spells));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";

        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            ArrayList<Spell> spells = new ArrayList<Spell>();

            try {
                JSONArray array = new JSONArray(result);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    Spell spell = new Spell();

                    try {
                        spell.populate(row);
                    }
                    //catch exception in single item
                    catch (JSONException e) {
                        e.printStackTrace();// todo - temp removed due to to many exceptions. - Clear out json file.
                    }
                    spells.add(spell);
                }

                onSpellListLoaded(spells);
            }
            //catch exception in json Array
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
