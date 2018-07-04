package pl.mmuller.bicycles.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rack {
    @Id
    @GeneratedValue
    private long id;
    private boolean isFree;
    @ManyToOne
    @JoinColumn(name = "station")
    private Station station;

    @OneToOne
    private Bike bike;
    public Rack(boolean isFree, Station station) {
        this.isFree = isFree;
        this.station = station;
    }
}
