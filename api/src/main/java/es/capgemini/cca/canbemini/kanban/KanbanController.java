package es.capgemini.cca.canbemini.kanban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.KanbanMapper;

//import com.devonfw.module.beanmapping.common.api.BeanMapper;

@RequestMapping(value = "/api/kanban")
@RestController
@CrossOrigin(origins = "*")
public class KanbanController {

    @Autowired
    KanbanService kanbanService;

    @Autowired
    KanbanMapper kanbanMapper;

    public KanbanController() {

    }

    // @GetMapping("/api/kanban/{id}")
    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public KanbanDto getKanban(@PathVariable("id") Long id) {
        return kanbanMapper.KanbanToKanbanDto(kanbanService.getKanban(id));
    }

    // @GetMapping("/api/kanban")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<KanbanDto> getAllKanbans() {
        return kanbanMapper.map(kanbanService.findAll());
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody KanbanDto kanban) {
        kanbanService.saveKanban(id, kanban);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        kanbanService.deleteKanban(id);
    }

}