package es.capgemini.cca.canbemini.mapppers;

import java.util.List;

import org.mapstruct.Mapper;

import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.Attachment;
import es.capgemini.cca.canbemini.kanban.swimlane.note.attachment.AttachmentDto;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    Attachment AttachmentDtoToAttachment(AttachmentDto dto);

    AttachmentDto AttachmentToAttachmentDto(Attachment kanban);

    List<AttachmentDto> map(List<Attachment> attachmentList);

    List<Attachment> AttachmentDtoListToAttachmentList(List<AttachmentDto> attachmentList);
}