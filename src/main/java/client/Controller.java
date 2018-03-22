package client;

import client.dao.Dao;
import client.model.CurrentArticle;

import java.util.List;

public final class Controller {
    private static final Controller INSTANCE = new Controller();
    private Controller() {}
    public static Controller getInstance() { return INSTANCE; }

    public List<String> takeTitles() {
        return null;
    }

    public String add(String title, String body) {
        return null;
    }

    public String delete(int id) {
        return null;
    }

    public String update(int id, String body) {
        return null;
    }

    public void takeArticle(String title) {
        CurrentArticle.getInstance().setArticle(Dao.getInstance().takeArticle(title));
    }
 }