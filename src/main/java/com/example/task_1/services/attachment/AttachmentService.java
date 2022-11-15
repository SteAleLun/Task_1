package com.example.task_1.services.attachment;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;

public interface AttachmentService {

    GetAttachmentMetadataDTO create (CreateAttachmentMetadataDTO createDTO);
}
