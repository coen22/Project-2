import java.util.ArrayList;


public class LineStack<E> implements StackADT<E>{
	private ArrayList<E> list;
	
	@Override
	public void push(E e) {
		list.add(0, e);
	}

	@Override
	public E pop() {
		return list.remove(0);
	}

	@Override
	public Integer size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return (list.size() == 0);
	}

}
