package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
      	//....Spring can inject DAO object into the variable autowired
	    //PetStoreDao object named petStoreDao as pvt instance variable
      	@Autowired
      	private PetStoreDao petStoreDao;

//........Method below saves any new pet store that is entered through JSON. PetStoreData object as a parameter and return a PetStoreData object
	    public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore  = findOrCreatePetStore(petStoreId);

		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));//....Call the PetStoreDao method save(petStore). Return a new PetStoreData object created from the return value of the save() method.

	}
	   
//...below...Call copyPetStoreFields(). This method takes a PetStore object and a PetStoreData object as parameters. Matching fields are copied from the PetStoreData object to  PetStore object.
	        private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
			
	        petStore.setPetStoreId(petStoreData.getPetStoreId());
			petStore.setPetStoreName(petStoreData.getPetStoreName());
			petStore.setPetStoreCity(petStoreData.getPetStoreCity());
			petStore.setPetStoreState(petStoreData.getPetStoreState());
			petStore.setPetStoreZip(petStoreData.getPetStoreZip());
			petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		}
//...Below.. method finds pet store by Id using petStoreDao. If a pet store with the given ID is found, it is returned.If no pet store is found, it throws a NoSuchElementException with an error message.
		private PetStore findPetStoreById(Long petStoreId)  {
		return petStoreDao.findById(petStoreId)
		.orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " was not found."));
		}
//		         This method either finds an existing pet store by its ID using the findPetStoreById method, or creates a new PetStore object if the petStoreId is null. If petStoreId is null, it creates a new PetStore instance. If  petStoreId is not null, it calls findPetStoreById method to get corresponding PetStore instance. Method returns the obtained PetStore object.
		private PetStore findOrCreatePetStore(Long petStoreId) {
			PetStore petStore;
			if(Objects.isNull(petStoreId)) {
			petStore = new PetStore();
			}
			else {
			petStore = findPetStoreById(petStoreId);
			}
			return petStore;
		    }//...end findOrCreatePetStore 

	
}//...end PetStoreService class
