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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import es.capgemini.cca.canbemini.users.UsersServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class KanbanTest {

    @Mock
    KanbanRepository kanbanRepository;

    @InjectMocks
    KanbanServiceImpl kanbanService;

    @InjectMocks
    UsersServiceImpl userService;

    public static final Long EXISTS_KANBAN_ID = 1L;
    public static final Long NOT_EXISTS_KANBAN_ID = 9L;
    public static final Long EXISTS_USER_ID = 1L;

    @Mock
    private Authentication auth;

    @BeforeEach
    public void initSecurityContext() {
        when(auth.getPrincipal()).thenReturn("cesar@email.com");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void findAllShouldReturnAllUserKanbans() {

        List<Kanban> list = new ArrayList<>();
        list.add(mock(Kanban.class));

        when(kanbanRepository.findUserKanbans(EXISTS_USER_ID)).thenReturn(list);

        List<Kanban> kanbans = kanbanService.findUserKanbans();

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

    @AfterEach
    public void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
