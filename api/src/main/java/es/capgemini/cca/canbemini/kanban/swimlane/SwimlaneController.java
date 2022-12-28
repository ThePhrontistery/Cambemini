package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.SwimlaneMapper;

@RequestMapping(value = "/api/kanban/swimlane")
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@EnableWebSecurity
public class SwimlaneController {
    @Autowired
    SwimlaneService swimlaneService;

    @Autowired
    SwimlaneMapper swimlaneMapper;

    public SwimlaneController() {

    }

    @RequestMapping(path = "/get/{swimlaneId}", method = RequestMethod.GET)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Collaborator',#swimlaneId)")
    public SwimlaneDto getSwimlane(@PathVariable("swimlaneId") Long swimlaneId) {
        return swimlaneMapper.SwimlaneToSwimlaneDto(swimlaneService.findSwimlane(swimlaneId));
    }

    @RequestMapping(path = "/{kanbanId}", method = RequestMethod.GET)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId)")
    public List<SwimlaneDto> getAllKanbanSwimlanes(@PathVariable("kanbanId") Long kanbanId) {
        return swimlaneMapper.swimlaneListToSwimlaneListDto(swimlaneService.findAll(kanbanId));
    }

    @RequestMapping(path = { "/save/{kanbanId}", "/save/{kanbanId}/{swimlaneId}" }, method = RequestMethod.PUT)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Editor',#kanbanId)")
    public void save(@PathVariable(name = "swimlaneId", required = false) Long swimlaneId,
            @RequestBody SwimlaneDto swimlane, @PathVariable(name = "kanbanId", required = true) Long kanbanId) {
        swimlaneService.saveSwimlane(swimlaneId, swimlane, kanbanId);
    }

    @RequestMapping(path = "/{swimlaneId}", method = RequestMethod.DELETE)
    @PreAuthorize("@swimlaneServiceImpl.isAuthorized('Editor',#swimlaneId)")
    public void delete(@PathVariable("swimlaneId") Long swimlaneId) {
        swimlaneService.deleteSwimlane(swimlaneId);
    }

    @RequestMapping(path = "/updateLanesOrder/{kanbanId}", method = RequestMethod.PUT)
    @PreAuthorize("@kanbanServiceImpl.isAuthorized('Collaborator',#kanbanId)")
    public void updateSwimlanesOrder(@RequestBody List<SwimlaneDto> swimlanes,
            @PathVariable("kanbanId") Long kanbanId) {
        for (int i = 0; i < swimlanes.size(); i++)
            swimlaneService.saveSwimlane(swimlanes.get(i).getId(), swimlanes.get(i), kanbanId);
    }
}
