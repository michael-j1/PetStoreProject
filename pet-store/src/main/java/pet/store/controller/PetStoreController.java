package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

/*@RestController                 //.....tells Spring class is Rest controller. Expects and returns JSON in request/response bodies. Dafault status code is 200(OK) Tells Spring to map HTTP requests to class methods 
@RequestMapping("/pet_store")  //.....tells Spring the URI for every HTTP request that is mapped to a method in this controller class must start with "/pet_store"
@Slf4j                        //.....Lombok annotation that creates an SLF4J logger. It adds the logger as an instance variable named log. Used like this:log.info("This is a log line"): @ Promineo source

public class PetStoreController {
       
	    @Autowired //..................................(inject) the PetStoreService as an instance variable.
        private PetStoreService petStoreService;

         //............PostMapping.......................................
		// ......this method will get post request /pet_store / will be mapped
       //....The POST request is mapped to this method (/pet_store) POST request sent to /pet_store/ is mapped to this method(code = HttpStatus.CREATED)//....overrides default status of 200 with 201
        @PostMapping("/pet_store")
    	@ResponseStatus(code = HttpStatus.CREATED)
    	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
    	log.info("Creating pet store {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    	}
    	

        //..............This method updates an existing pet store with the specified ID by setting ID on the provided petStoreData object and saving it using the petStoreService. 
        @PutMapping("pet_store/{petStoreId}")
    	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
    	@RequestBody PetStoreData petStoreData) {
    	petStoreData.setPetStoreId(petStoreId);
    	log.info("Updating pet store {}", petStoreData);	
    	return petStoreService.savePetStore(petStoreData);
    	}
        
        @PutMapping("/{petStoreId}/employee/{employeeId}")
        public PetStoreData updateEmployee(@PathVariable Long employeeId,
        @RequestBody PetStoreData petStoreData) {
        petStoreData.setEmployeeId(employeeId);
        log.info("Updating employee {}", petStoreData);	
        return petStoreService.savePetStore(petStoreData);
            	}
        		
        		
          //................................................................................
         // source @Promineo Create a method in the controller that will add an employee to the employee table. The method should be annotated with @PostMapping and @ResponseStatus. 
        //@PostMapping: This must be configured to allow the caller to send an HTTP POST request to "/pet_store/{petStoreId}/employee", where {petStoreId}  is the ID of the pet store in which to add the employee (for example, "/pet_store/1/employee"). Remember that the "/pet_store" part of the HTTP URI is defined at the class level in the @RequestMapping annotation.
       // @ResponseStatus: This should be configured to return a 201 (Created) status code.
      //   The method should be public and return a PetStoreEmployee object.
     //	The method should accept the pet store ID and the PetStoreEmployee object as parameters. The pet store ID is passed in the URI and the PetStoreEmployee object is passed as JSON in the request body.
    //	Log the request.	The method should call the saveEmployee method in the pet store service and should return the results of that method call.

    	@PostMapping("/{petStoreId}/employee")
    	@ResponseStatus(code = HttpStatus.CREATED)
    	public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
    	log.info("Creating pet store employee {}", petStoreEmployee);		
    	PetStoreEmployee employee = petStoreService.saveEmployee(petStoreId, petStoreEmployee);
    		
    		return employee;
    	}
    	

                               //..getAllPetStores..
    	@GetMapping("/pet_store")
         public List<PetStoreData> getAllPetStores() {
	     log.info("Retrieving all pet stores");
         List<PetStoreData> petStores = petStoreService.retrieveAllPetStores();
         return petStores;
}
         
//......getPetStoreById
@GetMapping("/pet_store/{petStoreId}")
    public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
	log.info("Retrieving pet store with ID= {}", petStoreId);	
    PetStoreData petStore = petStoreService.retrievePetStoreById(petStoreId);
    return petStore;
}


//.....delete mapping
         @DeleteMapping("/pet_store/{petStoreId}")
         public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
	    log.info("Deleting pet store with ID={}", petStoreId);
    petStoreService.deletePetStoreById(petStoreId);
    return Map.of("message", "Delete pet store with ID=" + petStoreId + " was successful.");
}

*/


@RestController
//@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store {}", petStoreData);		
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet store {}", petStoreData);	
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/pet_store/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, 
    @RequestBody PetStoreEmployee petStoreEmployee) {	
	log.info("Creating pet store employee {}", petStoreEmployee);			
	PetStoreEmployee employee = petStoreService.saveEmployee(petStoreId, petStoreEmployee);	
	return employee;
	}
	
	
	@PutMapping("pet_store/{petStoreId}/employee/{employeeId}")
    public PetStoreData updateEmployee(@PathVariable Long employeeId,
    @RequestBody PetStoreData petStoreData) {
    petStoreData.setEmployeeId(employeeId);
    log.info("Updating employee {}", petStoreData);	
    return petStoreService.savePetStore(petStoreData);
        	}
    		
	
	
	@PostMapping("/pet_store/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer addCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
				log.info("Creating pet store customer {}", petStoreCustomer);		
				PetStoreCustomer customer = petStoreService.saveCustomer(petStoreId, petStoreCustomer);
		return customer;
	}
	
	
	@GetMapping("/pet_store")
	public List<PetStoreData> getAllPetStores() {
		log.info("Retrieving all pet stores");
	    List<PetStoreData> petStores = petStoreService.retrieveAllPetStores();
	    return petStores;
	}
	
	
	@GetMapping("/pet_store/{petStoreId}")
	public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store with ID= {}", petStoreId);
	    PetStoreData petStore = petStoreService.retrievePetStoreById(petStoreId);
	    return petStore;
	}
	
	
	
	
	@DeleteMapping("/pet_store/{petStoreId}/employees")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID={}", petStoreId);
	    petStoreService.deletePetStoreById(petStoreId);
	    return Map.of("message", "Delete pet store with ID=" + petStoreId + " was successful.");
	}
	
	
	
	
	/*@DeleteMapping("/pet_store/{petStoreId}/employee/{employeeId}")
	public Map<String, String> deleteEmployeeById(@PathVariable Long petStoreId,
			@RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Deleting employee with ID={}", petStoreEmployee);
	    PetStoreEmployee employee = petStoreService.deleteEmployee(petStoreId, petStoreEmployee);
	    return Map.of("message", "Delete pet store employee with ID=" + employee + " was successful.");
	}*/
	

}//...end PetStoreController class
