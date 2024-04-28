package gateway.api.service;

import gateway.api.entity.Address;
import gateway.api.exceptions.AddressNotFoundException;
import gateway.api.exceptions.CepInvalidException;
import gateway.api.model.dto.AddressDTO;
import gateway.api.model.mapper.AddressMapper;
import gateway.api.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class AddressService {
    @Autowired
    private HttpClient httpClient;

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddressMapper addressMapper;

    private final String URL_BRASILAPI = "https://brasilapi.com.br/api/cep/v1/";

    public ResponseEntity<AddressDTO> getCep(Integer cep) throws Exception {

        try {

            if (String.valueOf(cep).length() != 8) {
                throw new CepInvalidException("O CEP informado é inválido.");
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL_BRASILAPI + cep))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new AddressNotFoundException("Endereço não encontrado. Favor informar um CEP válido.");

            } if (response.statusCode() == 400) {
                throw new CepInvalidException("O CEP informado é inválido.");
            }

            Address addressEntity = objectMapper.readValue(response.body(), Address.class);

            Address addressbyCep = addressRepository.findByCep(addressEntity.getCep());

            if (Objects.isNull(addressbyCep))
                addressEntity = addressRepository.save(addressEntity);

            return new ResponseEntity<AddressDTO>(addressMapper.convert(addressEntity), HttpStatus.OK);

        } catch (HttpClientErrorException | URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Ocorreu um erro na chamada da API: " + e.getMessage());
        }
    }
}
