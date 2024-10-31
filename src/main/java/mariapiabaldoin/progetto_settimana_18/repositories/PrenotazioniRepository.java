package mariapiabaldoin.progetto_settimana_18.repositories;


import mariapiabaldoin.progetto_settimana_18.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, Long> {

}