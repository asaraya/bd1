package tec.bd.weather.entity;

public class Log {

    private Integer id;
    private String entryText;

    public Log(Integer id, String entryText) {
        this.id = id;
        this.entryText = entryText;
    }

    public Log(String entryText) {
        this.entryText = entryText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }
}
