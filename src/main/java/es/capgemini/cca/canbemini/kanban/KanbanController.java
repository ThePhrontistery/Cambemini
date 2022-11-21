package es.capgemini.cca.canbemini.kanban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KanbanController {

    @Autowired
    KanbanRepository repository;

    @GetMapping("/api/kanban/{id}")
    public Kanban getKanban(@PathVariable long id){

        return repository.findById(id);
    }

    @GetMapping("/api/kanban")
    public Iterable<Kanban> getAllKanbans() {


        return repository.findAll();
    }
}