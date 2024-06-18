public class linkedList <T> {
	public Node <T> head ;
	public Node <T> current;
	public int nbe ;
	
	
	
	
	public void insert(T d) {
		Node<T> n = new Node<>(d);
		if(isempty())
		{
			head = n ;
			current = head;
			nbe++;
			return ;
			}
		Node<T> t = current.next;
		current.next=n;
		current =current.next ; 
		current.next=t;
		nbe++;
	}
	public T retrieve() {
		return current.data;
	}
	public boolean last() {
		return current.next == null;
	}
	
	public boolean isempty() {
		return head == null ;
	}
	
	
	public void findfirst() {
		current = head ;
	}
	public void findnext() {
		current = current.next;
	}
	
}
	
	
	
	
	
	