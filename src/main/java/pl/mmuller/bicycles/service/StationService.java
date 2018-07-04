package pl.mmuller.bicycles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mmuller.bicycles.dao.BikeDao;
import pl.mmuller.bicycles.dao.RackDao;
import pl.mmuller.bicycles.dao.StationDao;
import pl.mmuller.bicycles.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StationService {
    @Autowired
    StationDao stationDao;
    @Autowired
    BikeDao bikeDao;
    @Autowired
    RackDao rackDao;

    public Bike borrowBike(String stationName) throws Exception{
        Station station=stationDao.findById(stationName).orElseThrow(()->new Exception("no such station"));
        Bike bike = station.getBikeSet().stream()
                .findFirst()
                .orElseThrow(()->new Exception("no free bike"));
        Rack rack = rackDao.findByBike(bike);
        rack.setFree(true);
        rack.setBike(null);
        if(station.getRackSet().contains(rack)){
            station.getRackSet().remove(rack);
            station.setFreeRackQuantity(station.getFreeRackQuantity()+1);
            station.setOccupiedRackQuantity(station.getOccupiedRackQuantity()-1);
        }
        station.setBikeQuantity(station.getBikeQuantity()-1);
        bike.setStation(null);
        bikeDao.save(bike);
        return bike;
    }

    public void returnBike(BikeStationDto bikeStationDto) throws Exception{
        Station station=stationDao.findById(bikeStationDto.getStationName()).orElseThrow(()->new Exception("no such station"));
        Rack rack = station.getRackSet().stream()
                .filter(r->r.isFree())
                .findFirst()
                .orElse(null);
        Bike bike = bikeDao.findById(bikeStationDto.getBikeId()).orElseThrow(()->new Exception("no such bike"));
        if(rack!=null) {
            rack.setBike(bike);
            rack.setFree(false);
            station.setFreeRackQuantity(station.getFreeRackQuantity()-1);
            station.setOccupiedRackQuantity(station.getOccupiedRackQuantity()+1);
        }
        station.getBikeSet().add(bike);
        station.setBikeQuantity(station.getBikeQuantity()+1);
        bike.setStation(station);
        stationDao.save(station);
    }

    public Station addStation(StationDto stationDto) throws Exception{
        Station station=new Station();
        station.setName(stationDto.getName());
        station.setRackSet(new HashSet<>());
        for (int i=0;i<stationDto.getRackQuantity();i++){
            station.getRackSet().add(new Rack(true,station));
        }
        station.setBikeSet(new HashSet<>());
        station.setFreeRackQuantity(stationDto.getRackQuantity());
        station.setOccupiedRackQuantity(0);
        station.setBikeQuantity(0);
        return stationDao.save(station);
    }

    public void addNewBikeToStation(String stationName) throws Exception{
        Station station=stationDao.findById(stationName).orElseThrow(()->new Exception("no such station"));
        Rack rack = station.getRackSet().stream()
                .filter(r->r.isFree())
                .findFirst()
                .orElse(null);
        Bike bike= new Bike();
        if(rack!=null) {
            rack.setBike(bike);
            rack.setFree(false);
            station.setFreeRackQuantity(station.getFreeRackQuantity()-1);
            station.setOccupiedRackQuantity(station.getOccupiedRackQuantity()+1);
        }
        station.getBikeSet().add(bike);
        station.setBikeQuantity(station.getBikeQuantity()+1);
        bike.setStation(station);
        stationDao.save(station);
    }

    public void delete(String stationName) throws Exception{
        Station station=stationDao.findById(stationName).orElseThrow(()->new Exception("no such station"));
        stationDao.delete(station);
    }


    public List<Station> allStation() {
        List<Station> list = new ArrayList<>();
        stationDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

}
