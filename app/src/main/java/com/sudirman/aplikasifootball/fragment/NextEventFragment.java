package com.sudirman.aplikasifootball.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudirman.aplikasifootball.R;
import com.sudirman.aplikasifootball.adapter.AdapterNextEvent;
import com.sudirman.aplikasifootball.httpclient.ApiInterface;
import com.sudirman.aplikasifootball.httpclient.RetrofitClient;
import com.sudirman.aplikasifootball.model.ResponseNextEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NextEventFragment extends Fragment {

    String idLeague;

    @BindView(R.id.rvNextEvent)
    RecyclerView rvNextEvent;

    AdapterNextEvent adapter;

    ApiInterface apiInterface;

    public NextEventFragment() {
        // Required empty public constructor
    }

    public static NextEventFragment newInstance(String idLeague) {
        NextEventFragment fragment = new NextEventFragment();
        Bundle args = new Bundle();
        args.putString("id", idLeague);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idLeague = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_next_event, container, false);

        ButterKnife.bind(this,view);

        apiInterface= RetrofitClient.getRetrofitClient().create(ApiInterface.class);

        adapter=new AdapterNextEvent(getContext(),apiInterface);
        rvNextEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNextEvent.setAdapter(adapter);
        rvNextEvent.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        nextevent();

        return view;
    }

    public void nextevent(){
        Call<ResponseNextEvent> api=apiInterface.getNextEventByLeague(idLeague);

        api.enqueue(new Callback<ResponseNextEvent>() {
            @Override
            public void onResponse(Call<ResponseNextEvent> call, Response<ResponseNextEvent> response) {
                if (response.isSuccessful()){
                    adapter.setItems(response.body().getEvents());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseNextEvent> call, Throwable t) {

            }
        });
    }
}
