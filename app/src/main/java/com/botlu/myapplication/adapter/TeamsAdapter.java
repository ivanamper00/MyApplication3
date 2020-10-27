package com.botlu.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.botlu.myapplication.R;
import com.botlu.myapplication.database.HockeyEyPiAy;
import com.botlu.myapplication.model.GamesModel;
import com.botlu.myapplication.model.RequestResponseGamesModel;
import com.botlu.myapplication.model.RequestResponseTeamDetailsModel;
import com.botlu.myapplication.model.RequestResponseTeamModel;
import com.botlu.myapplication.model.TeamsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {
    Context context;
    List<TeamsModel> teamsModels;
    TeamsModel teamsModel;
    Dialog teamDialog;
    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamLogo;
        TextView teamId;
        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            teamId = itemView.findViewById(R.id.team_id);
            teamLogo = itemView.findViewById(R.id.team_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTeam(teamId.getText().toString());
                    teamDialog = new Dialog(context);
                    teamDialog.setContentView(R.layout.team_detail_list);
                    teamDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    teamDialog.show();
//                    Toast.makeText(context, teamId.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public TeamsAdapter(Context context, List<TeamsModel> teamsModels){
        this.context = context;
        this.teamsModels = teamsModels;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list, parent, false);
        return new TeamsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        teamsModel = teamsModels.get(position);
        holder.teamId.setText(teamsModel.getId().toString());
        holder.teamName.setText(teamsModel.getName());
        Picasso.get().load(teamsModel.getLogo()).into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return teamsModels.size();
    }


    public void getTeam(String id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HockeyEyPiAy.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HockeyEyPiAy api = retrofit.create(HockeyEyPiAy.class);

        Call<RequestResponseTeamDetailsModel> call = api.getTeam(id);

        call.enqueue(new Callback<RequestResponseTeamDetailsModel>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseTeamDetailsModel> call, retrofit2.Response<RequestResponseTeamDetailsModel> response) {
                RequestResponseTeamDetailsModel gamesResponse = response.body();
                List<RequestResponseTeamDetailsModel.Response> gamesModels = gamesResponse.getResponse();
                RequestResponseTeamDetailsModel.Response response1 = gamesModels.get(0);
                ProgressBar dialogLoading = teamDialog.findViewById(R.id.dialog_loading);
                TextView arena = teamDialog.findViewById(R.id.team_dialog_arena);
                    dialogLoading.setVisibility(ProgressBar.VISIBLE);
                ImageView dialogTeamLogo = teamDialog.findViewById(R.id.team_dialog_logo);
                TextView dialogTeamName = teamDialog.findViewById(R.id.team_dialog_name);
                TextView dialogTeamFounded = teamDialog.findViewById(R.id.team_dialog_founded);
                TextView dialogArenaName = teamDialog.findViewById(R.id.team_dialog_arena_name);
                TextView dialogArenaCapacity = teamDialog.findViewById(R.id.team_dialog_arena_capacity);
                TextView dialogArenaLocation = teamDialog.findViewById(R.id.team_dialog_arena_location);

                Picasso.get().load(response1.getLogo()).into(dialogTeamLogo);
                dialogTeamName.setText("Team Name:\n" + nullable(response1.getName()));
                dialogTeamFounded.setText("Founded:\n" + nullable(response1.getFounded()));
                dialogArenaName.setText("Name:\n" + nullable(response1.getArena().getName()));
                dialogArenaCapacity.setText("Capacity:\n" + nullable(response1.getArena().getCapacity()));
                dialogArenaLocation.setText("Location: \n" + nullable(response1.getArena().getLocation()));
                dialogLoading.setVisibility(ProgressBar.GONE);
                arena.setText("Arena");
            }
            @Override
            public void onFailure(Call<RequestResponseTeamDetailsModel> call, Throwable t) {
                Toast.makeText((Activity)context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String nullable(String string){

        return string != null ? string : "N/A";
    }
    public String nullable(Integer number){

        String strNumber = (number != null) ? number.toString() : "N/A";
        return strNumber;

    }
}
