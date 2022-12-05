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
        return swimlaneMapper.map(swimlaneService.findAll(kanbanId));
    }

    @RequestMapping(path = { "", "/{id}/{kanbanId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = true) Long id,
            @PathVariable(name = "kanbanId", required = true) Long kanbanId, @RequestBody SwimlaneDto swimlane) {
        swimlaneService.saveSwimlane(id, kanbanId, swimlane);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        swimlaneService.deleteSwimlane(id);
    }
}
