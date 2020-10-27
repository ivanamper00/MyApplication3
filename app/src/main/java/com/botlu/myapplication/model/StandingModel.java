package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class StandingModel {
    @SerializedName("position")
    private Integer position;
    @SerializedName("stage")
    private String stage;
    @SerializedName("group")
    private Group group;
    @SerializedName("team")
    private TeamsModel team;
    @SerializedName("league")
    private LeagueModel league;
    @SerializedName("country")
    private CountryModel country;
    @SerializedName("games")
    private Games games;
    @SerializedName("goals")
    private Goals goals;
    @SerializedName("points")
    private Integer points;
    @SerializedName("form")
    private String form;

    public StandingModel(Integer position, String stage, Group group, TeamsModel team, LeagueModel league, CountryModel country, Games games, Goals goals, Integer points, String form) {
        this.position = position;
        this.stage = stage;
        this.group = group;
        this.team = team;
        this.league = league;
        this.country = country;
        this.games = games;
        this.goals = goals;
        this.points = points;
        this.form = form;
    }

    public static class Group{
        @SerializedName("name")
        private String name;

        public Group(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    public static class Games{
        @SerializedName("played")
        private Integer played;
        @SerializedName("win")
        private Win win;
        @SerializedName("win_overtime")
        private Win winOvertime;
        @SerializedName("lose")
        private Win lose;
        @SerializedName("lose_overtime")
        private Win loseOvertime;

        public Games(Integer played, Win win, Win winOvertime, Win lose, Win loseOvertime) {
            this.played = played;
            this.win = win;
            this.winOvertime = winOvertime;
            this.lose = lose;
            this.loseOvertime = loseOvertime;
        }

        public static class Win{
            @SerializedName("total")
            private Integer total;
            @SerializedName("percentage")
            private String percentage;

            public Win(Integer total, String percentage) {
                this.total = total;
                this.percentage = percentage;
            }

            public Integer getTotal() {
                return total;
            }

            public String getPercentage() {
                return percentage;
            }
        }

        public Integer getPlayed() {
            return played;
        }

        public Win getWin() {
            return win;
        }

        public Win getWinOvertime() {
            return winOvertime;
        }

        public Win getLose() {
            return lose;
        }

        public Win getLoseOvertime() {
            return loseOvertime;
        }
    }
    public static class Goals{
        @SerializedName("for")
        private Integer _for;
        @SerializedName("against")
        private Integer against;

        public Goals(Integer _for, Integer against) {
            this._for = _for;
            this.against = against;
        }

        public Integer get_for() {
            return _for;
        }

        public Integer getAgainst() {
            return against;
        }
    }

    public Integer getPosition() {
        return position;
    }

    public String getStage() {
        return stage;
    }

    public Group getGroup() {
        return group;
    }

    public TeamsModel getTeam() {
        return team;
    }

    public LeagueModel getLeague() {
        return league;
    }

    public CountryModel getCountry() {
        return country;
    }

    public Games getGames() {
        return games;
    }

    public Goals getGoals() {
        return goals;
    }

    public Integer getPoints() {
        return points;
    }

    public String getForm() {
        return form;
    }
}
