package io.rancho.retail.beaconstac.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import io.rancho.retail.beaconstac.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoList extends Fragment {

    EditText editText;
    Button button;
    ListView listView;
    ArrayAdapter<String>arrayAdapter;
    ArrayList<String> itemList = new ArrayList<>() ;
    String item;
    View view;
    SharedPreferences sharedPreferences;
    public ToDoList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        editText = (EditText)view.findViewById(R.id.item_edittext);
        button = (Button)view.findViewById(R.id.item_addbutton);

        listView = (ListView)view.findViewById(R.id.itemList);
        arrayAdapter = new ArrayAdapter<String>(view.getContext(),R.layout.card_list,itemList);
        listView.setAdapter(arrayAdapter);

        itemList.add("Jeans");
        itemList.add("Bag");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = editText.getText().toString();
            if(item != null)
            {
                itemList.add(item);
                Log.d("ABC","String is "+item);
                arrayAdapter.notifyDataSetChanged();
            }
            }
        });

        return view;
    }
}
