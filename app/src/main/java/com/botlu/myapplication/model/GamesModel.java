package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GamesModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("timestamp")
    private Integer timestamp;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("status")
    private Status status;
    @SerializedName("country")
    private CountryModel country;
    @SerializedName("league")
    private LeagueModel league;
    @SerializedName("teams")
    private Teams teams;
    @SerializedName("scores")
    private Scores scores;
    @SerializedName("periods")
    private Periods periods;
    @SerializedName("events")
    private Boolean events;


    public GamesModel(Integer id, String date, String time, Integer timestamp, String timezone, Status status, CountryModel country, LeagueModel league, Teams teams, Scores scores, Periods periods, Boolean events) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.timestamp = timestamp;
        this.timezone = timezone;
        this.status = status;
        this.country = country;
        this.league = league;
        this.teams = teams;
        this.scores = scores;
        this.periods = periods;
        this.events = events;
    }

    public static class Status{
        @SerializedName("long")
        private String _long;
        @SerializedName("short")
        private String _short;

        public Status(String _long, String _short) {
            this._long = _long;
            this._short = _short;
        }

        public String get_long() {
            return _long;
        }

        public String get_short() {
            return _short;
        }
    }
    public static class Teams{
        @SerializedName("home")
        private TeamsModel home;
        @SerializedName("away")
        private TeamsModel away;

        public Teams(TeamsModel home, TeamsModel away) {
            this.home = home;
            this.away = away;
        }

        public TeamsModel getHome() {
            return home;
        }

        public TeamsModel getAway() {
            return away;
        }
    }
    public static class Scores{
        @SerializedName("home")
        private Integer home;
        @SerializedName("away")
        private Integer away;

        public Scores(Integer home, Integer away) {
            this.home = home;
            this.away = away;
        }

        public Integer getHome() {
            return home;
        }

        public Integer getAway() {
            return away;
        }
    }
    public static class Periods{
        @SerializedName("first")
        private Object first;
        @SerializedName("second")
        private Object second;
        @SerializedName("third")
        private Object third;
        @SerializedName("overtime")
        private Object overtime;
        @SerializedName("penalties")
        private Object penalties;

        public Periods(Object first, Object second, Object third, Object overtime, Object penalties) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.overtime = overtime;
            this.penalties = penalties;
        }

        public Object getFirst() {
            return first;
        }

        public Object getSecond() {
            return second;
        }

        public Object getThird() {
            return third;
        }

        public Object getOvertime() {
            return overtime;
        }

        public Object getPenalties() {
            return penalties;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getTimezone() {
        return timezone;
    }

    public Status getStatus() {
        return status;
    }

    public CountryModel getCountry() {
        return country;
    }

    public LeagueModel getLeague() {
        return league;
    }

    public Teams getTeams() {
        return teams;
    }

    public Scores getScores() {
        return scores;
    }

    public Periods getPeriods() {
        return periods;
    }

    public Boolean getEvents() {
        return events;
    }
}
