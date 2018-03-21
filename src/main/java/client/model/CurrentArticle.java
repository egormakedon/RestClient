package client.model;

import java.util.Observable;

public final class CurrentArticle extends Observable {
    private static final CurrentArticle INSTANCE = new CurrentArticle();
    private CurrentArticle() {}
    public static CurrentArticle getInstance() {
        return INSTANCE;
    }

    private Article article = new Article();
    private Author author = new Author();
    private Resource resource = new Resource();

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Article getArticle() {
        return article;
    }

    public Author getAuthor() {
        return author;
    }

    public Resource getResource() {
        return resource;
    }

    public void reset() {
        article.reset();
        author.reset();
        resource.reset();
    }
    public void changeObserver() {
        super.setChanged();
        this.notifyObservers();
    }
}