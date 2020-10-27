package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponseTeamDetailsModel {
    @SerializedName("results")
    private Integer results;
    @SerializedName("response")
    private List<Response> response = null;

    public static class Response{
        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("logo")
        private String logo;
        @SerializedName("national")
        private Boolean national;
        @SerializedName("founded")
        private Integer founded;
        @SerializedName("arena")
        private Arena arena;
        @SerializedName("country")
        private Country country;

        public static class Arena{
            @SerializedName("name")
            private String name;
            @SerializedName("capacity")
            private Integer capacity;
            @SerializedName("location")
            private String location;

            public String getName() {
                return name;
            }

            public Integer getCapacity() {
                return capacity;
            }

            public String getLocation() {
                return location;
            }
        }

        public static class Country{
            @SerializedName("id")
            private Integer id;
            @SerializedName("name")
            private String name;
            @SerializedName("code")
            private String code;
            @SerializedName("flag")
            private String flag;

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getCode() {
                return code;
            }

            public String getFlag() {
                return flag;
            }
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLogo() {
            return logo;
        }

        public Boolean getNational() {
            return national;
        }

        public Integer getFounded() {
            return founded;
        }

        public Arena getArena() {
            return arena;
        }

        public Country getCountry() {
            return country;
        }

    }

    public Integer getResults() {
        return results;
    }

    public List<Response> getResponse() {
        return response;
    }
}
