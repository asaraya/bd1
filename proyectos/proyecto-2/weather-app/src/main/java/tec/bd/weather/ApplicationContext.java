package tec.bd.weather;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sqlite.SQLiteDataSource;
import tec.bd.weather.entity.City;
import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.entity.State;
import tec.bd.weather.entity.Log;
import tec.bd.weather.repository.Repository;
//import tec.bd.weather.repository.memory.InMemoryForecastRepository;
import tec.bd.weather.repository.sql.CityRepository;
import tec.bd.weather.repository.sql.CountryRepository;
import tec.bd.weather.repository.sql.ForecastRepository;
import tec.bd.weather.repository.sql.StateRepository;
import tec.bd.weather.service.*;
import tec.bd.weather.repository.sql.LogRepository;

import javax.sql.DataSource;

public class ApplicationContext {

    private static final String SQLITE_DB_URL = "jdbc:sqlite::resource:sqlite/weather-service.db";
    private static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/weather_service" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String MYSQL_DB_USERNAME = "weatherappuser";
    private static final String MYSQL_DB_PASSWORD = "weatherapppass";
    private DataSource sqliteDataSource;

    private DataSource mysqlDataSource;

    private Repository<Forecast, Integer> inMemoryForecastRepository;
    private Repository<Forecast, Integer> sqlForecastRepository;
    private Repository<Country, Integer> sqlCountryRepository;
    private Repository<City, Integer> sqlCityRepository;
    private Repository<Log, Integer> sqlLogRepository;
    private CityService cityService;
    private Repository<State, Integer> sqlStateRepository;
    private StateService stateService;
    private WeatherService weatherService;
    private CountryService countryService;
    private LogService logService;

    public ApplicationContext() {
        initSqliteDataSource();
        initMySqlDataSource();

        //initInMemoryForecastRepository();
        initSQLForecastRepository();
        initSQLCountryRepository();

        initSQLCityRepository();
        initCityService();

        initSQLStateRepository();
        initStateService();

        initWeatherService();
        initCountryService();

        initSQLLogRepository();
        initLogService();
    }

    private void initSqliteDataSource() {
        var sqliteDS = new SQLiteDataSource();
        sqliteDS.setUrl(SQLITE_DB_URL);
        this.sqliteDataSource = sqliteDS;
    }

    public void initMySqlDataSource() {
        var mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(MYSQL_DB_URL);
        mysqlDataSource.setUser(MYSQL_DB_USERNAME);
        mysqlDataSource.setPassword(MYSQL_DB_PASSWORD);
        this.mysqlDataSource = mysqlDataSource;
    }

    private void initHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(MYSQL_DB_URL);
        hikariConfig.setUsername(MYSQL_DB_USERNAME);
        hikariConfig.setPassword(MYSQL_DB_PASSWORD);
        hikariConfig.addDataSourceProperty("connectionTestQuery", "SELECT 1");
        hikariConfig.addDataSourceProperty("maximumPoolSize", "4");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.mysqlDataSource = new HikariDataSource(hikariConfig);
    }

    /*private void initInMemoryForecastRepository() {
        this.inMemoryForecastRepository = new InMemoryForecastRepository();
    }*/

    private void initSQLForecastRepository() {
        this.sqlForecastRepository = new ForecastRepository(this.mysqlDataSource);
    }

    private void initWeatherService() {
        this.weatherService = new WeatherServiceImpl(this.sqlForecastRepository);
    }

    public Repository<Forecast, Integer> getInMemoryForecastRepository() {
        return this.inMemoryForecastRepository;
    }

    public Repository<Forecast, Integer> getSqlForecastRepository() {
        return this.sqlForecastRepository;
    }

    public WeatherService getWeatherService() {
        return this.weatherService;
    }

    // Country Service

    private void initSQLCountryRepository() {
        this.sqlCountryRepository = new CountryRepository(this.mysqlDataSource);
    }

    public Repository<Country, Integer> getSqlCountryRepository() {
        return this.sqlCountryRepository;
    }

    private void initCountryService() {
        this.countryService = new CountryServiceImpl(this.sqlCountryRepository);
    }

    public CountryService getCountryService() {
        return this.countryService;
    }

    // City Service
    private void initSQLCityRepository() {
        this.sqlCityRepository = new CityRepository(this.mysqlDataSource);
    }

    public Repository<City, Integer> getSqlCityRepository() {
        return this.sqlCityRepository;
    }

    private void initCityService() {
        this.cityService = new CityServiceImpl(this.sqlCityRepository);
    }

    public CityService getCityService() {
        return this.cityService;
    }

    // State service
    private void initSQLStateRepository() {
        this.sqlStateRepository = new StateRepository(this.mysqlDataSource);
    }

    public Repository<State, Integer> getSqlStateRepository() {
        return this.sqlStateRepository;
    }

    private void initStateService() {
        this.stateService = new StateServiceImpl(this.sqlStateRepository);
    }

    public StateService getStateService() {
        return this.stateService;
    }

    // Log service
    private void initSQLLogRepository() { this.sqlLogRepository = new LogRepository(this.mysqlDataSource);}

    public Repository<Log, Integer> getSqlLogRepository() {
        return this.sqlLogRepository;
    }

    private void initLogService() {
        this.logService = new LogServiceImpl(this.sqlLogRepository);
    }

    public LogService getLogService() {
        return this.logService;
    }

}