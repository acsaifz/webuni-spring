package hu.webuni.airport.dto;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AirportDto {
    private long id;
    @Size(min = 3, max = 20)
    private String name;
    private String iata;

    public AirportDto() {
    }

    public AirportDto(String name, String iata){
        this.name = name;
        this.iata = iata;
    }

    public AirportDto(long id, String name, String iata) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportDto that = (AirportDto) o;
        return name.equals(that.name) && iata.equals(that.iata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iata);
    }
}
