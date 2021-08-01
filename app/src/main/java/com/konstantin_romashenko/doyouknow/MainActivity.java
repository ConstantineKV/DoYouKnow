package com.konstantin_romashenko.doyouknow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.konstantin_romashenko.doyouknow.adapter.*;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements iNavItemSelectedListener
{
    private ActionBar actionBar;
    private RecOnClickListener recOnlickListener;
    private DataAdapter adapter;
    private List<ListItem> listData;
    private RecyclerView rcView;
    private String category = "";
    public SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMenu();
        setRecOnlickListener();
        init();
        actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void setupMenu()
    {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null)
        {
            mMenuFragment = new MenuListFragment();
            mMenuFragment.setNavItemSelectedListener(this);
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    @Override
    public void onNavItemSelectedListener(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.id_favorite:

                break;
            case R.id.id_planet:
                updateMainList(getResources().getStringArray(R.array.planets_arr), "planets");
                break;
            case R.id.id_travel:
                updateMainList(getResources().getStringArray(R.array.travels_arr), "travel");
                break;
            case R.id.id_universal:
                updateMainList(getResources().getStringArray(R.array.universe_arr), "universal");
                break;
            case R.id.id_stars:
                updateMainList(getResources().getStringArray(R.array.stars_arr), "stars");
                break;
            case R.id.id_smart:
                updateMainList(getResources().getStringArray(R.array.smart_words), "smart");
                break;
            case R.id.id_umor:
                updateMainList(getResources().getStringArray(R.array.Funny_words), "umor");
                break;
            case R.id.id_ufo:
                updateMainList(getResources().getStringArray(R.array.UFO_arr), "UFO");
                break;
            case R.id.id_spirits:
                updateMainList(getResources().getStringArray(R.array.spirits_arr), "spirits");
                break;

        }
    }

    private void updateMainList(String[] array, String category)
    {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        String tempCat = pref.getString(category, "none");
        if(tempCat != null)
        {
            if(tempCat == "none")
            {
                for(int i = 0; i < array.length; i++)
                {
                    stringBuilder.append("0");
                }

                Log.i("MyLog",category + " " + stringBuilder.toString());
                saveString(stringBuilder.toString());
            }
            else
            {

            }


        }

        List<ListItem> list = new ArrayList<>();
        for(int i=0; i < array.length; i++)
        {
            ListItem item = new ListItem();
            item.setText(array[i]);
            item.setFavorite(false);
            list.add(item);
        }

        adapter.updateList(list);
    }

    public void init()
    {
        pref = getSharedPreferences("CAT", MODE_PRIVATE);
        rcView = findViewById(R.id.rcView);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();
        String[] planetsArray = getResources().getStringArray(R.array.planets_arr);
        adapter = new DataAdapter(this,recOnlickListener,listData);

        updateMainList(planetsArray, "planets");
        rcView.setAdapter(adapter);

    }

    private void setRecOnlickListener()
    {
        recOnlickListener = new RecOnClickListener()
        {
            @Override
            public void onItemClicked(int pos)
            {
                String tempCat = pref.getString(category, "none");
                if(tempCat != null)
                {

                    if(tempCat.charAt(pos) == '0')
                    {
                        saveString(replaceCharAtPosition(pos, '1', tempCat));
                    }
                    if(tempCat.charAt(pos) == '1')
                    {
                        saveString(replaceCharAtPosition(pos, '0', tempCat));
                    }
                }
                Log.i("MyLog", "Saved data fav: " + pref.getString(category, "none") + " pos = " + pos);
            }
        };
    }

    public String replaceCharAtPosition(int position, char ch, String st)
    {
        char[] charArr = st.toCharArray();
        charArr[position] = ch;
        return new String(charArr);
    }

    private void saveString(String stToSave)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(category, stToSave);
        editor.apply();
    }
}