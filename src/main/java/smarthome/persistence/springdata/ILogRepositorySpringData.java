package smarthome.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.datamodel.LogDataModel;

public interface ILogRepositorySpringData extends JpaRepository<LogDataModel, String> {


}
