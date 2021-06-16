package at.ac.fhcampuswien.newsapi;

import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.List;

public class NewsAPIExample {

    public static final String APIKEY = "c7b3c13cf0ba4b4db0f6f086b526c460";    //TODO add your api key

    public static void main(String[] args) throws NewsApiException {

        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)// example of how to use enums
                .setSourceCountry(Country.at)       // example of how to use enums
                .setSourceCategory(Category.health) // example of how to use enums
                .createNewsApi();

        NewsResponse newsResponse = newsApi.getNews();
        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }

        newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.EVERYTHING)
                .setFrom("2020-03-20")
                .setExcludeDomains("Lifehacker.com")
                .createNewsApi();

        newsResponse = newsApi.getNews();

        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }

    }
}