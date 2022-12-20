package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.KanbanMapper;
import es.capgemini.cca.canbemini.security.UserDetailsImpl;

//import com.devonfw.module.beanmapping.common.api.BeanMapper;

@RequestMapping(value = "/api/kanban")
@RestController
@CrossOrigin(origins = "*")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KanbanController {

    @Autowired
    KanbanService kanbanService;

    @Autowired
    KanbanMapper kanbanMapper;

    public KanbanController() {

    }

    // @GetMapping("/api/kanban/{id}")
    @RequestMapping(path = "/get/{kanbanId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId)")
    public KanbanDto getKanban(@PathVariable("kanbanId") Long kanbanId,
            @AuthenticationPrincipal UserDetailsImpl userId) {
        return kanbanMapper.KanbanToKanbanDto(kanbanService.getKanban(kanbanId));
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.verifyUser(#userId, #user)")
    public List<KanbanDto> getAllUserKanbans(@PathVariable("userId") Long userId,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return kanbanMapper.KanbanListToKanbaListDto(kanbanService.findUserKanbans(userId));
    }

    // @GetMapping("/api/kanban")
    @RequestMapping(path = "/{userId}/{kanbanId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId) || @kanbanServiceImpl.verifyUser(#userId, #user)")
    public List<Kanban> getUserKanban(@PathVariable("userId") @AuthenticationPrincipal Long userId,
            @PathVariable("kanbanId") Long kanbanId) {
        return kanbanService.findUserKanbanId(userId, kanbanId);
        // return kanbanMapper.map(kanbanService.findUserKanbans(userId));
    }

    @RequestMapping(path = { "/save/{userId}", "/save/{id}/{userId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@kanbanServiceImpl.verifyUser(#userId, #user)")
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody KanbanDto kanban,
            @PathVariable(name = "userId") Long userId) {
        kanbanService.saveKanban(id, kanban, userId);
    }
    /*
     * @RequestMapping(path = "/delete/{kanbanId}/{userId}", method =
     * RequestMethod.DELETE)
     * 
     * @PreAuthorize(
     * "@userKanbanPermissionService.isAuthorized('Owner',#kanbanId,#userId)")
     * public void delete(@PathVariable("kanbanId") Long
     * kanbanId, @PathVariable("userId") Long userId) {
     * kanbanService.deleteKanban(kanbanId); }
     */

    @RequestMapping(path = "/delete/{kanbanId}", method = RequestMethod.DELETE)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Owner',#kanbanId)")
    public void delete(@PathVariable("kanbanId") Long kanbanId) {
        kanbanService.deleteKanban(kanbanId);
    }

}