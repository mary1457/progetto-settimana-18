package mariapiabaldoin.progetto_settimana_18.repositories;


import mariapiabaldoin.progetto_settimana_18.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {
    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByUsername(String username);
}