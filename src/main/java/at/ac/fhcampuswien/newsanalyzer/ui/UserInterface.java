package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.NewsApiException;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.SortBy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface
{
	private Controller ctrl = new Controller();
	public static final String APIKEY = "c7b3c13cf0ba4b4db0f6f086b526c460";

	public void getDataFromCtrl1() {
		System.out.println("Euro 2021 news");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("sport")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.de)
				.setSourceCategory(Category.sports)
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDataFromCtrl2() {
		// TODO implement me
		System.out.println("Corona");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.gb)
				.setSourceCategory(Category.health)
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDataFromCtrl3(){
		// TODO implement me
		System.out.println("Stocks News");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Stocks")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.us)
				.setSourceCategory(Category.business)
				.setSortBy(SortBy.RELEVANCY)
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDataForCustomInput() {
		// TODO implement me
		System.out.println("New Movies");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Movies")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.us)
				.setSourceCategory(Category.entertainment)
				.createNewsApi();

		ctrl.process(newsApi);
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Euro 2021 news", this::getDataFromCtrl1);
		menu.insert("b", "Corona", this::getDataFromCtrl2);
		menu.insert("c", "Stocks News", this::getDataFromCtrl3);
		menu.insert("d", "New Movies",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
		while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
				number = null;
			} else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
				number = null;
			}
		}
		return number;
	}
}
