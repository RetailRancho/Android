package io.rancho.retail.beaconstac.cards;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.rancho.retail.beaconstac.R;

/**
 * Created by Chinmay on 1/31/16.
 */
public class ListCard extends RecyclerView.ViewHolder{
    public TextView cardTextView;
    public ListCard(View itemView) {
        super(itemView);
        this.cardTextView = (TextView)itemView.findViewById(R.id.list_textview);
    }
}
