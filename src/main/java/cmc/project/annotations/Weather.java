package cmc.project.annotations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="weather")
public class Weather implements Serializable {


    @Id
    @Column(name = "city")
    private String city;

    @Id
    @Column(name = "dt")
    private String dt;

    @Column(name = "wf")
    private String wf;


    @Column(name = "tmn")
    private String tmn;

    @Column(name = "tmx")
    private String tmx;


    public Weather() {
    }

    public Weather(String city, String dt, String wf, String tmn, String tmx) {
        this.city = city;
        this.dt = dt;
        this.wf = wf;
        this.tmn = tmn;
        this.tmx = tmx;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getWf() {
        return wf;
    }

    public void setWf(String wf) {
        this.wf = wf;
    }

    public String getTmn() {
        return tmn;
    }

    public void setTmn(String tmn) {
        this.tmn = tmn;
    }

    public String getTmx() {
        return tmx;
    }

    public void setTmx(String tmx) {
        this.tmx = tmx;
    }
}