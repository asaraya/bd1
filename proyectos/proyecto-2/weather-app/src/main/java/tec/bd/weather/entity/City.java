package tec.bd.weather.entity;

public class City {

    private Integer idCiudad;
    private String cityName;
    private String zipcode;
    private int idState;

    public City(Integer idCiudad, String cityName, String zipcode, int idState) {
        this.idCiudad = idCiudad;
        this.cityName = cityName;
        this.zipcode = zipcode;
        this.idState = idState;
    }

    public City(String cityName, int idState) {
        this.cityName = cityName;
        this.idState = idState;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public int getIdState() {return idState;}
    public void setStateId(int idState) {this.idState = idState;}
}
