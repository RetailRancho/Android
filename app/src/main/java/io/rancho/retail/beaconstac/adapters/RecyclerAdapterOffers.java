package io.rancho.retail.beaconstac.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rancho.retail.beaconstac.R;
import io.rancho.retail.beaconstac.cards.MyCard;
import io.rancho.retail.beaconstac.cards.Offers;

public class RecyclerAdapterOffers extends RecyclerView.Adapter<MyCard> {
    private Context context;
    private List<String> offers_array;

    public RecyclerAdapterOffers(Context context,List<String> offers_array)
    {
        this.context = context;
        this.offers_array = offers_array;
    }

    @Override
    public MyCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        MyCard myCard = new MyCard(v);
        return myCard;
    }

    @Override
    public void onBindViewHolder(MyCard holder, int position) {
        String offer = offers_array.get(position);
        holder.cardTextView.setText(offer);
    }

    @Override
    public int getItemCount() {
        return (null != offers_array ? offers_array.size() : 0);
    }
}
