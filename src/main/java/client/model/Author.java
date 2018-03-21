package client.model;

import java.util.Objects;

public class Author {
    private int authorId;
    private String name;
    private String surname;
    private String country;

    void reset() {
        authorId = 0;
        name = "";
        surname = "";
        country = "";
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId &&
                Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname) &&
                Objects.equals(country, author.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(authorId, name, surname, country);
    }
}
