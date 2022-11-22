package es.capgemini.cca.canbemini.kanban.swimlane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SwimlaneTest {

    @Mock
    SwimlaneRepository swimlaneRepository;

    @InjectMocks
    SwimlaneServiceImpl swimlaneService;

    private static final Long EXISTS_SWIMLANE_ID = 1L;

    @Test
    public void findAllShouldReturnAllSwimlanes() {
        List<Swimlane> list = new ArrayList<>();
        list.add(mock(Swimlane.class));

        when(swimlaneRepository.findAll()).thenReturn(list);

        List<Swimlane> swimlanes = swimlaneService.findAll();

        assertNotNull(swimlanes);
        assertEquals(3, swimlanes.size());
    }

    @Test
    public void findExistsSwimlaneShouldReturnSwimlane() {
        Swimlane swimlane = mock(Swimlane.class);
        when(swimlane.getId()).thenReturn(EXISTS_SWIMLANE_ID);
        when(swimlaneRepository.findById(EXISTS_SWIMLANE_ID)).thenReturn(Optional.of(swimlane));

        Swimlane swimlaneResponse = swimlaneService.findSwimlane(EXISTS_SWIMLANE_ID);

        assertNotNull(swimlaneResponse);
        assertEquals(EXISTS_SWIMLANE_ID, swimlane.getId());
    }

    @Test
    public void deleteExistsSwimlaneShouldDelete() {
        this.swimlaneService.deleteSwimlane(EXISTS_SWIMLANE_ID);

        verify(swimlaneRepository).deleteById(EXISTS_SWIMLANE_ID);
    }
}
