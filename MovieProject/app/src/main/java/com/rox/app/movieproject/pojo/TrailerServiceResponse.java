package com.rox.app.movieproject.pojo;

/**
 * Created by admuin on 31/10/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrailerServiceResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    public List<Trailer> trailers = new ArrayList<>();

    public TrailerServiceResponse() {
    }

    public TrailerServiceResponse(Integer id, List<Trailer> trailers) {
        this.id = id;
        this.trailers = trailers;
    }

    public Integer getId() {
        return id;
    }

    public List<Trailer> getResults() {
        return trailers;
    }


    public class Trailer {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("iso_639_1")
        @Expose
        private String iso6391;
        @SerializedName("iso_3166_1")
        @Expose
        private String iso31661;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("site")
        @Expose
        private String site;
        @SerializedName("size")
        @Expose
        private Integer size;
        @SerializedName("type")
        @Expose
        private String type;

        public Trailer() {
        }

        public Trailer(String id, String iso6391, String iso31661, String key, String name, String site, Integer size, String type) {
            this.id = id;
            this.iso6391 = iso6391;
            this.iso31661 = iso31661;
            this.key = key;
            this.name = name;
            this.site = site;
            this.size = size;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getIso31661() {
            return iso31661;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSite() {
            return site;
        }

        public Integer getSize() {
            return size;
        }

        public String getType() {
            return type;
        }
    }

}