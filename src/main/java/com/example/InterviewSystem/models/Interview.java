package com.example.InterviewSystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInterview;
    private String nameInterview;
    private Date dateStart;
    private Date dateEnd;
    private String description;

    public Long getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(Long idInterview) {
        this.idInterview = idInterview;
    }

    public String getNameInterview() {
        return nameInterview;
    }

    public void setNameInterview(String nameInterview) {
        this.nameInterview = nameInterview;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Interview() {
    }

    public Interview(String nameInterview, Date dateStart, Date dateEnd, String description) {
        this.nameInterview = nameInterview;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
