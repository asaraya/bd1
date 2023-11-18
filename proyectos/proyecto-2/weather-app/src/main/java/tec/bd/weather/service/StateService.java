package tec.bd.weather.service;

import tec.bd.weather.entity.State;

import java.util.List;
import java.util.Optional;

public interface StateService {

    List<State> getAllStates();

    Optional<State> getStateById(int stateId);

    State newState(String stateName, int countryId);

    void removeByStateId(int stateId);

    State updateState(State state);
}
