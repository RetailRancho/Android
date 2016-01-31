package io.rancho.retail.beaconstac.adapters;

import io.rancho.retail.beaconstac.cards.Offers;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Chinmay on 1/31/16.
 */
public interface MyAPI {

    @GET("/notifications/{id}")
    void getOffers(@Path("id") String id, Callback<Offers> offersCallback);
}
