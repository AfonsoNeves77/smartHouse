package smarthome.service;

import smarthome.domain.house.House;
import smarthome.domain.vo.housevo.LocationVO;

import java.util.Optional;

public interface HouseService {

    House addHouse(LocationVO locationVO);

    Optional<LocationVO> updateLocation(LocationVO locationVO);
}