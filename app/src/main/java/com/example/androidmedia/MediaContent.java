package com.example.androidmedia;

public class MediaContent {
    private String identifier;
    private String basedOn;
    private String status;
    private String type;
    private String modality;
    private String view;
    private String subject;
    private String encounter;
    private String createdDateTime;
    private String createdPeriod;
    private String issued;
    private String operator;
    private String reasonCode;
    private String bodySite;
    private String deviceName;
    private String device;
    private String height;
    private String width;
    private String frames;
    private String duration;
    private int content;
    private String note;

    public MediaContent() {
    }

    public MediaContent(String identifier, String basedOn, String status, String type, String modality, String view, String subject, String encounter, String createdDateTime, String createdPeriod, String issued, String operator, String reasonCode, String bodySite, String deviceName, String device, String height, String width, String frames, String duration, int content, String note) {
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

    public String _getIdentifier() {
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
