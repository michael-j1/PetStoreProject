package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Entity// specifies persistent entity in database
@Data//....generates boiler plate code for getters setters equals hashCode
public class Customer {
@Id //primary key
@GeneratedValue(strategy = GenerationType.IDENTITY)//...value of indentity will be auto generated
private Long customerId;//...unique identifier for customer
//columns in customer table
private String customerFirstName;
private String customerLastName;
private String customerEmail;

@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)//indicates customers field is owning side
Set<PetStore> petStores = new HashSet<>();

}

