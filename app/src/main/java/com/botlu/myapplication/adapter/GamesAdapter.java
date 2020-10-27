package com.botlu.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.botlu.myapplication.R;
import com.botlu.myapplication.model.GamesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {
    Context context;
    List<GamesModel> gamesModels;
    GamesModel gamesModel;
    public class GamesViewHolder extends RecyclerView.ViewHolder {

        TextView teamhome;
        TextView teamAway;
        TextView dateTime;
        TextView scoreAway;
        TextView scoreHome;
        ImageView homeLogo;
        ImageView AwayLogo;
        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);
            teamhome = itemView.findViewById(R.id.games_home_name);
            teamAway = itemView.findViewById(R.id.games_away_name);
            scoreAway = itemView.findViewById(R.id.games_away_score);
            dateTime = itemView.findViewById(R.id.games_date);
            scoreHome = itemView.findViewById(R.id.games_home_score);
            homeLogo = itemView.findViewById(R.id.games_home_logo);
            AwayLogo = itemView.findViewById(R.id.games_away_logo);
        }
    }

    public GamesAdapter(Context context, List<GamesModel> gamesModels){
        this.context = context;
        this.gamesModels = gamesModels;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list,parent,false);
        return new GamesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        gamesModel = gamesModels.get(position);
        holder.teamhome.setText(gamesModel.getTeams().getHome().getName());
        holder.teamAway.setText(gamesModel.getTeams().getAway().getName());
        holder.scoreAway.setText(nullable(gamesModel.getScores().getAway()));
        holder.scoreHome.setText(nullable(gamesModel.getScores().getHome()));
        holder.dateTime.setText(getDate(gamesModel.getDate()) + " " + convertedTime(gamesModel.getTime()));
        Picasso.get().load(gamesModel.getTeams().getHome().getLogo()).into(holder.homeLogo);
        Picasso.get().load(gamesModel.getTeams().getAway().getLogo()).into(holder.AwayLogo);
    }
    @Override
    public int getItemCount() {
        return gamesModels.size();
    }

    public String nullable(String string){
        return string == null ? "-" : string;
    }
    public String nullable(Integer number){
        if(number == null){
            return "-";
        }else {
            return number.toString();
        }
    }

    public String getDate(String date){
        return date.substring(0,10);
    }
    public String convertedTime(String time){
        String concatTime = time.substring(time.length()-3);
        int ntime = Integer.parseInt(time.substring(0,2));
        if(ntime > 12){
            return (ntime - 12) + concatTime + "PM";
        }else if(ntime == 12){
            return ntime + concatTime + "NN";
        }else if(ntime == 00){
            return ntime + concatTime + "MN";
        }else{
            return ntime + concatTime + "AM";
        }

    }



}
