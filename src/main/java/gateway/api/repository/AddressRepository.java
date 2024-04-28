package gateway.api.repository;

import gateway.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.cep = :cep")
    public Address findByCep(String cep);
}
