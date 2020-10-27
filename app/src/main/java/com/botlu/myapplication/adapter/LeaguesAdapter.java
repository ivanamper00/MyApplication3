package com.botlu.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.botlu.myapplication.R;
import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.controller.activity.MainActivity;
import com.botlu.myapplication.model.LeagueModel;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder> {
    Context context;
    List<LeagueModel> leagueModels;
    LeagueModel leagueModel;
    GlobalController globalController;
    public class LeaguesViewHolder extends RecyclerView.ViewHolder {

        TextView leagueName;
        TextView leagueid;
        ImageView leagueLogo;
        ImageView leagueCountry;
        public LeaguesViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueName = itemView.findViewById(R.id.league_name);
            leagueid = itemView.findViewById(R.id.league_id);
            leagueLogo = itemView.findViewById(R.id.league_logo);
            leagueCountry = itemView.findViewById(R.id.league_flag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,leagueid.getText(), Toast.LENGTH_SHORT).show();
                    globalController.startLoading();
                    globalController.clearContents();
                    globalController.setDefaultLeague(leagueid.getText().toString());
                    globalController.saveAllQueries();
                    globalController.loop();

                }
            });

        }
    }
    public LeaguesAdapter(Context context,List<LeagueModel> leagueModels){
        this.context = context;
        this.leagueModels = leagueModels;
        globalController = new GlobalController(this.context);
    }
    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_list,parent,false);
        return new LeaguesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        leagueModel = leagueModels.get(position);
        holder.leagueid.setText(leagueModel.getId().toString());
        holder.leagueName.setText(leagueModel.getName());
        Picasso.get().load(leagueModel.getLogo()).into(holder.leagueLogo);
        GlideToVectorYou.justLoadImage((Activity) context, Uri.parse(leagueModel.getCountry().getFlag()),holder.leagueCountry);
    }

    @Override
    public int getItemCount() {
        return leagueModels.size();
    }


}
