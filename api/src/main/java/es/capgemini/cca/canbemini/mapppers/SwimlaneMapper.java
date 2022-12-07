package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.swimlane.Swimlane;
import es.capgemini.cca.canbemini.kanban.swimlane.SwimlaneDto;

@Mapper(componentModel = "spring")
public interface SwimlaneMapper {
    Swimlane SwimlaneDtoToSwimlane(SwimlaneDto dto);

    SwimlaneDto SwimlaneToSwimlaneDto(Swimlane swimlane);

    List<Swimlane> swimlaneListDtoToSwimlaneList(List<SwimlaneDto> swimlaneListDto);

    List<SwimlaneDto> swimlaneListToSwimlaneListDto(List<Swimlane> swimlaneList);
}
