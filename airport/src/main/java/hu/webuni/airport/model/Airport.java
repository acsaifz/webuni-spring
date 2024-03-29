package hu.webuni.airport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Airport.countByIata", query = "SELECT COUNT(a.id) FROM Airport a WHERE a.iata = :iata AND a.id != :id")
public class Airport {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String iata;

    public Airport() {
    }

    public Airport(String name, String iata){
        this.name = name;
        this.iata = iata;
    }

    public Airport(long id, String name, String iata) {
        this(name, iata);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }
}
