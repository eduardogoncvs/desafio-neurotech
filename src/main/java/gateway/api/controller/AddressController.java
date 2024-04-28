package gateway.api.controller;

import gateway.api.model.dto.AddressDTO;
import gateway.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep/v1")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressDTO> getCep(@PathVariable Integer cep) throws Exception {
        return addressService.getCep(cep);
    }
}
