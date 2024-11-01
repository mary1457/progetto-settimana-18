package mariapiabaldoin.progetto_settimana_18.repositories;


import mariapiabaldoin.progetto_settimana_18.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, Long> {
    @Query("SELECT p FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.viaggio.data = :data")
    List<Prenotazione> findByDipendenteIdAndViaggioData(UUID dipendenteId, LocalDate data);


}