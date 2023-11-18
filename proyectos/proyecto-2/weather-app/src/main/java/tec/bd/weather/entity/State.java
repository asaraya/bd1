package tec.bd.weather.entity;

public class State {

    private Integer id;
    private String stateName;
    private int countryId;

    public State(Integer id, String stateName, int countryId) {
        this.id = id;
        this.stateName = stateName;
        this.countryId = countryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getCountryId(){return countryId;}

    public void setCountryId(int countryId) {this.countryId = countryId;}
}
