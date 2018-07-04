package com.example.dhirajchhabraeng.pokedex;

import android.os.Parcel;
import android.os.Parcelable;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class NextProperties implements Parcelable {
    private String title;
    private ArrayList<String> items;

    public NextProperties(String title, ArrayList<String> items) {
        this.title = title;
        this.items = items;
    }

    protected NextProperties(Parcel in) {
        title = in.readString();
        items = in.createStringArrayList();
    }

    public static final Creator<NextProperties> CREATOR = new Creator<NextProperties>() {
        @Override
        public NextProperties createFromParcel(Parcel in) {
            return new NextProperties(in);
        }

        @Override
        public NextProperties[] newArray(int size) {
            return new NextProperties[size];
        }
    };

    public NextProperties() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(items);
    }
}
