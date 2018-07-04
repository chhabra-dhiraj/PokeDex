package com.example.dhirajchhabraeng.pokedex;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pokemon> pokemonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonList.add(new Pokemon("http://pokeapi.co/api/v2/pokemon/300/"));

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        FragmentPokemon fragmentPokemon;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentPokemon = FragmentPokemon.newInstance(pokemonList.get(position));
        }

        @Override
        public int getCount() {
            return pokemonList.size();
        }
    }
}
