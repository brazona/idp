package br.brazona.idp.api.services.business;

import br.brazona.idp.api.core.dtos.business.SessionDTO;
import br.brazona.idp.api.core.dtos.business.UserDTO;
import br.brazona.idp.api.core.dtos.business.UserDetailsDTO;
import br.brazona.idp.api.core.exception.UserNotFoundException;
import br.brazona.idp.api.persistence.entities.SessionEntity;
import br.brazona.idp.api.persistence.entities.UsersEntity;
import br.brazona.idp.api.persistence.repositories.SessionRepository;
import br.brazona.idp.api.persistence.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void save(SessionDTO sessionDTO){
        SessionDTO sessionCurrent = getByUserId(sessionDTO.getUser_id());
        if (sessionCurrent != null){
            sessionDTO.setId(sessionCurrent.getId());
        }
        sessionRepository.save(new SessionEntity(sessionDTO));
    }

    public SessionDTO getByUserId(Long id){
        SessionEntity session = sessionRepository.findByUserId(id);
        if (session == null){
            return null;
        }
        return new SessionDTO(session);
    }
}
