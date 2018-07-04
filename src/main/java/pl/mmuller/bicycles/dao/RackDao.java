package pl.mmuller.bicycles.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mmuller.bicycles.model.Bike;
import pl.mmuller.bicycles.model.Rack;

public interface RackDao extends CrudRepository<Rack, Long> {
    Rack findByBike(Bike bike);
}
