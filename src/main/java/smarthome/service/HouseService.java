package smarthome.service;

import smarthome.domain.vo.housevo.LocationVO;

public interface HouseService {
    boolean addHouse(LocationVO locationVO);
    boolean updateLocation(LocationVO locationVO);
}
