package br.com.system.parkshare.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/map")
public class MapController {

    @Autowired
    private MapService service;

    // https://www.openstreetmap.org/search?query=40.7128%2C%20-74.0060#map=7/40.489/-75.597
    @GetMapping("/{latitude}/{longitude}/{zoom}")
    public ResponseEntity<ReturnOnlyData> get(
            @PathVariable double latitude, @PathVariable double longitude, @PathVariable int zoom,
            @RequestParam(value = "tileSizeInPoints", defaultValue = "256") int tileSizeInPoints) {

        if (zoom < 0 || zoom > 19) {
            return ResponseEntity.badRequest().body(new ReturnOnlyData("Zoom inválido"));
        }

        return ResponseEntity.ok(new ReturnOnlyData(service.findGarages(latitude, longitude, zoom)));
    }

    @GetMapping("/garage/{garagem}")
    public ResponseEntity<Object> get(@PathVariable String garagem,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (garagem == null || garagem.isEmpty()) {
            return ResponseEntity.badRequest().body(new ReturnOnlyData("Garagem inválida"));
        }

        return ResponseEntity.ok(service.findGarages(garagem, page, pageSize));
    }

    public record ReturnOnlyData(Object data) {
    }
}
