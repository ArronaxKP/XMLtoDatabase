import java.math.BigDecimal;


public class Decimal {
	private BigDecimal value;
	
	public Decimal(String value, int length, int decimalPlaces){
		this.value = new BigDecimal(value);
		this.value = this.value.setScale(2); 
	}
}
