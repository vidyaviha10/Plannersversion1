package version1;

public class person {

	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	String name;
	Double value;
	Double weight;
	public person(String name, Double value, Double weight) {
		super();
		this.name = name;
		this.value = value;
		this.weight = weight;
	}
	
	public person()
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}


