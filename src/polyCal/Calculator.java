package polyCal;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int operation = -1;

        while (operation != 5) {

            System.out.println("Please select an operation:");
            System.out.println("  1.Addition");
            System.out.println("  2.Multiplication");
            System.out.println("  3.Evaluation");
            System.out.println("  4.Derivative");
            System.out.println("  5.Exit");

            operation = scanner.nextInt();

            String p1;
            String p2;
            String number;

            switch (operation) {

                case 1: {
                            System.out.println("Please insert the first polynomial");
                            p1 = scanner.next();
                            
                            System.out.println("Please insert the second polynomial");
                            p2 = scanner.next();
                            
                            
                            System.out.println("The solution is:");
                            
                            //solution
                            Polynomial poly1=new Polynomial(p1);
        					Polynomial poly2=new Polynomial(p2);
        					System.out.println(poly1.add(poly2));
        					
                            break;
                }    	
                case 2: {
		                	System.out.println("Please insert the first polynomial");
		                    p1 = scanner.next();
		                    
		                    System.out.println("Please insert the second polynomial");
		                    p2 = scanner.next();
		                    
		                    
		                    System.out.println("The solution is:");
                            
                            //solution
		                	Polynomial poly1=new Polynomial(p1);
							Polynomial poly2=new Polynomial(p2);
							System.out.println(poly1.mult(poly2));
							
                            break;
                }
                case 3: {
		                	System.out.println("Please insert the first polynomial");
		                    p1 = scanner.next();
		                    
		                    System.out.println("Please insert the scalar");
		                    number = scanner.next();
		                    
		                    
		                    System.out.println("The solution is:");
                            
                            //solution
		                    Polynomial poly1=new Polynomial(p1);
							Scalar scalar= new Scalar(Double.parseDouble(number));
							System.out.println(poly1.evaluate(scalar));
                            
                            break;
                }
                case 4: {
		                	System.out.println("Please insert the polynomial ");
		    				p1=scanner.next();
		    				
		                	Polynomial poly1=new Polynomial(p1);
		                	
							System.out.println("The derivative polynomial is:");                            
                            //solution
							System.out.println(poly1.derivative());

							break;
                }
                case 5:{
                    break;
                }
                default:{
                	System.out.println("Invalid input");
                }
            }
        }
        scanner.close();
    }
}
