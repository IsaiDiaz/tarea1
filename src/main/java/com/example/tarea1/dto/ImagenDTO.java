package com.example.tarea1.dto;

public class ImagenDTO {

    private Long id;
    private String url;
    private String message;

    public ImagenDTO() {
    }
    public ImagenDTO(Long id, String url, String message){
        this.id = id;
        this.url = url;
        this.message = message;
    }

    public ImagenDTO(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
