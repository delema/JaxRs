package es.lema.service.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Client {
	
	protected Integer identifier = null;
	protected String name = null;
	protected String address = null;
	protected String telephone = null;
	
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
