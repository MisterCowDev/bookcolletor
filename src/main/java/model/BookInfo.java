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

}
