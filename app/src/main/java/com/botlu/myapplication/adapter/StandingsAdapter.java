package com.botlu.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.botlu.myapplication.R;
import com.botlu.myapplication.model.StandingModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {
    Context context;
    List<StandingModel> standingModels;
    StandingModel standingModel;
    StandingModel teamStandingDialog;
    Dialog standingDialog;
    public class StandingsViewHolder extends RecyclerView.ViewHolder {
        TextView group;
        TextView teamName;
        TextView winLoss;
        TextView teamId;
        ImageView teamLogo;

        public StandingsViewHolder(@NonNull View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.standings_group);
            teamId = itemView.findViewById(R.id.standings_team_id);
            teamName = itemView.findViewById(R.id.standings_team_name);
            winLoss = itemView.findViewById(R.id.standings_win_loss);
            teamLogo = itemView.findViewById(R.id.standings_team_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    standingDialog = new Dialog(context);
                    standingDialog.setContentView(R.layout.team_standing_detail_list);
                    standingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    standingDialog.show();

                    for(int i = 0; i < standingModels.size(); i++){
                        if(standingModels.get(i).getTeam().getId() == Integer.parseInt(teamId.getText().toString())){
                            teamStandingDialog = standingModels.get(i);

                            TextView conference = standingDialog.findViewById(R.id.team_standing_dialog_conference);
                            TextView teamDialogName = standingDialog.findViewById(R.id.team_standing_dialog_name);
                            TextView league = standingDialog.findViewById(R.id.team_standing_dialog_league);
                            TextView country = standingDialog.findViewById(R.id.team_standing_dialog_country);
                            TextView played = standingDialog.findViewById(R.id.team_standing_dialog_played);
                            TextView win = standingDialog.findViewById(R.id.team_standing_dialog_win);
                            TextView oWin = standingDialog.findViewById(R.id.team_standing_dialog_win_overtime);
                            TextView lose = standingDialog.findViewById(R.id.team_standing_dialog_lose);
                            TextView oLose = standingDialog.findViewById(R.id.team_standing_dialog_lose_overtime);
                            ImageView teamDialogLogo = standingDialog.findViewById(R.id.team_standing_dialog_logo);

                            conference.setText(teamStandingDialog.getGroup().getName());
                            teamDialogName.setText("Team:\n"+ teamStandingDialog.getTeam().getName());
                            league.setText("League: "+ teamStandingDialog.getLeague().getName());
                            country.setText("Country: "+ teamStandingDialog.getCountry().getName());
                            played.setText("Games Played: "+ teamStandingDialog.getGames().getPlayed().toString());
                            win.setText(teamStandingDialog.getGames().getWin().getTotal().toString());
                            oWin.setText(teamStandingDialog.getGames().getWinOvertime().getTotal().toString());
                            lose.setText(teamStandingDialog.getGames().getLose().getTotal().toString());
                            oLose.setText(teamStandingDialog.getGames().getLoseOvertime().getTotal().toString());
                            Picasso.get().load(teamStandingDialog.getTeam().getLogo()).into(teamDialogLogo);
                            break;
                        }
                    }
                }
            });

        }
    }

    public StandingsAdapter(Context context,List<StandingModel> standingModels){
        this.context = context;
        this.standingModels = standingModels;
    }

    @NonNull
    @Override
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standings_list,parent,false);
        return new StandingsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StandingsViewHolder holder, int position) {
        standingModel = standingModels.get(position);
        holder.group.setText(standingModel.getGroup().getName());
        holder.teamName.setText(standingModel.getTeam().getName());
        holder.teamId.setText(standingModel.getTeam().getId().toString());
        holder.winLoss.setText("W: "+(standingModel.getGames().getWin().getTotal()+standingModel.getGames().getWinOvertime().getTotal()) +
                " - L:" + (standingModel.getGames().getLose().getTotal() + standingModel.getGames().getLoseOvertime().getTotal()));
        Picasso.get().load(standingModel.getTeam().getLogo()).into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return standingModels.size();
    }

    public String nullable(String string){

        return string != null ? string : "";
    }
    public String nullable(Integer number){

        String strNumber = (number != null) ? number.toString() : "";
        return strNumber;
    }
}
