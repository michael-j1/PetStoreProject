package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;
          @Data
          @NoArgsConstructor
   public class PetStoreData { //....DTO

 //......the following are fields from pet store entity. define instance variables for storing pet store data. 
	  private Long   petStoreId;
	 
	  private String petStoreName;
	  private String petStoreAddress;
	  private String petStoreCity;
	  private String petStoreState;
	  private String petStoreZip;
	  private String petStorePhone;	
	  private Set<PetStoreCustomer> customers = new HashSet<>();
	  private Set<PetStoreEmployee> employees = new HashSet<>();
		
	     
	      //...constructor for PetStoreData takes PetStore as parameter. sets matching fields in the PetStoreData class to data in the PetStore class
public PetStoreData(PetStore petStore) {
		  this.petStoreId = petStore.getPetStoreId();
		  this.petStoreAddress = petStore.getPetStoreAddress();		  
		  this.petStoreName = petStore.getPetStoreName();
		  this.petStoreCity = petStore.getPetStoreCity();
		  this.petStoreState = petStore.getPetStoreState();
		  this.petStoreZip = petStore.getPetStoreZip();
		  this.petStorePhone = petStore.getPetStorePhone();

		  //.........Sets the employees and customers fields to the respective PetStoreCustomer and PetStoreEmployee objects. These are Sets so used loops 
          for(Customer customer : petStore.getCustomers()) {
		  this.customers.add(new PetStoreCustomer(customer));
		  }
		  for(Employee employee : petStore.getEmployees()) {
		  this.employees.add(new PetStoreEmployee(employee));
		  }
	      }
	  
	  
 //.......inner PetStoreCustomer class......................
           @Data
           @NoArgsConstructor
public static class PetStoreCustomer {
		   //......Constructor it takes Customer object gets corresponding fields in PetStoreCustomer instance
	       public PetStoreCustomer(Customer customer) { 
		   customerId = customer.getCustomerId();
		   customerFirstName = customer.getCustomerFirstName();
		   customerLastName = customer.getCustomerLastName();
		   customerEmail = customer.getCustomerEmail();
	       }//...end PetStoreCustomer  constructor class
	       //the following is fields from customer entity
	       private Long   customerId;
	       private String customerFirstName;
	       private String customerLastName;
	       private String customerEmail;
           }//...end PetStoreCustomer inner class
	  
           
           
   //..........inner PetStoreEmployee class is the DTO. Takes Employee object
           @Data
       	   @NoArgsConstructor 									
  public static class PetStoreEmployee {
           //....constructor takes Employee object gets the corresponding fields in PetStoreExployee instance
       		public PetStoreEmployee(Employee employee) { 	
       		employeeId = employee.getEmployeeId();
       		employeeFirstName = employee.getEmployeeFirstName();
       		employeeLastName = employee.getEmployeeLastName();
       		employeePhone = employee.getEmployeePhone();
       		employeeJobTitle = employee.getEmployeeJobTitle();
       		} //end PetStoreEmployee Constructor class
       		private Long   employeeId;
       		private String employeeFirstName;
       		private String employeeLastName;
       		private String employeePhone;
       		private String employeeJobTitle;
           
           
           } //end PetStoreEmployee class


          

}//....end PetStoreData class