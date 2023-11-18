package tec.bd.weather.entity;

import java.util.Date;

public class Forecast {

    private Integer id; // forecast_code en la base de datos

    private Integer cityCode; // city_code en la base de datos

    private float temperature;

    private Date forecastDate;

    public Forecast() { }

    public Forecast(Integer cityCode, float temperature, Date forecastDate) {
        this.cityCode = cityCode;
        this.temperature = temperature;
        this.forecastDate = forecastDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public static void validate(Forecast forecast) {
        if (forecast == null) {
            throw new RuntimeException("No forecast was provided");
        }
        if (forecast.getCityCode() == null) {
            throw new RuntimeException("No forecast city code was provided");
        }

        if (forecast.getTemperature() < 0) {
            throw new RuntimeException("Weather forecast temperature is invalid");
        }
        if (forecast.getForecastDate() == null) {
            throw new RuntimeException("Weather forecast date is invalid");
        }
        if (forecast.getForecastDate().after(new Date(System.currentTimeMillis()))) {
            throw new RuntimeException("Weather forecast date is in the future");
        }
    }

    @Override
    public String toString() {
        return "Forecast {" +
                "id = " + id +
                ", cityCode = " + cityCode +
                ", temperature = " + temperature +
                ", date = '" + forecastDate + '\'' +
                '}';
    }
}
