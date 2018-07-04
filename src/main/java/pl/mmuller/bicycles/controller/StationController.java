package pl.mmuller.bicycles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mmuller.bicycles.model.Bike;
import pl.mmuller.bicycles.model.BikeStationDto;
import pl.mmuller.bicycles.model.Station;
import pl.mmuller.bicycles.model.StationDto;
import pl.mmuller.bicycles.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {
    @Autowired
    StationService stationService;

    @GetMapping("/borrowBike/{stationName}")
    public Bike borrowBike(@PathVariable("stationName") String stationName) throws Exception{
        return stationService.borrowBike(stationName);
    }

    @PostMapping("/returnBike")
    public void returnBike(@RequestBody BikeStationDto bikeStationDto) throws Exception{
        stationService.returnBike(bikeStationDto);
    }

    @PostMapping("/addStation")
    public Station addStation(@RequestBody StationDto stationDto) throws Exception{
        return stationService.addStation(stationDto);
    }

    @GetMapping("/addBike/{stationName}")
    public void addBike(@PathVariable("stationName") String stationName) throws Exception{
        stationService.addNewBikeToStation(stationName);
    }

    @DeleteMapping("/delete/{stationName}")
    public void softDeleteUser(@PathVariable("stationName") String stationName) throws Exception{
        stationService.delete(stationName);
    }

    @GetMapping("/showStations")
    public List<Station> showStations(){
        return stationService.allStation();
    }
}
