package tec.bd.weather.repository.sql;

public class Queries {
    // Forecast
    public static final String FIND_ALL_FORECAST = "call find_all_forecasts(?)";
    public static final String FIND_FORECAST_BY_ID = "call find_forecast_by_id(?)";
    public static final String CREATE_FORECAST = "call create_forecast(?, ?, ?, ?)";
    public static final String DELETE_FORECAST_BY_ID = "call delete_forecast(?)";
    public static final String UPDATE_FORECAST = "call update_forecast(?, ?, ?, ?)";


    // Country
    public static final String FIND_ALL_COUNTRIES_PROC = "call find_all_countries(?)";
    public static final String CREATE_COUNTRY_PROC = "call create_country(?, ?)";
    public static final String DELETE_COUNTRY_BY_ID_PROC = "call delete_country(?)";
    public static final String UPDATE_COUNTRY_PROC = "call update_country(?, ?)";

    // City
    public static final String FIND_CITY_BY_ID_PROC = "call find_city_by_id(?)";
    public static final String FIND_ALL_CITIES_PROC = "call find_all_cities(?)";
    public static final String CREATE_CITY_PROC = "call create_city(?, ?, ?, ?)";
    public static final String DELETE_CITY_BY_ID_PROC = "call delete_city(?)";
    public static final String UPDATE_CITY_PROC = "call update_city(?, ?, ?)";

    // State
    public static final String FIND_STATE_BY_ID_PROC = "call find_state_by_id(?)";
    public static final String FIND_ALL_STATES_PROC = "call find_all_states(?)";
    public static final String CREATE_STATE_PROC = "call create_state(?, ?, ?)";
    public static final String DELETE_STATE_BY_ID_PROC = "call delete_state(?)";
    public static final String UPDATE_STATE_PROC = "call update_state(?, ?)";

    // Logs
    public static final String FIND_LOGS = "call find_logs(?, ?)";
}