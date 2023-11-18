package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.State;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateRepository implements Repository<State, Integer> {

    private final DataSource dataSource;

    public StateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<State> findById(Integer stateId) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_STATES_PROC)) {

            stmt.setInt(1, stateId);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var state = new State(rs.getInt(1), rs.getString(2), rs.getInt(3));
                return Optional.of(state);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve state", e);
        }
    }

    @Override
    public List<State> findAll() {
        List<State> allStates = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_STATES_PROC)) {

            stmt.setInt(1, 0);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var state = new State(rs.getInt(1), rs.getString(2), rs.getInt(3));
                allStates.add(state);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve states", e);
        }

        return allStates;
    }


    @Override
    public State save(State state) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.CREATE_STATE_PROC)) {

            stmt.setString(1, state.getStateName());
            stmt.setInt(2, state.getCountryId());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();

            var newState = new State(stmt.getInt(3), state.getStateName(), state.getCountryId());
            return newState;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save state", e);
        }
    }

    @Override
    public void delete(Integer stateId) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.DELETE_STATE_BY_ID_PROC)) {

            stmt.setInt(1, stateId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete state", e);
        }
    }


    @Override
    public State update(State state) {
        // es muy similar al de save
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.UPDATE_STATE_PROC)) {

            stmt.setInt(1, state.getId());
            stmt.setString(2, state.getStateName());
            stmt.executeUpdate();

            return state;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update state", e);
        }
    }
}
