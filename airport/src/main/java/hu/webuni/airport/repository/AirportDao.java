package hu.webuni.airport.repository;

import hu.webuni.airport.model.Airport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AirportDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Airport save(Airport airport){
        em.persist(airport);
        return airport;
    }

    public boolean existsIata(String iata, long airportId){
        long count = em.createNamedQuery("Airport.countByIata", Long.class)
                .setParameter("iata", iata)
                .setParameter("id", airportId)
                .getSingleResult();
        return count > 0L;
    }

    public List<Airport> findAll(){
        String sql = "SELECT a FROM Airport a";
        return em.createQuery(sql,Airport.class)
                .getResultList();
    }

    public Airport findById(long id) throws NoResultException {
        String sql = "SELECT a FROM Airport a WHERE a.id = :id";
        return em.createQuery(sql, Airport.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void delete(long id){
        em.remove(findById(id));
    }

    @Transactional
    public Airport update(Airport airport){
        return em.merge(airport);
    }


}
