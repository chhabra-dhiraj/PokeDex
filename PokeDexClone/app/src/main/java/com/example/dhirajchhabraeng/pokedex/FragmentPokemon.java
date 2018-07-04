package com.example.dhirajchhabraeng.pokedex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentPokemon extends Fragment {

    private ImageView pokemonImage;

    public static FragmentPokemon newInstance(Pokemon pokemon) {
        Bundle args = new Bundle();
        args.putParcelable("pokemon", pokemon);

        FragmentPokemon fragment = new FragmentPokemon();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);

        OkHttpClient okHttpClient = new OkHttpClient();

        pokemonImage = view.findViewById(R.id.pokemon_image);

        Request request = new Request.Builder().url(getArguments().getString("apiUrl")).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             String json = response.body().string();
                Gson gson = new Gson();
                final Pokemon pokemon = gson.fromJson(json, Pokemon.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(pokemon.getPokemonImageUrl()).into(pokemonImage);
                    }
                });
            }
        });
        
        return view;
    }
}
