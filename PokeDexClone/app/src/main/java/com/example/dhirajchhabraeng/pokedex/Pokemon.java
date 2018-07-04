package com.example.dhirajchhabraeng.pokedex;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Pokemon implements Parcelable{
    @SerializedName("front_default")
    private String pokemonImageUrl = "";

    public Pokemon(String pokemonImageUrl) {
        this.pokemonImageUrl = pokemonImageUrl;
    }

    protected Pokemon(Parcel in) {
        pokemonImageUrl = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getPokemonImageUrl() {
        return pokemonImageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pokemonImageUrl);
    }
}
