package client.model;

import java.util.Objects;

public class Resource {
    private int resourceId;
    private String name;
    private String url;

    void reset() {
        resourceId = 0;
        name = "";
        url = "";
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return resourceId == resource.resourceId &&
                Objects.equals(name, resource.name) &&
                Objects.equals(url, resource.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(resourceId, name, url);
    }
}
