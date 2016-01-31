package io.rancho.retail.beaconstac.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.rancho.retail.beaconstac.R;
import io.rancho.retail.beaconstac.cards.ListCard;
import io.rancho.retail.beaconstac.cards.ListItem;
import io.rancho.retail.beaconstac.cards.MyCard;

/**
 * Created by Chinmay on 1/31/16.
 */
public class RecyclerAdapterToDo extends RecyclerView.Adapter<ListCard> {

    private List<ListItem> itemList;
    private Context context;
    public RecyclerAdapterToDo(Context context,List<ListItem> itemList)
    {
        this.context = context;
        this.itemList = itemList;
    }
    @Override
    public ListCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, null);
        ListCard myCard = new ListCard(v);
        return myCard;
    }

    @Override
    public void onBindViewHolder(ListCard holder, int position) {
        ListItem item = itemList.get(position);
        holder.cardTextView.setText(item.getItem_name());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
