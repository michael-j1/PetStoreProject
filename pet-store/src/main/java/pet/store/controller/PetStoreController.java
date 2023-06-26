package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController//.....tells Spring class is Rest controller. Expects and returns JSON in request/response bodies. Dafault status code is 200(OK) Tells Spring to map HTTP requests to class methods 
@RequestMapping("/pet_store")  //.....tells Spring the URI for every HTTP request that is mapped to a method in this controller class must start with "/pet_store"
@Slf4j      //.....Lombok annotation that creates an SLF4J logger. It adds the logger as an instance variable named log. Used like this:log.info("This is a log line"): @ Promineo source

public class PetStoreController {
       
	    @Autowired //..................................(inject) the PetStoreService as an instance variable.
        private PetStoreService petStoreService;

        //PostMapping.......................................
		// ......this method will get post request /pet_store / will be mapped
        @PostMapping()//....The POST request is mapped to this method (/pet_store) POST request sent to /pet_store/ is mapped to this method(code = HttpStatus.CREATED)//....overrides default status of 200 with 201
		@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) { //......use Jackson to convert JSON request payload into PetStoreData object supplies to method as parameter
	    log.info("Saving Pet Store {}", petStoreData);
	    return petStoreService.savePetStore(petStoreData);
	  } 

        //..............This method updates an existing pet store with the specified ID by setting ID on the provided petStoreData object and saving it using the petStoreService. 
        @PutMapping("/pet_store/{petStoreId}") 						//.............handles HTTP PUT requests to the /pet_store/{petStoreId}
    	public PetStoreData updatePetStore(@PathVariable Long petStoreId, 	//............petStoreId value extracted from the URL
    			@RequestBody PetStoreData petStoreData) {
    		log.info("Updating pet store with ID=" + petStoreId);
    		petStoreData.setPetStoreId(petStoreId);
    		
    		return petStoreService.savePetStore(petStoreData); 		//...................................RETURNS UPDATED INFO 
    	}



}//...end PetStoreController class
