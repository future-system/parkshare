package br.com.system.parkshare.address;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    private final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?q=%s&format=json&addressdetails=1";
    private final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";

    public Object getAddress(String address) {
        return new RestTemplate().getForObject(String.format(NOMINATIM_URL, address), Object.class);
    }

    public Object getCep(String cep) {
        return new RestTemplate().getForObject(String.format(VIA_CEP_URL, cep), Object.class);
    }

}
