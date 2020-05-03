package com.sudirman.aplikasifootball.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sudirman.aplikasifootball.R;
import com.sudirman.aplikasifootball.httpclient.ApiInterface;
import com.sudirman.aplikasifootball.model.EventsItem;
import com.sudirman.aplikasifootball.model.ResponseLookupTeam;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterLastEvent extends RecyclerView.Adapter<AdapterLastEvent.ViewHolder> {

    Context context;
    List<EventsItem> items;

    ApiInterface apiInterface;

    public AdapterLastEvent(Context context,ApiInterface apiInterface) {
        this.context = context;
        this.items = new ArrayList<>();
        this.apiInterface=apiInterface;
    }

    public void setItems(List<EventsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvHome.setText(items.get(position).getStrHomeTeam());
        holder.tvAway.setText(items.get(position).getStrAwayTeam());

        holder.tvTanggal.setText(items.get(position).getStrDate());

        if(items.get(position).getIntAwayScore()==null){
            holder.tvSkor.setText("0 : 0");
        }else {
            holder.tvSkor.setText(items.get(position).getIntHomeScore() + " : " + items.get(position).getIntAwayScore());
        }
        //Image Home
        getImageTeam(items.get(position).getIdHomeTeam(),holder.ivHome);

        //Image Away
        getImageTeam(items.get(position).getIdAwayTeam(),holder.ivAway);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void getImageTeam(String idTeam,ImageView imageView){
        Call<ResponseLookupTeam> api=apiInterface.getlookupTeam(idTeam);
        api.enqueue(new Callback<ResponseLookupTeam>() {
            @Override
            public void onResponse(Call<ResponseLookupTeam> call, Response<ResponseLookupTeam> response) {
                if(response.isSuccessful()){
                    Picasso.get().load(response.body().getTeams().get(0).getStrTeamBadge())
                            .resize(100,100)
                            .into(imageView);
                }
            }

            @Override
            public void onFailure(Call<ResponseLookupTeam> call, Throwable t) {

            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivHome,ivAway;
        TextView tvHome,tvAway,tvTanggal,tvSkor;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivHome=itemView.findViewById(R.id.ivHome);
            ivAway=itemView.findViewById(R.id.ivAway);
            tvHome=itemView.findViewById(R.id.tvHome);
            tvAway=itemView.findViewById(R.id.tvAway);
            tvTanggal=itemView.findViewById(R.id.tvTanggal);
            tvSkor=itemView.findViewById(R.id.tvSkor);

            this.itemView=itemView;
        }
    }
}