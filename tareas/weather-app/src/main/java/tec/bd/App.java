package tec.bd;

import tec.bd.weather.WeatherService;
import tec.bd.weather.WeatherServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        WeatherService weatherService = new WeatherServiceImpl();
        System.out.println(weatherService.getTemperature("Alajuela"));
    }
}
