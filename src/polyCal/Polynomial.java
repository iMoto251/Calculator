package polyCal;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Polynomial {
	
	private List<PolyTerm> list;
	//private boolean isRational; //set to true if rational scalar. False is real scalar
	
	public Polynomial(String polynom) { //Constructor
		this.list=new LinkedList<>();
		String currentPoly=""; // resets current polynomial to prepare for new entry
		
		for(int i=0;i<polynom.length();i++)
		{
			if((polynom.charAt(i)=='+' || polynom.charAt(i)=='-') &&  i!=0) {
				currentPoly=polynom.substring(0, i);
				PolyTerm poly=new PolyTerm(currentPoly);
				list.add(poly);
				int end=polynom.length();
				polynom=polynom.substring(i,end);
				i=0;
			}
			
			else if(i==polynom.length()-1){
				currentPoly=polynom.substring(0, i+1);
				PolyTerm poly=new PolyTerm(currentPoly);
				list.add(poly);
			}
		}					
	}
	
	public Polynomial(Polynomial poly) { //copy constructor
		this.list=new LinkedList<>();
		
		Iterator<PolyTerm> it=poly.list.iterator();
		while(it.hasNext()) 
			list.add(it.next());
	}
			
			
	public Polynomial add(Polynomial poly) {
		Polynomial pos=new Polynomial(this);
		Iterator<PolyTerm> it1;		
		Iterator<PolyTerm> it2=poly.list.iterator();
		
		while(it2.hasNext()) {
			boolean found=false;
			PolyTerm current2=it2.next();
			it1=pos.list.iterator();	
			while(it1.hasNext() && !found) {
				PolyTerm current1=it1.next();
				if(current1.canAdd(current2)) {
					pos.list.remove(current1);
					pos.list.add(current1.add(current2));
					found=true;
				}	
			}
			if(!found)
				pos.list.add(current2);
		}
		return pos;
	}
	
	
	private void addPolyTerm(PolyTerm pt) {
		Polynomial new_poly=new Polynomial(this);
		Iterator<PolyTerm> it=new_poly.getList().iterator();
		boolean inserted=false;
		
		while(it.hasNext()) {
			PolyTerm p=it.next();
			if(pt.getExponent()==p.getExponent()) {
				this.list.remove(p);
				this.list.add(p.add(pt));
				inserted=true;
			}
		}
		if(!inserted)
			this.list.add(pt);
	}
	
	
	private Polynomial unite(Polynomial poly) {
		Polynomial output=new Polynomial("");
		Iterator<PolyTerm> it=poly.getList().iterator();
		
		while(it.hasNext()) {
			output.addPolyTerm(it.next());
		}
		return output;
	}
	
	
	public Polynomial mult(Polynomial poly) {
		Polynomial pos=new Polynomial("");
		Iterator<PolyTerm> it1;		
		Iterator<PolyTerm> it2=poly.list.iterator();
		
		while(it2.hasNext()) {
			PolyTerm current2=it2.next();
			it1=this.list.iterator();	
			while(it1.hasNext()) {
				PolyTerm current1=it1.next();
				pos.list.add(current1.mult(current2));
			}	
		}
		return unite(pos);
	}
	
	
	public Scalar evaluate(Scalar scalar) {
		Iterator<PolyTerm> it1=this.list.iterator();
		Scalar ans=new Scalar(0);
		
		while(it1.hasNext()) {
			ans=ans.add(it1.next().evaluate(scalar));
		}
		return ans;	
	}
	
	
	public Polynomial derivative() {
		Polynomial pos=new Polynomial("");
		Iterator<PolyTerm> it1=this.list.iterator();
		while(it1.hasNext()) 
			pos.list.add(it1.next().derivative());
		return pos;
	}
	
	
	public boolean equals(Polynomial poly) {
		Collections.sort(this.list);
		Collections.sort(poly.list);
		
		Iterator<PolyTerm> it1=this.list.iterator();
		Iterator<PolyTerm> it2=poly.list.iterator();
		
		while(it1.hasNext()) {
			if(!it1.next().equals(it2.next()))
				return false;
		}
		return true;
	}
	
	
	public List<PolyTerm> getList(){ return this.list; }
	public void setList(List<PolyTerm> list){ this.list=list; }
	
	
	public String toString() {
		Collections.sort(list);
		Iterator<PolyTerm> it=this.list.iterator();
		String output="";
		PolyTerm current;
		while(it.hasNext()) {
			current=it.next();
			if(current.toString().charAt(0)!='0') {
				if(current.toString().charAt(0)=='-')
					output+=current.toString();
				else
					output+="+"+current.toString();
			}
		}
		if(output.charAt(0)=='+')
			return output.substring(1);
		return output;
	}
}
