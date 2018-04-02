package livelib.api;

public class Book {

    private int id;
    private String name;
    private String no;
    private String author;
    private String avg_mark;
    private String rating;
    private String top100rating;
    private String ub_rating10;
    private String ub_rating;
    private String count_readers;
    private String year;
    private String publisher;
    private String pic_url;
    private String large_pic_url;
    private String in_collection;
    private String tags;
    private String isbn;
    private String count_readers_num;
    private String count_reviews;
    private String count_quotes;
    private String count_stories;
    private String count_selections;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public String getAuthor() {
        return author;
    }

    public String getAvg_mark() {
        return avg_mark;
    }

    public String getRating() {
        return rating;
    }

    public String getTop100rating() {
        return top100rating;
    }

    public String getUb_rating10() {
        return ub_rating10;
    }

    public String getUb_rating() {
        return ub_rating;
    }

    public String getCount_readers() {
        return count_readers.replaceAll("[(]|[)]|[ ]", "");
    }

    public String getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher.contains(",") ? publisher.substring(0, publisher.lastIndexOf(",")) : publisher;
    }

    public String getPic_url() {
        return pic_url;
    }

    public String getLarge_pic_url() {
        return large_pic_url;
    }

    public String getIn_collection() {
        return "-1".equals(in_collection) ? "false" : "true";
    }

    public String getTags() {
        return tags;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCount_readers_num() {
        return count_readers_num;
    }

    public String getCount_reviews() {
        return count_reviews;
    }

    public String getCount_quotes() {
        return count_quotes;
    }

    public String getCount_stories() {
        return count_stories;
    }

    public String getCount_selections() {
        return count_selections;
    }

    @Override
    public String toString() {
        return " id: " + id +
                " name: " + name +
                " author: " + author +
                " year: " + year;
    }
}
