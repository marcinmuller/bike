package pl.mmuller.bicycles.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Station {
    @Id
    private String name;
    @JsonIgnore
    @OneToMany(
            targetEntity = Rack.class,
            mappedBy = "station",
            cascade = CascadeType.ALL
    )
    private Set<Rack> rackSet;
    @JsonIgnore
    @OneToMany(
            targetEntity = Bike.class,
            mappedBy = "station",
            cascade = CascadeType.ALL
    )
    private Set<Bike> bikeSet;
    private int freeRackQuantity;
    private int occupiedRackQuantity;
    private int bikeQuantity;
}
