public class WordInformation {
	public String w ;
	public linkedList <WordOccurrence> occlist ;
	public int size ;
	public  WordInformation(String d ) {
		w =d ;
		occlist = new linkedList<>();
		}
	public  WordInformation(String d ,int l , int p) {
		w =d ;
		occlist = new linkedList<>();
		occlist.insert(new WordOccurrence(l,p));
		size++;
		}
	
	public void addOccur(int l , int p) {
		occlist.insert(new WordOccurrence(l,p));
		size++;
	}
	
	
	
}
