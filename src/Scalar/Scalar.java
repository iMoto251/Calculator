package Scalar;

public interface Scalar {
		
	public Scalar add(Scalar s);
	public Scalar mul(Scalar s);
	public Scalar mul(int num); 
	public Scalar pow(int exponent);
	public Scalar neg();	
	public boolean equals(Scalar s);
	public String toString();
	
}
