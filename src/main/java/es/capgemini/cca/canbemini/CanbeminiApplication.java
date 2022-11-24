package es.capgemini.cca.canbemini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanRepository;

@SpringBootApplication
public class CanbeminiApplication {

    private static final Logger log = LoggerFactory.getLogger(CanbeminiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CanbeminiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(KanbanRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Kanban("My first Kanban"));
            repository.save(new Kanban("A second Kanban"));

            // fetch all Kanbans
            log.info("Kanbans found with findAll():");
            log.info("-------------------------------");
            for (Kanban kanban : repository.findAll()) {
                log.info(kanban.toString());
            }
            log.info("");

            // fetch an individual Kanban by ID
            Kanban kanban = repository.findById(1L).orElse(null);
            log.info("Kanban found with findById(1L):");
            log.info("--------------------------------");
            log.info(kanban.toString());
            log.info("");

        };
    }
}
