package client.model;

import java.util.Date;
import java.util.Objects;

public class Article {
    private int articleId;
    private String title;
    private String body;
    private Date date = new Date();

    void reset() {
        articleId = 0;
        title = "";
        body = "";
        date = new Date(0);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return articleId == article.articleId &&
                Objects.equals(title, article.title) &&
                Objects.equals(body, article.body) &&
                Objects.equals(date, article.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, title, body, date);
    }
}