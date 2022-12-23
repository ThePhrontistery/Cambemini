package es.capgemini.cca.canbemini.kanban;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.KanbanMapper;
import es.capgemini.cca.canbemini.mapppers.UserKanbanPermissionMapper;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionDto;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionService;

//import com.devonfw.module.beanmapping.common.api.BeanMapper;

@RequestMapping(value = "/api/kanban")
@RestController
@CrossOrigin(origins = "*")

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

    // @GetMapping("/api/kanban/{id}")
    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public KanbanDto getKanban(@PathVariable("id") Long id) {
        return kanbanMapper.KanbanToKanbanDto(kanbanService.getKanban(id));
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public List<KanbanDto> getAllUserKanbans(@PathVariable("userId") Long userId) {
        // return kanbanService.findUserKanbans(userId);
        return kanbanMapper.KanbanListToKanbaListDto(kanbanService.findUserKanbans(userId));
    }

    // @GetMapping("/api/kanban")
    @RequestMapping(path = "/{userId}/{kanbanId}", method = RequestMethod.GET)
    public KanbanDto getAllUserKanbans(@PathVariable("userId") Long userId, @PathVariable("kanbanId") Long kanbanId) {
        Optional<Kanban> opt = kanbanService.findUserKanbanId(userId, kanbanId).stream().findFirst();
        if (opt.isPresent())
            return kanbanMapper.KanbanToKanbanDto(opt.get());
        return null;
    }

    @RequestMapping(path = "/code/{code}", method = RequestMethod.GET)
    public KanbanDto getKanbanIdByCode(@PathVariable("code") String code) {
        return kanbanMapper.KanbanToKanbanDto(kanbanService.getByCode(code));
    }

    @RequestMapping(path = { "/save/{userId}", "/save/{id}/{userId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody KanbanDto kanban,
            @PathVariable(name = "userId") Long userId) {
        kanbanService.saveKanban(id, kanban, userId);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission(#id, 'OWNER')")
    public void delete(@PathVariable("id") Long id) {
        kanbanService.deleteKanban(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UserKanbanPermissionDto> getAllUserKanbanPermission() {
        return userKanbanPermissionMapper
                .userKanbanPermissionListToUserKanbanPermissionListDto(this.userKanbanPermisssionService.get());
    }
}