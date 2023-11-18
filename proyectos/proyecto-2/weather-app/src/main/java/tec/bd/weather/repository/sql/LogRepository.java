package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.Log;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class LogRepository implements Repository<Log, Integer> {

    private final DataSource dataSource;

    public LogRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public Optional<Log> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Log> findAll() {
        List<Log> allLogs = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_LOGS)) {

            stmt.setInt(1, 0);
            stmt.setInt(2, 0);

            try (ResultSet rs = stmt.executeQuery()) {
                // Loop through the result set
                while (rs.next()) {
                    var log = new Log(rs.getInt(1), rs.getString(2));
                    allLogs.add(log);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve logs", e);
        }

        return allLogs;
    }

    @Override
    public Log save(Log log) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Log update(Log source) {
        return null;
    }

}
