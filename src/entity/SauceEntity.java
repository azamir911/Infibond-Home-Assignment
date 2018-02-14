package entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SauceEntity extends BaseEntity {

	public SauceEntity(String name, double price, int amount) {
		super(name, price, amount);
	}
	
}
