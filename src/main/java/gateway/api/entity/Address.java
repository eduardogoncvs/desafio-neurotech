package gateway.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    @Transient
    private String service;
}
