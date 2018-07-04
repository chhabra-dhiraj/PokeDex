package com.example.dhirajchhabraeng.pokedex;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Properties> propertiesList = new ArrayList<>();
    ArrayList<NextProperties> nextPropertiesList = new ArrayList<>();
    GenreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NextProperties nextProperties = new NextProperties("speed", null);

        propertiesList.add(new Properties("Weight", null));
        propertiesList.add(new Properties("Rank", null));
        propertiesList.add(new Properties("Base Experience", null));
        propertiesList.add(new Properties("abilities", nextPropertiesList));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new GenreAdapter(propertiesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    class Properties extends ExpandableGroup<NextProperties> {

        public Properties(String title, List<NextProperties> items) {
            super(title, items);
        }

    }

    class PropertiesViewHolder extends GroupViewHolder {

        private TextView propertiesTitle;

        public PropertiesViewHolder(View itemView) {
            super(itemView);
            propertiesTitle = itemView.findViewById(R.id.properties_title);
        }

        public void setPropertiesTitle(Properties properties) {
            propertiesTitle.setText(properties.getTitle());
        }

    }

    class NextPropertiesViewHolder extends ChildViewHolder {

        private TextView artistName;

        public NextPropertiesViewHolder(View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.internal_property);
        }

        public void onBind(NextProperties nextProperty) {
            artistName.setText(nextProperty.getTitle());
        }
    }

    class GenreAdapter extends ExpandableRecyclerViewAdapter<PropertiesViewHolder, NextPropertiesViewHolder> {

        public GenreAdapter(List<Properties> propertiesList) {
            super(propertiesList);
        }

        @Override
        public PropertiesViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new PropertiesViewHolder(view);
        }

        @Override
        public NextPropertiesViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.next_list_item, parent, false);
            return new NextPropertiesViewHolder(view);
        }

        @Override
        public void onBindChildViewHolder(NextPropertiesViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
            NextProperties nextProperty = ((Properties) group).getItems().get(childIndex);
            holder.onBind(nextProperty);
        }

        @Override
        public void onBindGroupViewHolder(PropertiesViewHolder holder, int flatPosition, ExpandableGroup group) {
            holder.setPropertiesTitle((Properties) group);
        }
    }

}


