package io.rancho.retail.beaconstac.cards;


import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.rancho.retail.beaconstac.R;


/**
 * Created by Chinmay on 9/27/15.
 */
public class MyCard extends RecyclerView.ViewHolder {

    public TextView cardTextView;
    public ImageView cardImageView;
    public MyCard(View itemView) {
        super(itemView);
        this.cardTextView = (TextView)itemView.findViewById(R.id.card_text_view);
        this.cardImageView = (ImageView)itemView.findViewById(R.id.card_image_view);
    }
}