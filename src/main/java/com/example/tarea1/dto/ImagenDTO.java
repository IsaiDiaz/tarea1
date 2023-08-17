package com.example.tarea1.dto;

public class ImagenDTO {
    private String url;
    private String message;

    public ImagenDTO() {
    }
    public ImagenDTO(String url, String message){
        this.url = url;
        this.message = message;
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
