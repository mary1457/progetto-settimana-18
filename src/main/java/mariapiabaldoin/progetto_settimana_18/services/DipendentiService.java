package mariapiabaldoin.progetto_settimana_18.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mariapiabaldoin.progetto_settimana_18.entities.Dipendente;
import mariapiabaldoin.progetto_settimana_18.exceptions.BadRequestException;
import mariapiabaldoin.progetto_settimana_18.exceptions.NotFoundException;
import mariapiabaldoin.progetto_settimana_18.payloads.NewDipendenteDTO;
import mariapiabaldoin.progetto_settimana_18.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class DipendentiService {

    @Autowired
    private DipendentiRepository dipendentiRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Dipendente save(NewDipendenteDTO body) {

        this.dipendentiRepository.findByEmail(body.email()).ifPresent(

                dipendente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );

        this.dipendentiRepository.findByUsername(body.username()).ifPresent(

                dipendente -> {
                    throw new BadRequestException("Username " + body.username() + " già in uso!");
                }
        );


        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());


        return this.dipendentiRepository.save(newDipendente);
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.dipendentiRepository.findAll(pageable);
    }

    public Dipendente findById(UUID dipendenteId) {
        return this.dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    public Dipendente findByIdAndUpdate(UUID dipendenteId, NewDipendenteDTO body) {

        Dipendente found = this.findById(dipendenteId);


        if (!found.getEmail().equals(body.email())) {
            this.dipendentiRepository.findByEmail(body.email()).ifPresent(

                    dipendente -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }

        if (!found.getUsername().equals(body.username())) {
            this.dipendentiRepository.findByUsername(body.username()).ifPresent(

                    dipendente -> {
                        throw new BadRequestException("Username " + body.username() + " già in uso!");
                    }
            );
        }

        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());


        return this.dipendentiRepository.save(found);
    }

    public void findByIdAndDelete(UUID userId) {
        Dipendente found = this.findById(userId);
        this.dipendentiRepository.delete(found);
    }

    public Dipendente uploadAvatar(MultipartFile file, UUID dipendenteId) {

        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }

        Dipendente found = this.findById(dipendenteId);
        found.setAvatarURL(url);
        return this.dipendentiRepository.save(found);


    }
}
