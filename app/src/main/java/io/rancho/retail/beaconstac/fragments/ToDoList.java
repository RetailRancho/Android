package io.rancho.retail.beaconstac.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.rancho.retail.beaconstac.R;
import io.rancho.retail.beaconstac.activities.MainActivity;
import io.rancho.retail.beaconstac.adapters.RecyclerAdapterToDo;
import io.rancho.retail.beaconstac.cards.ListItem;

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
        arrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,itemList);
        listView.setAdapter(arrayAdapter);

        item = editText.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item!=null)
                {
                    itemList.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}
