package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.PetStore;
public interface PetStoreDao extends JpaRepository<PetStore, Long> {

}
//data layer interface extends jpa repo