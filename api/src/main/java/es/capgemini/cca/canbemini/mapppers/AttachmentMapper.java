package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.AttachmentDto;

@Mapper(componentModel = "spring", uses = { UserKanbanPermissionMapper.class, UsersMapper.class })
public interface AttachmentMapper {
    @Mapping(target = "note", ignore = true)
    Attachment AttachmentDtoToAttachment(AttachmentDto dto);

//    @Mapping(target = "note", ignore = true)
    AttachmentDto AttachmentToAttachmentDto(Attachment kanban);

    List<AttachmentDto> map(List<Attachment> attachmentList);

    List<Attachment> AttachmentDtoListToAttachmentList(List<AttachmentDto> attachmentList);
}