package es.lema.service.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Authentication {
	
	protected String user = null;
	protected String password = null;
	protected String token = null;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
