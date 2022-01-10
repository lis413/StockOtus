package ru.lis154.stockotus.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEntity {
    private String message;
    private String type;
    private String status;
    private String created;
    private String createdBy;
    private String modified;
    private String modifiedBy;
}
