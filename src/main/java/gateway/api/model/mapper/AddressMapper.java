package gateway.api.model.mapper;

import gateway.api.entity.Address;
import gateway.api.model.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO convert(Address address) {
        return AddressDTO.builder()
                .cep(address.getCep())
                .state(address.getState())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .street(address.getStreet())
                .service(address.getService())
                .build();
    }
}