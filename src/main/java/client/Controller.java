package client;

import java.util.List;

public final class Controller {
    private static final Controller INSTANCE = new Controller();
    private Controller() {}
    public static Controller getInstance() { return INSTANCE; }

    public List<String> takeTitles() {
        List<String> result = null;

        if (getServerType() == ServerType.Type.RPC) {
            result = controllerRPC.takeTitles();
        } else if (getServerType() == ServerType.Type.SOAP) {
            result = controllerSOAP.takeArticleList();
        }

        return result;
    }
    public void takeArticle(String title) {
        if (getServerType() == ServerType.Type.RPC) {
            controllerRPC.takeArticle(title);
        } else if (getServerType() == ServerType.Type.SOAP) {
            controllerSOAP.takeArticle(title);
        }
    }
    public String add(String title, String body) {
        String result = null;
        if (getServerType() == ServerType.Type.RPC) {
            result = controllerRPC.addArticle(title, body);
        } else if (getServerType() == ServerType.Type.SOAP) {
            result = controllerSOAP.add(title, body);
        }
        return result;
    }
    public String delete(int id) {
        String result = null;
        if (getServerType() == ServerType.Type.RPC) {
            result = controllerRPC.deleteArticle(id);
        } else if (getServerType() == ServerType.Type.SOAP) {
            result = controllerSOAP.deleteArticle(id);
        }
        return result;
    }
    public String update(int id, String body) {
        String result = null;
        if (getServerType() == ServerType.Type.RPC) {
            result = controllerRPC.updateArticle(id, body);
        } else if (getServerType() == ServerType.Type.SOAP) {
            result = controllerSOAP.updateArticle(id, body);
        }
        return result;
    }

    public List<String> takeTitles() {
        Transport transport = new Transport();
        HandbookService.Client client = transport.getClient();

        try {
            List<Article> articleList = client.takeArticleList();
            List<String> titleList = new ArrayList<String>();
            for (Article article : articleList) {
                titleList.add(article.getTitle());
            }
            return titleList;
        } catch (TException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException();
        } finally {
            transport.close();
        }
    }

    public void takeArticle(String title) {
        Transport transport = new Transport();
        HandbookService.Client client = transport.getClient();

        try {
            Article article = client.takeArticle(title);
            CurrentPage currentPage = CurrentPage.getInstance();

            currentPage.setArticleId(article.getArticleId());
            currentPage.setTitle(article.getTitle());
            currentPage.setBody(article.getBody());
            currentPage.setDate(new Date(article.getDate().year, article.getDate().month, article.getDate().day));
            currentPage.setImageId(article.getImage().imageId);
            currentPage.setPath(article.getImage().path);
            currentPage.setAuthorId(article.getAuthor().authorId);
            currentPage.setName(article.getAuthor().name);
            currentPage.setSurname(article.getAuthor().surname);
            currentPage.setCountry(article.getAuthor().country);
        } catch (TException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException();
        } finally {
            transport.close();
        }
    }
    public String addArticle(String title, String body) {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);

        Transport transport = new Transport();
        HandbookService.Client client = transport.getClient();

        String result;
        try {
            result = client.add(article);
        } catch (TException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException();
        } finally {
            transport.close();
        }
        return result;
    }
    public String deleteArticle(int id) {
        Article article = new Article();
        article.setArticleId(id);

        Transport transport = new Transport();
        HandbookService.Client client = transport.getClient();

        String result;
        try {
            result = client.deleteArticle(article);
        } catch (TException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException();
        } finally {
            transport.close();
        }
        return result;
    }
    public String updateArticle(int id, String body) {
        Article article = new Article();
        article.setArticleId(id);
        article.setBody(body);

        Transport transport = new Transport();
        HandbookService.Client client = transport.getClient();

        String result;
        try {
            result = client.updateArticle(article);
        } catch (TException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException();
        } finally {
            transport.close();
        }
        return result;
    }
 }