package at.ac.fhcampuswien.newsapi;

public class NewsApiException extends Exception {
    public NewsApiException(String error){
        super(error);
    }
}
