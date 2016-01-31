package io.rancho.retail.beaconstac.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import io.rancho.retail.beaconstac.R;
import io.rancho.retail.beaconstac.adapters.MyAPI;
import io.rancho.retail.beaconstac.adapters.RecyclerAdapterOffers;
import io.rancho.retail.beaconstac.cards.Offers;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Messages extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapterOffers recyclerAdapterOffers;
    public static final String BASE_URL = "http://52.26.104.171";
    public Messages() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pebble, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL).build();
        MyAPI myAPI = restAdapter.create(MyAPI.class);
        myAPI.getOffers("1", new Callback<Offers>() {
            @Override
            public void success(Offers offers, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ABC",error.getMessage());
            }
        });
        return view;
    }

}
