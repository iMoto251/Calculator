package Poly;
import Scalar.RationalScalar;
import Scalar.RealScalar;
import Scalar.Scalar;

public class PolyTerm implements Comparable<PolyTerm>{
	private Scalar coefficient;
	private int exponent;
	private boolean isRational; //true- it is a rational scalar. real scalar otherwise.
	
	public PolyTerm(Scalar coefficient, int exponent, boolean isRational) {
		if(exponent<0)
			throw new IllegalArgumentException("input wrong! exponent should be non-negative");
		this.coefficient=coefficient;
		this.exponent=exponent;
		this.isRational=isRational;
	}
	
	public PolyTerm(String polyterm, boolean isRational) {
		this.isRational=isRational;
		if(polyterm==null)
			throw new IllegalArgumentException("polyterm cannot be null");
		String coefficient="";
		int index=0;
		while(index<polyterm.length() && polyterm.charAt(index)!='x' ) {
			coefficient+=polyterm.charAt(index);
			index++;
		}
		int exp_index=polyterm.indexOf('^');
		if(exp_index!=-1) {
			String exponent=polyterm.substring(exp_index+1, polyterm.length());
			this.exponent=Integer.parseInt(exponent);
		}
		else
			this.exponent=0;
		
		String numerator="", divisor="";
		if(coefficient.contains("/")) {
			index=0;
			while(coefficient.charAt(index)!='/')
			{
				numerator+=coefficient.charAt(index);
				index++;
			}
			index++;
			while(index!=coefficient.length()) {
				divisor+=coefficient.charAt(index);
				index++;
			}
		}

		if(isRational) {
			if(!coefficient.contains("/")) {
				numerator=coefficient;
				divisor="1";
			}
			if(coefficient.equals("-"))
				numerator="-1";
			if(coefficient.equals("+"))
				numerator="1";
			if(numerator.length()==0)
				numerator="1";
			if(divisor.length()==0)
				divisor="1";
			this.coefficient=new RationalScalar(Integer.parseInt(numerator), Integer.parseInt(divisor));
		}
		else {
			if(coefficient.length()==0 || coefficient.charAt(coefficient.length()-1)=='+') 
				coefficient="1";
			else if(coefficient.charAt(coefficient.length()-1)=='-')
				coefficient="-1";
			
			if(coefficient.contains("/"))
				this.coefficient=new RealScalar(Double.parseDouble(numerator)/Double.parseDouble(divisor));
			else
				this.coefficient=new RealScalar(Double.parseDouble(coefficient));
		}
	}
	
		public boolean canAdd(PolyTerm pt) {
		if(pt.exponent==this.exponent && isRational==pt.getIsRational())
			return true;
		return false;
	}
	
	public PolyTerm add(PolyTerm pt) {
		if(!this.canAdd(pt))
			throw new IllegalArgumentException("cannot add this polyterm");
		Scalar pos=	this.coefficient.add(pt.getCoefficient());
		return new PolyTerm(pos, this.exponent, isRational);
	}
	
	public PolyTerm mul(PolyTerm pt) {
		Scalar pos=	this.coefficient.mul(pt.getCoefficient());
		return new PolyTerm(pos, this.exponent+pt.getExponent(), isRational);
	}
	
	public Scalar evaluate(Scalar scalar) {
		return (scalar.pow(this.exponent)).mul(this.coefficient);
	}
	
	public PolyTerm derivate() {
		Scalar pos=this.coefficient.mul(this.exponent);
		if(this.exponent>0)
			return new PolyTerm(pos,this.exponent-1, isRational);
		if(this.exponent>=0)
			return new PolyTerm(pos,this.exponent, isRational);
		throw new IllegalArgumentException("the exponent wrong, less than 0");
	}
	
	public boolean equals(PolyTerm pt) {
		boolean isEqual=false;
		if(pt.getIsRational()==this.isRational) {
			if(pt.getCoefficient().equals(coefficient) && pt.getExponent()==exponent)
				isEqual=true;
		}
		return isEqual;
	}
	
	public int getExponent() {
		return this.exponent;
	}
	public void setExponent(int exponent) {
		this.exponent=exponent;
	}
	public Scalar getCoefficient() {
		return this.coefficient;
	}
	public void setCoefficient(Scalar coefficient) {
		this.coefficient=coefficient;
	}
	public boolean getIsRational() {
		return isRational;
	}
	public void setIsRational(boolean rational) {
		isRational=rational;
	}
	
	public String toString() {
		if(this.exponent>=1 && !coefficient.toString().equals("1")) 
			return this.coefficient.toString()+"x^"+this.exponent;
		if(this.exponent>=1 && coefficient.toString().equals("1")) 
			return "x^"+this.exponent;
		return this.coefficient.toString();
	}

	@Override
	public int compareTo(PolyTerm o) {
		return this.exponent-o.getExponent();
	}
}
