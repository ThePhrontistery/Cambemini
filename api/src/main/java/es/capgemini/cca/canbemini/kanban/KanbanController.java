package es.capgemini.cca.canbemini.kanban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KanbanController {

    private static final String template = "Hello, %s!";


    @Autowired
    KanbanRepository repository;

    @GetMapping("/api/kanban")
    public Iterable<Kanban> kanban() {


        return repository.findAll();
    }
}