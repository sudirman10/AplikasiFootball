package com.sudirman.aplikasifootball.httpclient;

import com.sudirman.aplikasifootball.model.ResponseAllLeague;
import com.sudirman.aplikasifootball.model.ResponseDetailLeague;
import com.sudirman.aplikasifootball.model.ResponseLastEvent;
import com.sudirman.aplikasifootball.model.ResponseLookupTeam;
import com.sudirman.aplikasifootball.model.ResponseNextEvent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search_all_leagues.php?s=Soccer")
    Call<ResponseAllLeague> getAllLeague();

    @GET("lookupleague.php")
    Call<ResponseDetailLeague> getDetailLeague(@Query("id") String id);

    @GET("eventsnextleague.php")
    Call<ResponseNextEvent> getNextEventByLeague(@Query("id") String id);

    @GET("eventspastleague.php")
    Call<ResponseLastEvent> getlastEventByLeague(@Query("id") String id);

    @GET("lookupteam.php")
    Call<ResponseLookupTeam> getlookupTeam(@Query("id") String id);

}
