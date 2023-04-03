package com.gft.Desafio_noticias_rapidas.dto.Noticia;

public class NoticiaDTO {

    private String title;
    private String description;
    private String link;
    private String datetime;
    private String date;
    private String time;

    public NoticiaDTO() {
    }

    public NoticiaDTO(String title, String description, String link, String datetime, String date, String time) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.datetime = datetime;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NoticiaDTO{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
