package smarthome.persistence;

import smarthome.domain.house.House;
import smarthome.domain.vo.housevo.HouseIDVO;

public interface HouseRepository extends Repository<HouseIDVO, House> {
    House getFirstHouse();
    HouseIDVO getFirstHouseIDVO();
    boolean update(House house);
}
