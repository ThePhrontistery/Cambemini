package es.capgemini.cca.canbemini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.capgemini.cca.canbemini.kanban.Kanban;
import es.capgemini.cca.canbemini.kanban.KanbanRepository;
<<<<<<< HEAD
=======
import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneRepository;
import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteRepository;
import es.capgemini.cca.canbemini.permission.Permission;
import es.capgemini.cca.canbemini.permission.PermissionRepository;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermissionRepository;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersRepository;
>>>>>>> 4fd6d58e4a13c1e722203266e81c67684af98c1a

@SpringBootApplication
public class CanbeminiApplication {

    private static final Logger log = LoggerFactory.getLogger(CanbeminiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CanbeminiApplication.class, args);
    }

    @Bean
<<<<<<< HEAD
    public CommandLineRunner demo(KanbanRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Kanban("My first Kanban"));
            repository.save(new Kanban("A second Kanban"));
=======
    public CommandLineRunner demo(KanbanRepository kanbanRepository, SwimlaneRepository swimlaneRepository,
            NoteRepository noteRepository, UsersRepository usersRepository, PermissionRepository permissionRepository,
            UserKanbanPermissionRepository ukpRepository) {
        return (args) -> {
            // save a few customers
            Users user1 = new Users("cesar@email.com");
            Users user2 = new Users("mercedes@email.com");
            Users user3 = new Users("jacques@email.com");
            Users user4 = new Users("raul@email.com");
            Users user5 = new Users("fredy@email.com");

            usersRepository.save(user1);
            usersRepository.save(user2);
            usersRepository.save(user3);
            usersRepository.save(user4);
            usersRepository.save(user5);

            // save a few Kanbans
            Kanban kanban1 = new Kanban("My first Kanban", "First Kanban Description");
            Kanban kanban2 = new Kanban("A second Kanban", "Second Kanban Description");
            kanbanRepository.save(kanban1);
            kanbanRepository.save(kanban2);

            // save a few Swimlanes
            Swimlane swimlane1 = new Swimlane("TO DO", kanban1);
            Swimlane swimlane2 = new Swimlane("TO PROGRESS", kanban1);
            Swimlane swimlane3 = new Swimlane("DONE", kanban1);
            Swimlane swimlane4 = new Swimlane("TO DO", kanban2);
            Swimlane swimlane5 = new Swimlane("TO PROGRESS", kanban2);
            Swimlane swimlane6 = new Swimlane("DONE", kanban2);
            swimlaneRepository.save(swimlane1);
            swimlaneRepository.save(swimlane2);
            swimlaneRepository.save(swimlane3);
            swimlaneRepository.save(swimlane4);
            swimlaneRepository.save(swimlane5);
            swimlaneRepository.save(swimlane6);

            // save a few Notes
            Note note1 = new Note("content1", swimlane1);
            Note note2 = new Note("content2", swimlane1);
            Note note3 = new Note("content3", swimlane2);
            Note note4 = new Note("content4", swimlane3);
            Note note5 = new Note("content5", swimlane3);
            Note note6 = new Note("content6", swimlane4);
            Note note7 = new Note("content7", swimlane4);
            Note note8 = new Note("content8", swimlane4);
            Note note9 = new Note("content9", swimlane5);
            Note note10 = new Note("content10", swimlane5);
            noteRepository.save(note1);
            noteRepository.save(note2);
            noteRepository.save(note3);
            noteRepository.save(note4);
            noteRepository.save(note5);
            noteRepository.save(note6);
            noteRepository.save(note7);
            noteRepository.save(note8);
            noteRepository.save(note9);
            noteRepository.save(note10);

            // save a few permissions
            Permission permission1 = new Permission("Owner");
            Permission permission2 = new Permission("Editor");
            Permission permission3 = new Permission("Collaborator");
            permissionRepository.save(permission1);
            permissionRepository.save(permission2);
            permissionRepository.save(permission3);

            // save user_kanban_permission table data
            UserKanbanPermission ukp1 = new UserKanbanPermission(user1, kanban1, permission1);
            UserKanbanPermission ukp2 = new UserKanbanPermission(user2, kanban1, permission2);
            UserKanbanPermission ukp3 = new UserKanbanPermission(user3, kanban1, permission3);
            UserKanbanPermission ukp4 = new UserKanbanPermission(user4, kanban2, permission1);
            UserKanbanPermission ukp5 = new UserKanbanPermission(user5, kanban2, permission2);
            UserKanbanPermission ukp6 = new UserKanbanPermission(user2, kanban2, permission3);
            ukpRepository.save(ukp1);
            ukpRepository.save(ukp2);
            ukpRepository.save(ukp3);
            ukpRepository.save(ukp4);
            ukpRepository.save(ukp5);
            ukpRepository.save(ukp6);
>>>>>>> 4fd6d58e4a13c1e722203266e81c67684af98c1a

            // fetch all Kanbans
            log.info("Kanbans found with findAll():");
            log.info("-------------------------------");
<<<<<<< HEAD
            for (Kanban kanban : repository.findAll()) {
=======
            for (Kanban kanban : kanbanRepository.findAll()) {
>>>>>>> 4fd6d58e4a13c1e722203266e81c67684af98c1a
                log.info(kanban.toString());
            }
            log.info("");

            // fetch an individual Kanban by ID
<<<<<<< HEAD
            Kanban kanban = repository.findById(1L).orElse(null);
            log.info("Kanban found with findById(1L):");
            log.info("--------------------------------");
            log.info(kanban.toString());
=======
            Kanban kanban = kanbanRepository.findById(1L).orElse(null);
            log.info("Kanban found with findById(1L):");
            log.info("--------------------------------");
            // log.info(kanban.toString());
>>>>>>> 4fd6d58e4a13c1e722203266e81c67684af98c1a
            log.info("");

        };
    }
}
