package eparon.npsbrowserj.TSV;

import java.io.Serializable;

public class BaseTSV implements Serializable {

    private String TitleID;
    private String Region;
    private String Title;
    private String Link;
    private String License;
    private String ContentID;
    private String LastDateTime;
    private long FileSize;

    public BaseTSV (String titleID, String region, String title, String link, String license, String contentID, String lastDateTime, long fileSize) {
        this.TitleID = titleID;
        this.Region = region;
        this.Title = title;
        this.Link = link;
        this.License = license;
        this.ContentID = contentID;
        this.LastDateTime = lastDateTime;
        this.FileSize = fileSize;
    }

    public String getTitleID () {
        return TitleID;
    }

    public String getRegion () {
        return Region;
    }

    public String getTitle () {
        return Title;
    }

    public String getLink () {
        return Link;
    }

    public String getLicense () {
        return License;
    }

    public String getContentID () {
        return ContentID;
    }

    public String getLastDateTime () {
        return LastDateTime;
    }

    public long getFileSize () {
        return FileSize;
    }

}
