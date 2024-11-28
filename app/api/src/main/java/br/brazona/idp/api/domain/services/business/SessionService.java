package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.SessionDTO;
import br.brazona.idp.api.domain.views.business.SessionVO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.infrastructure.entities.SessionEntity;
import br.brazona.idp.api.infrastructure.repositories.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
 * Business rule service: [ SessionService ], which implements functionalities related to: [ Users Sessions ] .
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Slf4j
@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionDTO sessionDTO;
    @Autowired
    private UserService userService;
    private final static String SERVICE_LOG = "Service started SessionService: {}";
    /**
     *
     * Method constructor.
     *
     **/
    public SessionService() {
    }

    /**
     *
     * Method that record the object with session user data.
     *
     * @param sessionVO object with the data to create or update record.
     *
     **/
    public void createUpdate(SessionVO sessionVO) {
        UserDetailsVO userVO = userService.getByUsername(sessionVO.getUsername());
        SessionVO sessionCurrent = getByUserId(userVO.getId());
        if (sessionCurrent != null){
            sessionCurrent.setAccess_token(sessionVO.getAccess_token());
            sessionRepository.save(sessionDTO.toEntityByID(sessionCurrent));
        } else {
            sessionVO.setUser_id(userVO.getId());
            SessionEntity sessionEntity = sessionDTO.toEntity(sessionVO);
            try {
                sessionRepository.save(sessionEntity);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        log.info("Stored session");
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param id Object of the User class, with the application's authentication values.
     * @return SessionVO view object witch data
     * 
     **/
    public SessionVO getByUserId(Long id) {
        SessionEntity session = sessionRepository.findByUserId(id);
        if (session == null){
            log.info("Session not found");
            return null;
        }
        return sessionDTO.toVO(session);
    }
}
