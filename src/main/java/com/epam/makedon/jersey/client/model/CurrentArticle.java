package com.epam.makedon.jersey.client.model;

import java.util.Observable;

public final class CurrentArticle extends Observable {
    private static final CurrentArticle INSTANCE = new CurrentArticle();
    private CurrentArticle() {}
    public static CurrentArticle getInstance() {
        return INSTANCE;
    }

    private Article article = new Article();

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void changeObserver() {
        super.setChanged();
        this.notifyObservers();
    }
}