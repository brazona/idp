package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.business.SessionDTO;
import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import br.brazona.idp.api.infrastructure.repositories.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    private final static String SERVICE_LOG = "Service started SessionService: {}";
    Logger logger = LoggerFactory.getLogger(SessionService.class);

    public void save(SessionDTO sessionDTO){
        logger.info(SERVICE_LOG, "save");

        SessionDTO sessionCurrent = getByUserId(sessionDTO.getUser_id());
        if (sessionCurrent != null){
            sessionDTO.setId(sessionCurrent.getId());
        }

        sessionRepository.save(new SessionEntity(sessionDTO));
        logger.info("Stored session");
    }

    public SessionDTO getByUserId(Long id){
        SessionEntity session = sessionRepository.findByUserId(id);
        if (session == null){
            logger.info("Session not found");
            return null;
        }
        return new SessionDTO(session);
    }
}
