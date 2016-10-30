package com.example.administrator.meituan2.Model;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DianyingWaitViewpagerData {

    /**
     * img : http://p1.meituan.net/movie/a1e8b7412c4ebeaea7f4c71d05f9456413901.jpg
     * movieId : 246124
     * movieName : 奇异博士
     * name : 《奇异博士》Astral Form片段
     * originName : Astral Form片段
     * url : http://maoyan.meituan.net/movie/videos/616fbaba449c4afe83c786d81307edb2.mp4
     * videoId : 81340
     * wish : 148634
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String img;
        private int movieId;
        private String movieName;
        private String name;
        private String originName;
        private String url;
        private int videoId;
        private int wish;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getWish() {
            return wish;
        }

        public void setWish(int wish) {
            this.wish = wish;
        }
    }
}
