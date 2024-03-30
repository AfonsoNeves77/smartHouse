package SmartHomeDDD.repository;

import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

import java.util.*;
import java.util.stream.Collectors;


public class DeviceRepository implements Repository<DeviceIDVO, Device> {

    private static final Map<DeviceIDVO, Device> DATA = new HashMap<>();

    /**
     * Saves an entity onto the repository;
     *
     * @param entity Entity
     * @return True or False
     */
    @Override
    public boolean save(Device entity) {
        if (!validateEntity(entity) || DATA.containsKey(entity.getId())) {
            return false;
        }
        DATA.put(entity.getId(), entity);
        return true;
    }

    /**
     * Verifies if an entity is valid;
     *
     * @param entity Entity
     * @return True or False
     */
    private boolean validateEntity(Device entity) {
        return entity != null && entity.getId() != null;
    }

    /**
     * Finds all entities saved onto the repository;
     *
     * @return Iterable.
     */
    @Override
    public Iterable<Device> findAll() {
        return DATA.values();
    }

    /**
     * Finds an entity by its ID;
     *
     * @param id IDVO
     * @return Entity
     */
    @Override
    public Device findById(DeviceIDVO id) {
        if (!isPresent(id)) {
            return null;
        } else {
            return DATA.get(id);
        }
    }

    /**
     * Verifies if an entity is present in the repository;
     *
     * @param id IDVO
     * @return True or False
     */
    @Override
    public boolean isPresent(DeviceIDVO id) {
        return DATA.containsKey(id);
    }

    /**
     * Finds all devices in a room by RoomIDVO
     *
     * @param roomID RoomIDVO
     * @return List of devices in the room
     */
    public List<Device> findByRoomID(RoomIDVO roomID) {
        return DATA.values().stream()
                .filter(device -> roomID.equals(device.getRoomID()))
                .collect(Collectors.toList());
    }
}