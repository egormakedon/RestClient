package com.epam.makedon.jersey.client;

import com.epam.makedon.jersey.client.model.Article;
import com.epam.makedon.jersey.client.model.CurrentArticle;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public final class Controller {
    private static final Controller INSTANCE = new Controller();
    public static Controller getInstance() { return INSTANCE; }

    private static final String URL_TAKE_ARTICLE_LIST = "http://localhost:8080/RestServer-1.0/service";

    private Client client;

    private Controller() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(config);
    }

    public List<String> takeArticleList() {
        WebResource service = client.resource(URL_TAKE_ARTICLE_LIST);
        List<Article> articleList = service.path("takeArticleList").accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Article>>(){});
        List<String> titles = new ArrayList<String>();
        for (Article article : articleList) {
            titles.add(article.getTitle());
        }
        return titles;
    }

    public void takeArticle(String title) {
        WebResource service = client.resource(URL_TAKE_ARTICLE_LIST);
        Article article = service.path("takeArticle").path(title).accept(MediaType.APPLICATION_JSON).get(Article.class);
        CurrentArticle.getInstance().setArticle(article);
    }

    public void delete(int id) {
        WebResource service = client.resource(URL_TAKE_ARTICLE_LIST);
        service.path(String.valueOf(id)).delete();
    }

    public void add(String title, String body) {
        WebResource service = client.resource(URL_TAKE_ARTICLE_LIST);
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        service.path("add").type(MediaType.APPLICATION_JSON).post(article);
    }

    public void update(int id, String body) {
        WebResource service = client.resource(URL_TAKE_ARTICLE_LIST);
        Article article = new Article();
        article.setArticleId(id);
        article.setBody(body);
        service.path("update").type(MediaType.APPLICATION_JSON).post(article);
    }
 }