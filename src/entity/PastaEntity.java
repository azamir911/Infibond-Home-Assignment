package entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PastaEntity extends BaseEntity {
	
	private List<SauceEntity> sauces;
	private String description;
	
	public PastaEntity(String name, List<SauceEntity> list, double price,
			String description, int amount) {
		super(name, price, amount);
		this.sauces = list;
		this.description = description;
	}

	public List<SauceEntity> getSauces() {
		return sauces;
	}

	public void setSauces(List<SauceEntity> list) {
		this.sauces = list;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		
		String string = super.toString();
		string += ", Saucea: " + this.sauces.toString() + ", description: " + description;
		return string;
	}

}
