package es.capgemini.cca.canbemini.websockect;

import es.capgemini.cca.canbemini.kanban.swimlane.note.Note;
import es.capgemini.cca.canbemini.kanban.swimlane.note.NoteDto;
import es.capgemini.cca.canbemini.websockect.model.NoteBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSockectController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /*@SendTo("/topic/2")//to = nome canale
    @MessageMapping("/note/block")
    public NoteDto sendMessage( NoteDto noteDto) {
        return  noteDto;
    }*/

    @MessageMapping("/kanbans/{kanbanId}")
    public void simple(@DestinationVariable String kanbanId, NoteBlock note) {
        simpMessagingTemplate.convertAndSend("/kanbans/" + kanbanId, note );
    }
}