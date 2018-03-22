package client.model;

import java.util.Date;
import java.util.Objects;

public class Article {
    private int articleId;
    private String title;
    private String body;
    private Date date;
    private Author author;
    private Resource resource;

    public Article() {
        title = "";
        body = "";
        date = new Date();
        author = new Author();
        resource = new Resource();
    }

    void reset() {
        articleId = 0;
        title = "";
        body = "";
        date = new Date(0);
        author.reset();
        resource.reset();
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public Author getAuthor() {
        return author;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return articleId == article.articleId &&
                Objects.equals(title, article.title) &&
                Objects.equals(body, article.body) &&
                Objects.equals(date, article.date) &&
                Objects.equals(author, article.author) &&
                Objects.equals(resource, article.resource);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, title, body, date, author, resource);
    }
}