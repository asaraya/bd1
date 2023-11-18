package tec.bd.weather.service;

import tec.bd.weather.entity.State;
import tec.bd.weather.repository.Repository;

import java.util.List;
import java.util.Optional;

public class StateServiceImpl implements StateService {

    private Repository<State, Integer> stateRepository;

    public StateServiceImpl(Repository<State, Integer> stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> getAllStates() {
        return this.stateRepository.findAll();
    }

    @Override
    public Optional<State> getStateById(int stateId) {
        // Validaciones. El stateId es mayor que cero?

        return this.stateRepository.findById(stateId);
    }

    @Override
    public State newState(String stateName, int countryId) {
        // No permitir que el stateName sea nulo o vacío

        // Validar si el stateName ya existe en la base de datos
        // para esto habría que buscar el nombre del estado stateName en la base de datos
        // y ver si existe. Si ya existe no se guarda.

        var stateToBeSaved = new State(null, stateName, countryId);
        return this.stateRepository.save(stateToBeSaved);
    }

    @Override
    public void removeByStateId(int stateId) {
        // Validaciones. El stateId es mayor que cero?

        var stateFromDBOpt = this.getStateById(stateId);

        if (stateFromDBOpt.isEmpty()) {
            throw new RuntimeException("State Id: " + stateId + " not found");
        }

        this.stateRepository.delete(stateId);
    }

    @Override
    public State updateState(State state) {
        // validar si el state.Id existe en la base de datos
        // validar si el nombre del estado ya existe en la BD

        var stateUpdated = this.stateRepository.update(state);
        return stateUpdated;
    }
}
