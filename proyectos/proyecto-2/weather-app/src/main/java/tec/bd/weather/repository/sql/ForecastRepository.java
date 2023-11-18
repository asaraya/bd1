package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ForecastRepository implements Repository<Forecast, Integer> {

    private final DataSource dataSource;

    public ForecastRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Forecast> findById(Integer forecastId) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.FIND_FORECAST_BY_ID)) {

            stmt.setInt(1, forecastId);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var forecast = this.fromResultSet(rs);
                return Optional.of(forecast);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve forecasts", e);
        }
    }

    public List<Forecast> findAll() {
        List<Forecast> allForecasts = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_FORECAST)) {

            // No es necesario establecer un valor para el parámetro en este caso

            try (ResultSet rs = stmt.executeQuery()) {
                // Loop through the result set
                while (rs.next()) {
                    var forecast = this.fromResultSet(rs);
                    allForecasts.add(forecast);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve forecasts", e);
        }

        return allForecasts;
    }

    @Override
    public Forecast save(Forecast forecast) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.CREATE_FORECAST)) {

            stmt.setInt(1, forecast.getCityCode());
            stmt.setFloat(2, forecast.getTemperature());
            stmt.setDate(3, new java.sql.Date(forecast.getForecastDate().getTime()));
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.executeUpdate();

            int newForecastCode = stmt.getInt(4);

            // Asignar el valor al objeto Forecast
            forecast.setId(newForecastCode);

            return forecast;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save forecast", e);
        }
    }

    @Override
    public void delete(Integer forecastId) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.DELETE_FORECAST_BY_ID)) {

            stmt.setInt(1, forecastId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete forecast", e);
        }
    }

    @Override
    public Forecast update(Forecast forecast) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.UPDATE_FORECAST)) {

            stmt.setInt(1, forecast.getCityCode());
            stmt.setFloat(2, forecast.getTemperature());
            stmt.setDate(3, new java.sql.Date(forecast.getForecastDate().getTime()));
            stmt.setInt(4, forecast.getId());
            int affectedRows = stmt.executeUpdate();

            return forecast;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update forecast", e);
        }
    }

    private Forecast fromResultSet(ResultSet rs) throws SQLException {
        var forecastDate = rs.getDate("forecast_date");
        return new Forecast(
                rs.getInt("city_code"),
                rs.getFloat("temperature"),
                new Date(forecastDate.getTime())
        );
    }



}
