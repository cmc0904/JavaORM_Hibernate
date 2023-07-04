package cmc.project.annotations;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherInformation  implements Serializable {
    private static final String url = "https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109";
    public List<Weather> getWeatherInformation() throws IOException {
        List<Weather> weathers = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();
        Elements locations = doc.select("location");

        for (Element location : locations) {
            for(Element data : locations.select("data")) {
                weathers.add(
                        new Weather(
                            location.select("city").text(),
                            data.select("tmEf").text(),
                            data.select("wf").text(),
                            data.select("tmn").text(),
                            data.select("tmx").text()
                        )
                );

            }
        }

        return weathers;
    }

}
