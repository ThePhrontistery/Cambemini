package es.capgemini.cca.canbemini.kanban.swimlane;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.SwimlaneMapper;

@RequestMapping(value = "/api/kanban/swimlane")
@RestController
@CrossOrigin(origins = "*")
public class SwimlaneController {
    @Autowired
    SwimlaneService swimlaneService;

    @Autowired
    SwimlaneMapper swimlaneMapper;

    public SwimlaneController() {

    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public SwimlaneDto getSwimlane(@PathVariable("id") Long id) {
        return swimlaneMapper.SwimlaneToSwimlaneDto(swimlaneService.findSwimlane(id));
    }

    @RequestMapping(path = "/{kanbanId}", method = RequestMethod.GET)
    public List<SwimlaneDto> getAllKanbanSwimlanes(@PathVariable("kanbanId") Long kanbanId) {
        return swimlaneMapper.swimlaneListToSwimlaneListDto(swimlaneService.findAll(kanbanId));
    }

    @RequestMapping(path = { "/save/{kanbanId}", "/save/{kanbanId}/{swimlaneId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "swimlaneId", required = false) Long swimlaneId,
            @RequestBody SwimlaneDto swimlane, @PathVariable(name = "kanbanId", required = true) Long kanbanId) {
        swimlaneService.saveSwimlane(swimlaneId, swimlane, kanbanId);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        swimlaneService.deleteSwimlane(id);
    }

    @RequestMapping(path = "/updateLanesOrder/{kanbanId}", method = RequestMethod.PUT)
    public void updateSwimlanesOrder(@RequestBody List<SwimlaneDto> swimlanes,
            @PathVariable("kanbanId") Long kanbanId) {
        for (int i = 0; i < swimlanes.size(); i++)
            swimlaneService.saveSwimlane(swimlanes.get(i).getId(), swimlanes.get(i), kanbanId);
    }
}
