package pl.mmuller.bicycles.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mmuller.bicycles.model.Bike;

public interface BikeDao extends CrudRepository<Bike, Long> {
}
