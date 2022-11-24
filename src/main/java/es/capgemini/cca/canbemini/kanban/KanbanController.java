package es.capgemini.cca.canbemini.kanban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.devonfw.module.beanmapping.common.api.BeanMapper;

@RequestMapping(value = "/kanban")
@RestController
@CrossOrigin(origins = "*")
public class KanbanController {

    @Autowired
    KanbanRepository repository;

    @Autowired
    // BeanMapper beanMapper;

    // @GetMapping("/api/kanban/{id}")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public Kanban getKanban(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @GetMapping("/api/kanban")
    public Iterable<Kanban> getAllKanbans() {

        return repository.findAll();
    }
}