package mariapiabaldoin.progetto_settimana_18.repositories;


import mariapiabaldoin.progetto_settimana_18.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {

}