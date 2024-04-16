package SmartHomeDDD.JPATest;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

public class RoomRepositoryJPAImpl {
    RoomFactory factory;

    public RoomRepositoryJPAImpl(RoomFactory factory) {
        this.factory = factory;
    }

    /*
    private EntityManager getEntityManager()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("smarthomeDDD ");
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

     */

    public Room save(Room room)
    {
        if (room == null) {
            throw new IllegalArgumentException();
        }

        RoomDataModel roomDataModel = new RoomDataModel(room);

        /*
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(roomDataModel);
        tx.commit();
        em.close();
        */
        return room;
    }

    public Iterable<Room> findAll()
    {
        /*
        Query query = getEntityManager().createQuery(
                "SELECT e FROM RoomDataModel e");

        List<RoomDataModel> listDataModel = query.getResultList();

        Iterable<Room> listDomain = RoomDataModel.toDomain(factory, listDataModel);

        return listDomain;
        */
        return null;
    }

    public Room ofIdentity(RoomIDVO id)
    {
        /*
        RoomDataModel roomDataModel = getEntityManager().find(RoomDataModel.class, id);

        if(roomDataModel != null)
        {
            Room roomDomain = RoomDataModel.toDomain(factory, roomDataModel);
            return roomDomain;
        }
        else
            return null;
        */
        return null;
    }

    public boolean containsOfIdentity(RoomIDVO id)
    {
        /*
        RoomDataModel roomDataModel = getEntityManager().find(RoomDataModel.class, id);

        if(roomDataModel != null)
        {
            return true;
        }
        else
            return false;
        */
        return false;
    }
}
