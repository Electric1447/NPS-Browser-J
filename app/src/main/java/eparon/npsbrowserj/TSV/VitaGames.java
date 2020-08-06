package eparon.npsbrowserj.TSV;

public class VitaGames extends BaseTSV {

    private String MinFW;

    public VitaGames (String titleID, String region, String title, String link, String license, String contentID, String lastDateTime, long fileSize, String sha256, String minFW) {
        super(titleID, region, title, link, license, contentID, lastDateTime, fileSize, sha256);
        this.MinFW = minFW;
    }

    public String getMinFW () {
        return MinFW;
    }

}
