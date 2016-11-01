package com.rox.app.movieproject.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admuin on 31/10/2016.
 */

public class ReviewServiceResponse {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("page")
        @Expose
        public Integer page;
        @SerializedName("results")
        @Expose
        public List<Result> results = new ArrayList<Result>();
        @SerializedName("total_pages")
        @Expose
        public Integer totalPages;
        @SerializedName("total_results")
        @Expose
        public Integer totalResults;


        public ReviewServiceResponse() {
        }


        public ReviewServiceResponse(Integer id, Integer page, List<Result> results, Integer totalPages, Integer totalResults) {
            this.id = id;
            this.page = page;
            this.results = results;
            this.totalPages = totalPages;
            this.totalResults = totalResults;
        }

        public ReviewServiceResponse withId(Integer id) {
            this.id = id;
            return this;
        }

        public ReviewServiceResponse withPage(Integer page) {
            this.page = page;
            return this;
        }

        public ReviewServiceResponse withResults(List<Result> results) {
            this.results = results;
            return this;
        }

        public ReviewServiceResponse withTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public ReviewServiceResponse withTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
            return this;
        }


    public class Result {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("author")
        @Expose
        public String author;
        @SerializedName("content")
        @Expose
        public String content;
        @SerializedName("url")
        @Expose
        public String url;


        public Result() {
        }


        public Result(String id, String author, String content, String url) {
            this.id = id;
            this.author = author;
            this.content = content;
            this.url = url;
        }

        public Result withId(String id) {
            this.id = id;
            return this;
        }

        public Result withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Result withContent(String content) {
            this.content = content;
            return this;
        }

        public Result withUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
