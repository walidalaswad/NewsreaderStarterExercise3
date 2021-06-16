package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiException;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;

import java.util.*;

public class Controller {

	public static final String APIKEY = "c7b3c13cf0ba4b4db0f6f086b526c460";  //TODO add your api key
	NewsResponse newsResponse = new NewsResponse();

	int numberOfArticles = 0;
	String providerWithMostArticles = "";
	String authorWithShortestName = "gskjghsdakjghsadkghsdkjghsakghsakjghasjkgasd";

	public void process(NewsApi newsApi) {
		System.out.println("Start process");

		//TODO implement Error handling

		//TODO load the news based on the parameters

		try {
			newsResponse = newsApi.getNews();
		}
		catch (NewsApiException e){
			System.out.println("Somehow we couldn't get the news :(");
		}

		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));

			articles.stream().forEach(article -> numberOfArticles++);

			Map<String, Integer> providers = new HashMap<>();

			List<String> titles = new ArrayList<>();


			//Providers with most articles
			articles.stream().forEach(i -> {
				if (providers.get(i.getSource().getName()) == null) {
					providers.put(i.getSource().getName(), 1);
				} else {
					providers.put(i.getSource().getName(), providers.get(i.getSource().getName()) + 1);
				}
			});

			providerWithMostArticles = providers.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

			//Author with shortest name
			articles.stream().forEach(article -> {
				if(!article.equals(null) && !(article.getAuthor() == null) && article.getAuthor().length() < authorWithShortestName.length())
					authorWithShortestName = article.getAuthor();
			});

			//Sort titles with length
			articles.stream().forEach(article -> {
				if(!titles.contains(article.getTitle()))
					titles.add(article.getTitle());
			});

			titles.sort((t1, t2) -> t2.length() - t1.length());

			System.out.println("\n\nThe total number of articles for this category is: " + numberOfArticles);
			System.out.println("\n\nThe author with the shortest name is: " + authorWithShortestName);
			System.out.print("\n\nThe titles sorted: \n");
			titles.stream().forEach(title -> System.out.println(title));
			System.out.println("\n\n Provider with most articles: " + providerWithMostArticles);

		}
		//TODO implement methods for analysis

		System.out.println("End process");
	}
}
