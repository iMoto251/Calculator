package polyCal;

public class PolyTerm implements Comparable<PolyTerm>{
	private Scalar coefficient;
	private int exponent;
	//private boolean isRational; //set to true if rational scalar. False is real scalar
	
	public PolyTerm(Scalar coefficient, int exponent) {
		if(exponent<0) { throw new IllegalArgumentException("Invalid. Negative exponent."); }
		this.coefficient=coefficient;
		this.exponent=exponent;
	}
	
	
	public PolyTerm(String polyterm) {
		if(polyterm==null)
			throw new IllegalArgumentException("Polyterm can't be null");
		
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
			if(coefficient.length()==0 || coefficient.charAt(coefficient.length()-1)=='+') 
				coefficient="1";
			
			else if(coefficient.charAt(coefficient.length()-1)=='-')
				coefficient="-1";
			
			else
				this.coefficient=new Scalar(Double.parseDouble(coefficient));
		
	}
	
		public boolean canAdd(PolyTerm pt) {
			if(pt.exponent==this.exponent)
				return true;
			else
				return false;
	}
	
	public PolyTerm add(PolyTerm pt) {
		if(!this.canAdd(pt))
			throw new IllegalArgumentException("Can't add this term");
		Scalar pos=	this.coefficient.add(pt.getCoefficient());
		
		return new PolyTerm(pos, this.exponent);
	}
	
	public PolyTerm mult(PolyTerm pt) {
		Scalar pos=	this.coefficient.mult(pt.getCoefficient());
		
		return new PolyTerm(pos, this.exponent+pt.getExponent());
	}
	
	public Scalar evaluate(Scalar scalar) {
		return (scalar.pow(this.exponent)).mult(this.coefficient);
	}
	
	public PolyTerm derivative() {
		Scalar pos=this.coefficient.mult(this.exponent);
		if(this.exponent>0)
			return new PolyTerm(pos,this.exponent-1);
		if(this.exponent>=0)
			return new PolyTerm(pos,this.exponent);
		
		throw new IllegalArgumentException("Invalid. Negative exponent.");
	}
	
	public boolean equals(PolyTerm pt) {
		boolean isEqual=false;
		
			if(pt.getCoefficient().equals(coefficient) && pt.getExponent()==exponent)
				isEqual=true;
		
		return isEqual;
	}
	
	public String toString() {
		if(this.exponent>=1 && !coefficient.toString().equals("1")) 
			return this.coefficient.toString()+"x^"+this.exponent;
		
		if(this.exponent>=1 && coefficient.toString().equals("1")) 
			return "x^"+this.exponent;
		
		return this.coefficient.toString();
	}
	
	//getters and setters
	public int 		getExponent() 						{ return this.exponent; }
	public void 	setExponent(int exponent) 			{ this.exponent=exponent; }
	public Scalar 	getCoefficient() 					{ return this.coefficient; }
	public void 	setCoefficient(Scalar coefficient) 	{ this.coefficient=coefficient; }

	@Override
	public int compareTo(PolyTerm o) {
		return this.exponent-o.getExponent();
	}
}
