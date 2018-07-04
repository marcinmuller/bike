package pl.mmuller.bicycles.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mmuller.bicycles.model.Station;

public interface StationDao extends CrudRepository<Station, String> {
}
