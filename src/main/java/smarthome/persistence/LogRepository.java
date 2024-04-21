package smarthome.persistence;

import smarthome.domain.log.Log;
import smarthome.domain.vo.logvo.LogIDVO;

public interface LogRepository extends Repository<LogIDVO,Log>{
    boolean save(Log log);
    boolean isPresent(LogIDVO id);
    Iterable<Log> findAll();
    Log findById(LogIDVO logIDVO);
}
