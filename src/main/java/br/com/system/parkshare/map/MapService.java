package br.com.system.parkshare.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.garage.Garage;
import br.com.system.parkshare.garage.GarageService;

@Service
public class MapService {

    @Autowired
    private GarageService garageService;


    public Iterable<Garage> findGarages(double latitude, double longitude, int zoom) {
        double[] bounds = calculateBounds(latitude, longitude, zoom);
        return findGarages(bounds[0], bounds[1], bounds[2], bounds[3]);
    }

    public Iterable<Garage> findGarages(double minLat, double maxLat, double minLon, double maxLon) {
        return garageService.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLon, maxLon);
    }

    public Page<Garage> findGarages(String name, int page, int size) {
        return garageService.findAllByName(name, page, size);
    }


    public double[] calculateBounds(double latitude, double longitude, int zoom) {
        double latitudeCoverage = 360.0 / Math.pow(2, zoom);
        double longitudeCoverage = 360.0 / (Math.pow(2, zoom) * Math.cos(Math.toRadians(latitude)));

        double minLat = latitude - (latitudeCoverage / 2);
        double maxLat = latitude + (latitudeCoverage / 2);
        double minLon = longitude - (longitudeCoverage / 2);
        double maxLon = longitude + (longitudeCoverage / 2);

        return new double[] {minLat, maxLat, minLon, maxLon};
    }
}
