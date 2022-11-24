package es.capgemini.cca.canbemini.kanban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class KanbanTest {

    @Mock
    KanbanRepository kanbanRepository;

    @InjectMocks
    KanbanServiceImpl kanbanService;

    public static final Long EXISTS_KANBAN_ID = 1L;
    public static final Long NOT_EXISTS_KANBAN_ID = 9L;
    public static final String KANBAN_NAME = "KANBAN1";

    @Test
    public void findAllShouldReturnAllKanbans() {

        List<Kanban> list = new ArrayList<>();
        list.add(mock(Kanban.class));

        when(kanbanRepository.findAll()).thenReturn(list);

        List<Kanban> kanbans = kanbanService.findAll();

        assertNotNull(kanbans);
        assertEquals(1, kanbans.size());
    }

    @Test
    public void findExistsKanbanShouldReturnKanban() {
        Kanban kanban = mock(Kanban.class);
        when(kanban.getId()).thenReturn(EXISTS_KANBAN_ID);
        when(kanbanRepository.findById(EXISTS_KANBAN_ID)).thenReturn(Optional.of(kanban));

        Kanban kanbanResponse = kanbanService.getKanban(EXISTS_KANBAN_ID);

        assertNotNull(kanbanResponse);
        assertEquals(EXISTS_KANBAN_ID, kanban.getId());
    }

    @Test
    public void findNotExistsKanbanShouldReturnNull() {
        when(kanbanRepository.findById(NOT_EXISTS_KANBAN_ID)).thenReturn(Optional.empty());

        Kanban kanban = kanbanService.getKanban(NOT_EXISTS_KANBAN_ID);

        assertNull(kanban);
    }

    @Test
    public void deleteExistsIdShouldDeleteKanban() {
        this.kanbanService.deleteKanban(EXISTS_KANBAN_ID);

        verify(kanbanRepository).deleteById(EXISTS_KANBAN_ID);
    }

    @Test
    public void saveNotExistsKanbanIdShouldInsert() {

        KanbanDto kanbanDto = new KanbanDto();
        kanbanDto.setTitle(KANBAN_NAME);

        ArgumentCaptor<Kanban> kanban = ArgumentCaptor.forClass(Kanban.class);

        kanbanService.saveKanban(null, kanbanDto);

        verify(kanbanRepository).save(kanban.capture());

        assertEquals(KANBAN_NAME, kanban.getValue().getTitle());
    }

    @Test
    public void saveExistsKanbanIdShouldUpdate() {

        KanbanDto kanbanDto = new KanbanDto();
        kanbanDto.setTitle(KANBAN_NAME);

        Kanban kanban = mock(Kanban.class);

        when(kanbanRepository.findById(EXISTS_KANBAN_ID)).thenReturn(Optional.of(kanban));

        kanbanService.saveKanban(EXISTS_KANBAN_ID, kanbanDto);

        verify(kanbanRepository).save(kanban);
    }

}
