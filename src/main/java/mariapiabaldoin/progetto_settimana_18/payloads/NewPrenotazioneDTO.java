package mariapiabaldoin.progetto_settimana_18.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;


public record NewPrenotazioneDTO(
        @NotEmpty(message = "Le note sono obbligatorie!")
        @Size(min = 2, max = 40, message = "Le note devono essere comprese tra 2 e 40 caratteri!")
        String note,
        @NotEmpty(message = "Il dipendente è obbligatorio!")
        UUID dipendenteID,
        @NotEmpty(message = "Il viaggio è obbligatorio!")
        UUID viaggioId) {
}



