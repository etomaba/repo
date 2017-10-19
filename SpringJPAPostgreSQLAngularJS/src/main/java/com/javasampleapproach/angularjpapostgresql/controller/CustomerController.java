package com.javasampleapproach.angularjpapostgresql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.angularjpapostgresql.message.Response;
import com.javasampleapproach.angularjpapostgresql.model.Customer;
import com.javasampleapproach.angularjpapostgresql.repo.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	@RequestMapping(value = "/postcustomer", method = RequestMethod.POST)
	public void postCustomer(@RequestBody Customer customer) {

		repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
	}

	@RequestMapping("/findall")
	public Response findAll() {

		Iterable<Customer> customers = repository.findAll();

		return new Response("Done", customers);
	}

	@RequestMapping("/customer/{id}")
	public Response findCustomerById(@PathVariable("id") long id) {

		Customer customer = repository.findOne(id);

		return new Response("Done", customer);
	}

	@RequestMapping("/findbylastname")
	public Response findByLastName(@RequestParam("lastName") String lastName) {

		List<Customer> customers = repository.findByLastName(lastName);

		return new Response("Done", customers);
	}
// bez uzycia angualara. trzeba dodac do adresu np. http://localhost:8090/findall2  http://localhost:8080/findbylastname?lastname=Smith	
	@RequestMapping("/saveman")
	public String process(){
		repository.save(new Customer("Jack", "Smith"));
		repository.save(new Customer("Adam", "Johnson"));
		repository.save(new Customer("Kim", "Smith"));
		repository.save(new Customer("David", "Williams"));
		repository.save(new Customer("Peter", "Davis"));
		return "Done";
	}
	
	@RequestMapping("/findall2")
	public String findAll2(){
		String result = "<html>";
		
		for(Customer cust : repository.findAll()){
			result += "<div>" + cust.toString() + "</div>";
		}
		
		return result + "</html>";
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findOne(id).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname2")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "<html>";
		
		for(Customer cust: repository.findByLastName(lastName)){
			result += "<div>" + cust.toString() + "</div>"; 
		}
		
		return result + "</html>";
	}
}
