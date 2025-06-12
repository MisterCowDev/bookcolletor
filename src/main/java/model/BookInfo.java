package model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class BookInfo {
    private Integer id;
    private String title;
    private String authorName;
    private Integer birthYear;
    private List<String> languages;
    private Double numbreDownload;

    public BookInfo(Integer id, String title, String authorName, Integer birthYear, Double numbreDownload, List<String> languages) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.birthYear = birthYear;
        this.numbreDownload = numbreDownload;
        this.languages = languages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getNumbreDownload() {
        return numbreDownload;
    }

    public void setNumbreDownload(Double numbreDownload) {
        this.numbreDownload = numbreDownload;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", birthYear=" + birthYear +
                ", languages=" + languages +
                ", numbreDownload=" + numbreDownload +
                '}';
    }
}
