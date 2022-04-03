package com.yakuza.backend.Model;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(schema = "dbo", name = "Papers")
public class Paper implements Serializable {

//    @Id
//    @SequenceGenerator(
//            name = "papers_sequence",
//            sequenceName = "papers_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "papers_sequence"
//    )
    private final Long id;
    private final String title;
//    @Column(name = "abstract")
    private final String paperAbstract;
    private final String status;
    private String cameraReadyCopyURL;
    private final String fullPaperURL;

    public Paper(Long id,
                 String title,
                 String paperAbstract,
                 String status,
                 String fullPaperURL) {
        this.id = id;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.status = status;
        this.fullPaperURL = fullPaperURL;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public String getStatus() {
        return status;
    }

    public String getFullPaperURL() {
        return fullPaperURL;
    }

    public String getCameraReadyCopyURL() {
        return cameraReadyCopyURL;
    }

    public void setCameraReadyCopyURL(String cameraReadyCopyURL) {
        this.cameraReadyCopyURL = cameraReadyCopyURL;
    }
}
