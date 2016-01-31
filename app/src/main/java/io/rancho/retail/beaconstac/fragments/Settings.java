package io.rancho.retail.beaconstac.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.rancho.retail.beaconstac.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        listView = (ListView)view.findViewById(R.id.settings_list);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }
}
