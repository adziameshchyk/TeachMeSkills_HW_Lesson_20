package core.services;

import core.models.City;
import core.services.crud.CityCRUDService;

import java.util.List;

public class CityService {

    CityCRUDService cityCRUDService;

    public CityService() {
        this.cityCRUDService = new CityCRUDService();
    }

    public City getCityById(int id) {
        return cityCRUDService.getCityById(id);
    }

    public int getCityId(String name) {
        return cityCRUDService.getCityId(name);
    }

    public List<City> getAllCities() {
        return null;
    }

    public void addCity(String name) {

    }

    public void deleteCity(int id) {

    }

}
