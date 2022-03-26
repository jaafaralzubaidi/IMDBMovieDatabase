public class Artist {

    private int id;
    private String name;
    private String url;
    private String picUrl;

    public Artist(int id, String name, String url, String picUrl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.picUrl = picUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPicUrl() {
        return picUrl;
    }

}
