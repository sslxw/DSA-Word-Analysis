

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordAnalysis {
	public linkedList <WordInformation> arrayOfdiffrentlengths[];
	public	WordInformation Uniquewords[];
	public int nbu ; 
	public int nbw ;  
	
	
	
	
	
	public void ins(String w , int l , int p) {
		int index = w.length();
		if( arrayOfdiffrentlengths[index] == null)
			 arrayOfdiffrentlengths[index] = new linkedList <WordInformation>();
		
		linkedList <WordInformation> t = arrayOfdiffrentlengths[index];
		
		if(t.isempty()) {
			t.insert(new WordInformation(w,l,p));
			nbw++;
			nbu++;
			return;
			}
		t.findfirst();
		while(!t.last()) {
			if(t.retrieve().w.equalsIgnoreCase(w))
				{t.retrieve().addOccur(l, p);
				t.nbe++;
				nbw++;
				return ;}
			t.findnext();
			
		}
		if(t.retrieve().w.equalsIgnoreCase(w))
			{t.retrieve().addOccur(l, p);
			nbw++;
			t.nbe++; // for operation 4 
			return ;}
		
		t.insert(new WordInformation(w,l,p));
		nbw++;
		nbu++;
		}
	

	
	
	
	
	
	public int documentLength() {
		return nbw;
	}
	
	public int uniqueWords() {
		return nbu;
	}
	
	public int totalOccurancesForWord(String w) {
		int index = w.length();
		if(index>=arrayOfdiffrentlengths.length)
			return 0;
		if(arrayOfdiffrentlengths[index]==null)
			return 0 ;
		linkedList<WordInformation> t = arrayOfdiffrentlengths[index];
		t.findfirst();
		while(!t.last()) {
			if(t.retrieve().w.equalsIgnoreCase(w))
				return t.retrieve().occlist.nbe;
			
			t.findnext();
		}
		if(t.retrieve().w.equalsIgnoreCase(w))
			return t.retrieve().occlist.nbe;
		return 0 ;
		
	}
	
	
	public int totalWordsForLength(int l) {
		if(l>arrayOfdiffrentlengths.length-1||arrayOfdiffrentlengths[l]==null)
			return 0 ;
		return arrayOfdiffrentlengths[l].nbe;
		
	}
	
	public void displayUniqueWords() {
		for (int i = 0 ;i<Uniquewords.length;i++)
			System.out.println("( "+Uniquewords[i].w +" , "+Uniquewords[i].occlist.nbe+" )");
			
	}
	
	
	
	
	
	public void readfromfile(String fname) {
		linkedList<String> words = getWordsLLFromFile(fname);
		int k = getKofll(words);
		arrayOfdiffrentlengths = (linkedList<WordInformation>[]) new linkedList<?>[k+1];
		int l = 1 ;
		int p = 1;
		words.findfirst();
		while(!words.last()) {
			if(words.retrieve().equalsIgnoreCase("\n")) {
				l++;
				p=1;
			}
			else
				ins(words.retrieve(),l,p++);
			words.findnext();
				}
		int c = 0 ;
		Uniquewords = new WordInformation[nbu];
		for(int i =0 ; i<arrayOfdiffrentlengths.length;i++) {
			if(arrayOfdiffrentlengths[i]==null)
				continue;
			linkedList<WordInformation> t = arrayOfdiffrentlengths[i];
			t.findfirst();
			while(!t.last()) {
				addUnique(t.retrieve(),c++);
				t.findnext();
			}
			addUnique(t.retrieve(),c++);
		}
		
}
	
	
	public linkedList<WordOccurrence> occurrences(String w){
		
		int index = w.length();
		if(index>=arrayOfdiffrentlengths.length)
			return null;
		if(arrayOfdiffrentlengths[index]==null)
			return null ;
		linkedList<WordInformation> t = arrayOfdiffrentlengths[index];
		t.findfirst();
		while(!t.last()) {
			if(t.retrieve().w.equalsIgnoreCase(w))
				return t.retrieve().occlist;
			
			t.findnext();
		}
		if(t.retrieve().w.equalsIgnoreCase(w))
			return t.retrieve().occlist;
		return null ;
		
	}
	
	
	public void addUnique(WordInformation w , int c) {
		int i = 0;
		while(i<c && w.occlist.nbe<=Uniquewords[i].occlist.nbe) {
			i++;
			}
		if(i==c) {
			Uniquewords[i]=w;
			return;
		}
		for(int j = c-1 ; i <=j ;j--) {
			Uniquewords[j+1]=Uniquewords[j];
		}
		Uniquewords[i]=w;
		}
	
	
	public int getKofll(linkedList<String> words) {
		int k = 1;
		words.findfirst();
		while(!words.last()) {
			if (words.retrieve().length()>k)
				k = words.retrieve().length();
			words.findnext();}

		return k ;
		
	}
		
	
	public linkedList<String> getWordsLLFromFile(String fname) {
		linkedList<String> words = new linkedList<>();
			try {
		    File myObj = new File(fname);
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	String data = myReader.nextLine();
		    	String l[] = data.split(" ");
		    	for(int i = 0 ; i<l.length ; i++) {
		    		String m = filter(l[i]);
		    		if(!m.equalsIgnoreCase(""))
		    			words.insert(m);
		    		}
		    	words.insert("\n");
		    								
		     }
		    myReader.close();
		    return words;
		  } catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		    return null;
		  }
		}
	public String filter(String w) {
		String[] pun = {"." , "," , "?" , "!" , ":" , ";", "(" , ")" , "[" , "]" ,"{" ,"}" , "/","\"","”"};//
		for(int i =0 ; i<pun.length;i++) {
			if(w.contains(pun[i])){
				w = w.replace(pun[i], "");
			}
		}
		return w ;
	}
	
	
public boolean checkAdjacent(String a, String b) {
	linkedList<WordOccurrence> al = occurrences(a);
	linkedList<WordOccurrence> bl = occurrences(b);
	if(al==null ||bl==null)
		return false;
	
	
	al.findfirst();
	bl.findfirst();
	return check(al,bl);
	
	
	
}

public boolean check(linkedList<WordOccurrence> a,linkedList<WordOccurrence> b) {
	if (a.retrieve().lineno==b.retrieve().lineno &&( a.retrieve().position==b.retrieve().position+1 ||a.retrieve().position==b.retrieve().position-1 ))
		return true ;
	if(a.last()&&b.last())
		return false;
	if(a.last()) {
		b.findnext();
		return check(a,b);
		
	}
	if(b.last()) {
		a.findnext();
		return check(a,b);
		
	}
	
	
	if(a.retrieve().lineno<b.retrieve().lineno) {
		a.findnext();
		return check(a,b);
	}
	else if(a.retrieve().lineno>b.retrieve().lineno) {
		b.findnext();
		return check(a,b);
		}
	else if(a.retrieve().position<b.retrieve().position) {
		a.findnext();
		return check(a,b);}
	b.findnext();
	return check(a,b);
	
		
	
	
}
	
	
	
	
	

}