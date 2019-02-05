package test.cesarkuehl.ProjectionsTest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="branch")
public class Branch {
	
	@Id
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Company company;
	
	@OneToOne(mappedBy="branch", fetch = FetchType.LAZY, optional = false)
	private Address address;
	
	@OneToMany(mappedBy="branch", fetch = FetchType.LAZY)
	private List<Employee> employeers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Employee> getEmployeers() {
		return employeers;
	}

	public void setEmployeers(List<Employee> employeers) {
		this.employeers = employeers;
	}
}
