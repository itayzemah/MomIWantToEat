
public class Ingredient {
	private String name;
	private double count;

	public Ingredient(String name, double count) {
		this.name = name;
		this.count = count;

	}
	public Ingredient(String ingre) {
		try {
		this.name = ingre.split("$")[0];
		this.count = Double.valueOf(ingre.split("$")[1]);
		}catch (Exception e) {
			this.count = 1;
		}
	}
	public double getCount() {
		return count;
	}

	public void addCount(float count) {
		this.count += count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(", כמות=");
		builder.append(count);
		return builder.toString();
	}

}
