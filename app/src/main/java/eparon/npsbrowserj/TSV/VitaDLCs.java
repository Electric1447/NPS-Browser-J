package eparon.npsbrowserj.TSV;

public class VitaDLCs extends BaseTSV {

    private String SHA256;

    public VitaDLCs (String titleID, String region, String title, String link, String license, String contentID, String lastDateTime, long fileSize, String sha256) {
        super(titleID, region, title, link, license, contentID, lastDateTime, fileSize);
        this.SHA256 = sha256;
    }

    public String getSHA256 () {
        return SHA256;
    }

}
