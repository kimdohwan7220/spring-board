package com.project.main.domain;

public class Post {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public Post(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void updateContent(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
    }

    public boolean hasId(Long id) {
        return this.id.equals(id);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getWriter() { return writer; }
    
}
