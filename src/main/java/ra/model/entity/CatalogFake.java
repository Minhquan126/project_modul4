package ra.model.entity;

import org.springframework.web.multipart.MultipartFile;

public class CatalogFake {
    private int catalogId;
    private String catalogName;
    private String description;
    private String country;
    private MultipartFile image;

    public CatalogFake() {
    }

    public CatalogFake(int catalogId, String catalogName, String description, String country, MultipartFile image) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.country = country;
        this.image = image;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
