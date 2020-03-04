package Claculator;
import java.util.*;

import Poly.Polynomial;
import Scalar.RationalScalar;
import Scalar.RealScalar;
import Scalar.Scalar;
public class Calculator {
	public static void main(String[]args) {
		Scanner reader=new Scanner(System.in); 
		System.out.println("Please select an operation:");
		System.out.println("1.Addition ");
		System.out.println("2.Multiplication");
		System.out.println("3.Evaluation");
		System.out.println("4.Derivate");
		System.out.println("5.Exit");
		int num=reader.nextInt();
		if(num==5) {
			System.out.println("Goodbye friend");
		}
		else {
			System.out.println("Please select the scalar field ");
			System.out.println("Rational (Q) or Real (R)");
			String s=reader.next();
			if(num==1) {
				System.out.println("Please insert the first polynomial ");
				String p1=reader.next();
				System.out.println("Please insert the second polynomial ");
				String p2=reader.next();
				if(s.equals("Q")) {
					Polynomial poly1=new Polynomial(p1,true);
					Polynomial poly2=new Polynomial(p2,true);
					System.out.println(poly1.add(poly2));
				}
				else if(s.equals("R")) {
					Polynomial poly1=new Polynomial(p1,false);
					Polynomial poly2=new Polynomial(p2,false);
					System.out.println(poly1.add(poly2));
				}
				else
					throw new IllegalArgumentException("fail, wrong input inserted!");
			}
			if(num==2) {
				System.out.println("Please insert the first polynomial ");
				String p1=reader.next();
				System.out.println("Please insert the second polynomial ");
				String p2=reader.next();
				if(s.equals("Q")) {
					Polynomial poly1=new Polynomial(p1,true);
					Polynomial poly2=new Polynomial(p2,true);
					System.out.println(poly1.mul(poly2));
				}
				else if(s.equals("R")) {
					Polynomial poly1=new Polynomial(p1,false);
					Polynomial poly2=new Polynomial(p2,false);
					System.out.println(poly1.mul(poly2));
				}
				else
					throw new IllegalArgumentException("fail, wrong input inserted!");
			}
			if(num==3) {
				System.out.println("Please insert the polynomial ");
				String p1=reader.next();
				System.out.println("Please insert the scalar");
				String number=reader.next();
				if(s.equals("Q")) {
					if(number.contains("/")) {
						int numeric=Integer.parseInt(number.substring(0, number.indexOf('/')));
						int div=Integer.parseInt(number.substring(number.indexOf('/')+1, number.length()));
						Polynomial poly1=new Polynomial(p1,true);
						Scalar scalar= new RationalScalar(numeric,div);
						System.out.println(poly1.evaluate(scalar));
					}
					else {
						Polynomial poly1=new Polynomial(p1,true);
						Scalar scalar= new RationalScalar(Integer.parseInt(number),1);
						System.out.println(poly1.evaluate(scalar));
					}
				}
				else if(s.equals("R")) {
					Polynomial poly1=new Polynomial(p1,false);
					Scalar scalar= new RealScalar(Double.parseDouble(number));
					System.out.println(poly1.evaluate(scalar));
				}
				else
					throw new IllegalArgumentException("fail, wrong input inserted!");
			}
			if(num==4) {
				System.out.println("Please insert the polynomial ");
				String p1=reader.next();
				if(s.equals("Q")) {
					Polynomial poly1=new Polynomial(p1,true);
					System.out.println("The derivative polynomial is:");
					System.out.println(poly1.derivate());
				}
				else if(s.equals("R")) {
					Polynomial poly1=new Polynomial(p1,false);
					System.out.println("The derivative polynomial is:");
					System.out.println(poly1.derivate());
				}
				else
					throw new IllegalArgumentException("fail, wrong input inserted!");
			}
		}
	}
}
