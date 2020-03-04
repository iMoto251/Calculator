package Scalar;

import java.text.DecimalFormat;

public class RealScalar implements Scalar{

	private double num;
	
	public RealScalar(double num) {
		this.num=num;
	}

	public double getNum() { return num; }
	public void setNum(double num) { this.num=num;}		
	
	public Scalar add(Scalar s) {
		double n;
		if(!(s instanceof RealScalar))
			throw new RuntimeException("Not a real Scalar");
		RealScalar scalar=(RealScalar)s;
		n=num+scalar.getNum();
		return new RealScalar(n);
	}
	
	public Scalar mul(Scalar s) {
		double n;
		if(!(s instanceof RealScalar))
			throw new RuntimeException("Not a real Scalar");
		RealScalar scalar=(RealScalar)s;
		n=num*scalar.getNum();
		return new RealScalar(n);
	}
	
	public Scalar mul(int num) {
		return new RealScalar(num*getNum());
	}
	
	public Scalar pow(int exponent) {
		double n;
		n=Math.pow(num, exponent);
		return new RealScalar(n);
	} 
	
	public Scalar neg() {
		double n;
		n=(-1)*getNum();
		return new RealScalar(n);
	} 
	
	public boolean equals(Scalar s) {
		if(!(s instanceof RealScalar))
			throw new RuntimeException("Not a real Scalar");
		RealScalar scalar=(RealScalar)s;
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
