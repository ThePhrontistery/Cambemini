package es.capgemini.cca.canbemini.kanban;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
=======
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
>>>>>>> origin/future
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.KanbanMapper;
import es.capgemini.cca.canbemini.mapppers.UserKanbanPermissionMapper;
import es.capgemini.cca.canbemini.security.UserDetailsImpl;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionService;

@RequestMapping(value = "/api/kanban")
@RestController
<<<<<<< HEAD
@CrossOrigin(origins = "*")

=======
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
>>>>>>> origin/future
public class KanbanController {

    @Autowired
    KanbanService kanbanService;

    @Autowired
    KanbanMapper kanbanMapper;

    @Autowired
    UserKanbanPermissionMapper userKanbanPermissionMapper;

    @Autowired
    UserKanbanPermissionService userKanbanPermisssionService;

    public KanbanController() {

    }

    @RequestMapping(path = "/get/{kanbanId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId)")
    public KanbanDto getKanban(@PathVariable("kanbanId") Long kanbanId) {
        return kanbanMapper.KanbanToKanbanDto(kanbanService.getKanban(kanbanId));
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<KanbanDto> getAllUserKanbans() {
        return kanbanMapper.KanbanListToKanbaListDto(kanbanService.findUserKanbans());
    }

    @RequestMapping(path = "/{userId}/{kanbanId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId) || @kanbanServiceImpl.verifyUser(#userId, #user)")
    public KanbanDto getUserKanban(@PathVariable("userId") Long userId, @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("kanbanId") Long kanbanId) {
        Optional<Kanban> opt = kanbanService.findUserKanbanId(userId, kanbanId).stream().findFirst();
        if (opt.isPresent())
            return kanbanMapper.KanbanToKanbanDto(opt.get());
        return null;
    }

    @RequestMapping(path = "/code/{code}", method = RequestMethod.GET)
    public KanbanDto getKanbanIdByCode(@PathVariable("code") String code) {
        return kanbanMapper.KanbanToKanbanDto(kanbanService.getByCode(code));
    }

    @RequestMapping(path = { "/save/{userId}", "/save/{kanbanId}/{userId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@kanbanServiceImpl.verifyUser(#userId, #user) || @kanbanServiceImpl.verifyUser(#userId, #user) && @kanbanServiceImpl.isAuthorized('Editor',#kanbanId)")
    public void save(@PathVariable(name = "kanbanId", required = false) Long kanbanId, @RequestBody KanbanDto kanban,
            @PathVariable(name = "userId") Long userId, @AuthenticationPrincipal UserDetailsImpl user) {
        kanbanService.saveKanban(kanbanId, kanban, userId);
    }

<<<<<<< HEAD
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission(#id, 'OWNER')")
    public void delete(@PathVariable("id") Long id) {
        kanbanService.deleteKanban(id);
=======
    @RequestMapping(path = "/delete/{kanbanId}", method = RequestMethod.DELETE)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Owner',#kanbanId)")
    public void delete(@PathVariable("kanbanId") Long kanbanId) {
        kanbanService.deleteKanban(kanbanId);
>>>>>>> origin/future
    }

}