package tec.bd.weather.service;

import tec.bd.weather.entity.Log;
import tec.bd.weather.repository.Repository;

import java.util.List;

public class LogServiceImpl implements LogService {

    private Repository<Log, Integer> logRepository;

    public LogServiceImpl(Repository<Log, Integer> logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> getLogs() {
        return this.logRepository.findAll();
    }

    @Override
    public List<Log> getLogs(int numElements) {
        return this.logRepository.findAll();
    }
}