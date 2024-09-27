 package br.com.system.parkshare;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
class ParkshareApplicationTests {

	public static void main(String[] args) {
        double latitude = 40.7128; // Latitude for New York City
        double longitude = -74.0060; // Longitude for New York City
        int zoomLevel = 3; // New zoom level

        double[] bounds = calculateBounds(latitude, longitude, zoomLevel);
        
        System.out.printf("Min Latitude: %.6f%n", bounds[0]);
        System.out.printf("Max Latitude: %.6f%n", bounds[1]);
        System.out.printf("Min Longitude: %.6f%n", bounds[2]);
        System.out.printf("Max Longitude: %.6f%n", bounds[3]);
    }

    public static double[] calculateBounds(double latitude, double longitude, int zoom) {
        double latitudeCoverage = 360.0 / Math.pow(2, zoom);
        double longitudeCoverage = 360.0 / (Math.pow(2, zoom) * Math.cos(Math.toRadians(latitude)));

        double minLat = latitude - (latitudeCoverage / 2);
        double maxLat = latitude + (latitudeCoverage / 2);
        double minLon = longitude - (longitudeCoverage / 2);
        double maxLon = longitude + (longitudeCoverage / 2);

        return new double[] {minLat, maxLat, minLon, maxLon};
    }

}
