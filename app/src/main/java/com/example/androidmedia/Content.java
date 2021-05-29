package com.example.androidmedia;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "content_table")
public class Content {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "identifier")
    private String identifier;
    @ColumnInfo(name = "basedOn")
    private String basedOn;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "modality")
    private String modality;
    @ColumnInfo(name = "view")
    private String view;
    @ColumnInfo(name = "subject")
    private String subject;
    @ColumnInfo(name = "encounter")
    private String encounter;
    @ColumnInfo(name = "createdDateTime")
    private String createdDateTime;
    @ColumnInfo(name = "createdPeriod")
    private String createdPeriod;
    @ColumnInfo(name = "issued")
    private String issued;
    @ColumnInfo(name = "operator")
    private String operator;
    @ColumnInfo(name = "reasonCode")
    private String reasonCode;
    @ColumnInfo(name = "bodySite")
    private String bodySite;
    @ColumnInfo(name = "deviceName")
    private String deviceName;
    @ColumnInfo(name = "device")
    private String device;
    @ColumnInfo(name = "height")
    private String height;
    @ColumnInfo(name = "width")
    private String width;
    @ColumnInfo(name = "frames")
    private String frames;
    @ColumnInfo(name = "duration")
    private String duration;
    @ColumnInfo(name = "content")
    private int content;
    @ColumnInfo(name = "note")
    private String note;


    public Content(@NonNull String identifier, String basedOn, String status, String type, String modality, String view, String subject, String encounter, String createdDateTime, String createdPeriod, String issued, String operator, String reasonCode, String bodySite, String deviceName, String device, String height, String width, String frames, String duration, int content, String note) {
        this.identifier = identifier;
        this.basedOn = basedOn;
        this.status = status;
        this.type = type;
        this.modality = modality;
        this.view = view;
        this.subject = subject;
        this.encounter = encounter;
        this.createdDateTime = createdDateTime;
        this.createdPeriod = createdPeriod;
        this.issued = issued;
        this.operator = operator;
        this.reasonCode = reasonCode;
        this.bodySite = bodySite;
        this.deviceName = deviceName;
        this.device = device;
        this.height = height;
        this.width = width;
        this.frames = frames;
        this.duration = duration;
        this.content = content;
        this.note = note;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getBasedOn() {
        return basedOn;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getModality() {
        return modality;
    }

    public String getView() {
        return view;
    }

    public String getSubject() {
        return subject;
    }

    public String getEncounter() {
        return encounter;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public String getCreatedPeriod() {
        return createdPeriod;
    }

    public String getIssued() {
        return issued;
    }

    public String getOperator() {
        return operator;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getBodySite() {
        return bodySite;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDevice() {
        return device;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getFrames() {
        return frames;
    }

    public String getDuration() {
        return duration;
    }

    public int getContent() {
        return content;
    }

    public String getNote() {
        return note;
    }
}
