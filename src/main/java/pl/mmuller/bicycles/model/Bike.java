package pl.mmuller.bicycles.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Bike {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "station")
    private Station station;
}
