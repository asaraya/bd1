package tec.bd.weather.service;

import tec.bd.weather.entity.Log;

import java.util.List;

public interface LogService {

    List<Log> getLogs();

    List<Log> getLogs(int numElements);
}