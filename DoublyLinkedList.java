import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------

//}
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */


class DoublyLinkedList<T extends Comparable<T>>
{
	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
	}

	public int getSizeOfList()
	{
		int size=0;
		DLLNode current = head;
		while (current!=null)
		{
			size++;
			current = current.next;
		}
		return size;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification: We are not running through any loops (while, for, etc.) or anything, we are just checking if 
	 * one thing is true/false with an if statement which should just take an asymptotic running time of 1.
	 */
	public boolean isEmpty()
	{
		if (head == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: Theta(n) 
	 *
	 * Justification: I am using one for loop which will cost us (n) time at worst if it runs through the entire list. 
	 * Nothing else will cost as much as this as it is just if-statements, etc.
	 */
	public void insertBefore( int pos, T data ) 
	{
		DLLNode node = new DLLNode(data, null, null);
		if (isEmpty())
		{
			head = node;
			tail= node;
		}
		else if (pos <= 0)
		{
			DLLNode oldHead = head;
			head = node;
			node.next = oldHead;
			oldHead.prev = head;
		}
		else if (pos >= getSizeOfList())
		{
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		else
		{
			DLLNode prevNode = head;
			DLLNode nextNode = prevNode.next;
			for (int i=1; i<=pos; i++)
			{
				if (i==pos)
				{
					break;
				}
				prevNode = prevNode.next;
				nextNode = nextNode.next;
			}
			node.prev = prevNode;
			node.next = nextNode;
			nextNode.prev = node;
			prevNode.next = node;
		}
		return;
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Theta(n)
	 *
	 * Justification:I am using one for loop which will cost us (n) time at worst if it runs through the entire list. 
	 * Nothing else will cost as much as this as it is just if-statements, declarations, etc.
	 *
	 */
	public T get(int pos) 
	{
		if (pos < 0 || pos >= this.getSizeOfList())
		{
			return null;
		}
		else
		{
			DLLNode current = head;
			for (int i=0; i<=pos; i++)
			{
				if (i==pos)
				{
					break;
				}
				current = current.next;
			}
			return current.data;
		}
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: Theta(n)
	 *
	 * Justification: We use a single for loop which will cost (n) time at worst if it runs through the entire list. 
	 * Nothing else will cost as much as this as it is just if-else statements, etc.
	 */
	public boolean deleteAt(int pos) 
	{
		boolean deleted;
		int listSize = getSizeOfList();
		if (pos < 0 || pos >= listSize || isEmpty())
		{
			deleted = false;
		}
		else
		{
			if (pos == 0)
			{
				head = head.next;
				if (listSize>1)
				{
					head.prev = null;
				}
			}
			else if (pos == listSize-1)
			{
				tail = tail.prev;
				tail.next = null;
			}
			else
			{
				DLLNode prevNode = head;
				DLLNode nextNode = (prevNode.next).next;
				for (int i=1; i<=pos; i++)
				{
					if (i==pos)
					{
						break;
					}
					prevNode = prevNode.next;
					nextNode = nextNode.next;
				}
				prevNode.next = nextNode;
				nextNode.prev = prevNode;
			}
			deleted = true;
		}
		return deleted;
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: TODO
	 *
	 * Justification:I am using one while loop which will cost us (n) time as it runs through the entire list. 
	 * Nothing else will cost as much as this as it is just assignments/declarations, etc.
	 *  TODO
	 */
	public void reverse()
	{
		DLLNode currentNode = head;
		DLLNode temp;
		while (currentNode !=null)
		{
			temp = currentNode.prev;
			currentNode.prev = currentNode.next;
			currentNode.next = temp;
			currentNode = currentNode.prev;
		}
		temp = head;
		head = tail;
		tail = temp;
	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements unique.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: Theta (n²)
	 *
	 * Justification: It is n² (n-squared) as we use 2 for loops, one is nested in the other. Each of those costs n so n x n is n². 
	 * Nothing else costs as much / more than that as it is just if statements inside etc.
	 */
	public void makeUnique()
	{
		DLLNode currentNode = head;
		DLLNode otherNode = currentNode;
		int size = getSizeOfList();
		for (int n = 0; n<size;n++)
		{
			for (int i=n+1;i<size;i++)
			{
				otherNode = otherNode.next;
				if (otherNode.data == currentNode.data)
				{
					(otherNode.prev).next = otherNode.next;
					if (otherNode.next!=null)
					{
						(otherNode.next).prev = otherNode.prev;
					}
					else
					{
						tail = otherNode.prev;
					}
					size--;
					i--;
				}
			}
			currentNode = currentNode.next;
			otherNode = currentNode;
		}
	}


	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification: All I am doing is assigning values to variables and things of this nature. I am not running through anything.
	 */
	public void push(T item) 
	{
		DLLNode oldHead = head;
		head = new DLLNode(item, null, oldHead);

	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification: All I am doing is assigning values to variables and things of this nature. I am not running through anything,
	 * including when I call isEmpty() as that is also just a running time of Theta(1)
	 */
	public T pop() 
	{
		T item = null;
		if (!isEmpty())
		{
			item = head.data;
			head = head.next;
		}
		return item;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification: I am just assigning values to variables and things of this nature which costs 1. 
	 * I am not doing anything more costly, including when I call isEmpty() as that is also just a running time of Theta(1)
	 */
	public void enqueue(T item) 
	{
		DLLNode oldTail = tail;
		tail = new DLLNode(item, null, null);
		if (isEmpty()) 
		{
			head = tail;
		}
		else 
		{
			oldTail.next = tail;
		}
	}

	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an enqueue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification: I am just assigning values to variables and things of this nature which costs 1. 
	 * I am not doing anything more costly, including when I call isEmpty() as that is also just a running time of Theta(1)
	 */
	public T dequeue() 
	{
		T item = null;
		if (isEmpty())
		{
			tail = null;
		}
		else
		{
			item = head.data;
			head = head.next;
		}
		return item;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

	/* public static void main(String[] args)
	{
		DoublyLinkedList<Integer> newList = new DoublyLinkedList<Integer>();
        newList.insertBefore(0,4);
        newList.insertBefore(0,3);
        newList.insertBefore(0,2);
        newList.insertBefore(0,1);
	 	newList.reverse();
	 	newList.reverse();
		System.out.println( newList.toString() + ", " + "(" + newList.getSizeOfList() + ")" + ", " + newList.isEmpty() + ", " + newList.get(2));
	} */
}