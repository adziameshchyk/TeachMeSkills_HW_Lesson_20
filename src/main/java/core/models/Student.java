package core.models;

import core.services.CityService;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int cityId;

    public Student(String firstName, String lastName, String cityName) {
        this.firstName = firstName;
        this.lastName = lastName;

        setCityId(cityName);
    }

    public Student(int id, String firstName, String lastName, int cityId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(String cityName) {
        CityService cityService = new CityService();
        this.cityId = cityService.getCityId(cityName);
    }

    @Override
    public String toString() {
        return "id " + id +
                ": " + firstName +
                " " + lastName +
                ", ";
    }
}
