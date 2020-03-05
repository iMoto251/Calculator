package polyCal;

import java.text.DecimalFormat;

public class Scalar{

	private double num;
	
	public Scalar(double num) {
		this.num=num;
	}

	public double getNum() { return num; }
	public void setNum(double num) { this.num=num;}		
	
	public Scalar add(Scalar s) {
		double n;
		if(!(s instanceof Scalar))
			throw new RuntimeException("Not a real Scalar");
		Scalar scalar=(Scalar)s;
		n=num+scalar.getNum();
		return new Scalar(n);
	}
	
	public Scalar mul(Scalar s) {
		double n;
		if(!(s instanceof Scalar))
			throw new RuntimeException("Not a real Scalar");
		Scalar scalar=(Scalar)s;
		n=num*scalar.getNum();
		return new Scalar(n);
	}
	
	public Scalar mul(int num) {
		return new Scalar(num*getNum());
	}
	
	public Scalar pow(int exponent) {
		double n;
		n=Math.pow(num, exponent);
		return new Scalar(n);
	} 
	
	public Scalar neg() {
		double n;
		n=(-1)*getNum();
		return new Scalar(n);
	} 
	
	public boolean equals(Scalar s) {
		if(!(s instanceof Scalar))
			throw new RuntimeException("Not a real Scalar");
		Scalar scalar=(Scalar)s;
		return num==scalar.getNum();	
	} 
	
	public String toString() {
		DecimalFormat numberFormat = new DecimalFormat("#.###");
		String output=numberFormat.format(num);
		if(output.equals("-1"))
			return "-";
		return output;			
	}
}
