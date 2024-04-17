package smarthome.domain.house;

import smarthome.domain.vo.housevo.LocationVO;

public interface HouseFactory {
    House createHouse(LocationVO locationVO);
}
