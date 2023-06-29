package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;
@Service
public class PetStoreService {
	
      	//....Spring can inject DAO object into the variable autowired
	    //PetStoreDao object named petStoreDao as pvt instance variable
      	@Autowired
      	private PetStoreDao petStoreDao;
      	@Autowired 
      	private EmployeeDao employeeDao;
      	@Autowired
      	private CustomerDao customerDao;

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
//		         .........This method either finds an existing pet store by its ID using the findPetStoreById method, or creates a new PetStore object if the petStoreId is null. If petStoreId is null, it creates a new PetStore instance. If  petStoreId is not null, it calls findPetStoreById method to get corresponding PetStore instance. Method returns the obtained PetStore object.
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

		//...saveEmployyee method
		@Transactional
		public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
			PetStore petStore = findPetStoreById(petStoreId);
			Long employeeId = petStoreEmployee.getEmployeeId();
			Employee employee = findOrCreateEmployee(petStoreId, employeeId);
			copyEmployeeFields(employee, petStoreEmployee);
			employee.setPetStore(petStore);
			petStore.getEmployees().add(employee);
			Employee dbEmployee = employeeDao.save(employee);
			return new PetStoreEmployee(dbEmployee);

		}
//......findEmployeeById() method
		        public Employee findEmployeeById(Long petStoreId, Long employeeId) {
                Employee employee = employeeDao.findById(employeeId).orElse(null);
			    if (employee == null) {
				throw new NoSuchElementException("Employee with ID=" + employeeId + " does not exist.");
		     	}
		    	if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
				throw new IllegalArgumentException(
		     	"Employee with ID=" + employeeId + " does not exist in pet store with ID=" + petStoreId);}
			    return employee;

		}
//....findOrCreateEmployee Method
		public Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {

			if (Objects.isNull(employeeId)) {
				return new Employee();
			}
			return findEmployeeById(petStoreId, employeeId);
		}

		
		//.....copyEmplpyeeFields method
		public void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
			employee.setEmployeeId(petStoreEmployee.getEmployeeId());
			employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
			employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
			employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
			employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());

		}
		//.....findPetStoreById
		    @Transactional(readOnly = false)
		    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
			PetStore petStore = findPetStoreById(petStoreId);
			Long customerId = petStoreCustomer.getCustomerId();
			Customer customer = findOrCreateCustomer(petStoreId, customerId);
			copyCustomerFields(customer, petStoreCustomer);
			customer.getPetStores().add(petStore);
			petStore.getCustomers().add(customer);
			Customer dbCustomer = customerDao.save(customer);
			return new PetStoreCustomer(dbCustomer);
		}
//....findOrCreateCustomer method
		    private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
			if (Objects.isNull(customerId)) {
	     	return new Customer();
			} return findCustomerById(petStoreId, customerId);
		}
		    
		    
//...findCustomerById method
		    private Customer findCustomerById(Long petStoreId, Long customerId) {
			Customer customer = customerDao.findById(customerId).orElse(null);
			if (customer == null) {
				throw new NoSuchElementException("Customer with ID=" + customerId + " does not exist.");
			}
			boolean petStoreFound = customer.getPetStores().stream()
					.anyMatch(petStore -> petStore.getPetStoreId().equals(petStoreId));

			if (!petStoreFound) {
				throw new IllegalArgumentException(
						"PetStore with ID=" + petStoreId + " not found in customer's pet stores.");
			}
		    return customer;
		}

		    
		    //....copyCustomerFields
		private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
			customer.setCustomerId(petStoreCustomer.getCustomerId());
			customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
			customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
			customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());

		}
//....retrieveAllPetStores
		@Transactional(readOnly = true)
		public List<PetStoreData> retrieveAllPetStores() {
		    List<PetStore> petStores = petStoreDao.findAll();
		    List<PetStoreData> result = new LinkedList<>();
		    for (PetStore petStore : petStores) {
		        PetStoreData psd = new PetStoreData(petStore);

		        psd.getCustomers().clear();
		        psd.getEmployees().clear();
		        result.add(psd);
		        }
		        return result;
		         }
		
//retrievePetStoreById........
		@Transactional(readOnly = true)
		public PetStoreData retrievePetStoreById(Long petStoreId) {
			PetStore petStore = petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("PetStore with ID=" + petStoreId + " does not exist."));
		    return new PetStoreData(petStore);
		}
		
//.......deletePetStoreById method
		@Transactional(readOnly = false)
		public void deletePetStoreById(Long petStoreId) {
			PetStore petStore = petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("PetStore with ID=" + petStoreId + " does not exist."));
		    petStoreDao.delete(petStore);

		}

		//public PetStoreEmployee deleteEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
	//		return null;
		//}

	 
}//...end PetStoreService class
