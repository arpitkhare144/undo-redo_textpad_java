import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;

class Stack
{
	private String arr[];
	private int arrc[];
	private int top;
	private int capacity;
	public int carr;

	// Constructor to initialize stack
	Stack(int size)
	{
		arr = new String[size];
		arrc = new int[size];
		capacity = size;
		top = -1;
	}

	// Utility function to add an element x in the stack
	public void push(String x,int c)
	{
		if (isFull())
		{
			System.out.println("OverFlow\nProgram Terminated\n");
			System.exit(1);
		}

		System.out.println("Inserting " + x);
		arr[++top] = x;
		arrc[++top] = c;
	}

	// Utility function to pop top element from the stack
	public String pop(Stack s)
	{
		// check for stack underflow
		if (isEmpty())
		{
			System.out.println("UnderFlow\nProgram Terminated");
			System.exit(1);
		}

		System.out.println("Removing " + peek());

		// decrease stack size by 1 and (optionally) return the popped element

		s.carr=(int)arrc[top--];
		return ((String)arr[top--]);
	}

	// Utility function to return top element in a stack
	public String peek()
	{
		if (!isEmpty())
			return arr[top];
		else
			System.exit(1);

		return (" ");
	}

	// Utility function to return the size of the stack
	public int size()
	{
		return top + 1;
	}

	// Utility function to check if the stack is empty or not
	public Boolean isEmpty()
	{
		return top == -1;	// or return size() == 0;
	}

	// Utility function to check if the stack is full or not
	public Boolean isFull()
	{
		return top == capacity - 1;	// or return size() == capacity;
	}


}


//------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------

public class UndoRedo extends JFrame implements KeyListener,ActionListener{
  protected JTextArea textArea = new JTextArea();

  //protected UndoManager undoManager = new UndoManager();

  protected JButton undoButton = new JButton("Undo");

  protected JButton redoButton = new JButton("delete");

  Stack sk=new Stack(1000);

  String s;
  char lsch;

 JMenuBar mb;
 JMenu file;
 JMenuItem open;
 JTextArea ta;





  public UndoRedo()
  {
    super("ARPIT'S TEXT EDITOR");

    undoButton.setEnabled(true);
    redoButton.setEnabled(true);

	open=new JMenuItem("Open File");
    open.addActionListener(this);
	file=new JMenu("File");
	file.add(open);
	mb=new JMenuBar();
	mb.setBounds(0,0,800,20);
	mb.add(file);

    JPanel buttonPanel = new JPanel(new GridLayout());
    buttonPanel.add(undoButton);
    buttonPanel.add(redoButton);
    buttonPanel.add(mb);
	textArea.addKeyListener(this);
    JScrollPane scroller = new JScrollPane(textArea);

    getContentPane().add(buttonPanel, BorderLayout.NORTH);
    getContentPane().add(scroller, BorderLayout.CENTER);


   	undoButton.addActionListener(this);

    redoButton.addActionListener(this);

    setSize(400, 300);
    setVisible(true);
    s=(String)textArea.getText();
  lsch=s.charAt(s.length());


 }


 public void actionPerformed(ActionEvent e)
 {
 	if(e.getSource()==open)
 	{

 		 JFileChooser fc=new JFileChooser();
 		 int i=fc.showOpenDialog(this);
 		 if(i==JFileChooser.APPROVE_OPTION)
 		 {
 		            File f=fc.getSelectedFile();
 		            String filepath=f.getPath();
 		            try
 		            {
 		            BufferedReader br=new BufferedReader(new FileReader(filepath));
 		            String s1="",s2="";
 		            while((s1=br.readLine())!=null){
 		            s2+=s1+"\n";
 		            }
 		            textArea.setText(s2);
 		            br.close();
             	    }
                    catch (Exception ex)
                    {
 				     ex.printStackTrace();
 			        }

	     }

 	}
 	if(e.getSource()==undoButton)
 	{


 			 try {
 			          int nc;
 			          String s22=sk.pop(sk);
 			          String o1=textArea.getText();
 			          int lenn=o1.length();
 			          System.out.println(lenn);
 			          int cc=sk.carr;
 			          String p11=o1.substring(0,cc-1);
 					  //p11.concat(s22);
 			          String p22=o1.substring(cc-1,lenn);
 					  //p11.concat(p22);
 					  textArea.setText(p11);

 			          textArea.append(s22);
 			          textArea.append(p22);

 			      }
 			    catch (Exception cre)
 			    {
 			          cre.printStackTrace();
     			}




 	}
 	if(e.getSource()==redoButton)
 	{

 		try {


 		          String s;
 		          s=(String)textArea.getText();
 		          //s=" ";
 		          int n2=textArea.getCaretPosition();
 		          char st=s.charAt(n2-1);
 				  String stack=st+"";
 				  sk.push(stack,n2);
 				  String p1=s.substring(0,n2-1);
 				  int len=s.length();
 				  if(len>n2)
 				  {
 				  	String p2=s.substring(n2,len);
 				  	String p3=p1.concat(p2);
 				  	textArea.setText(p3);
 			  	  }
 			  	  else
 			  	  {
 					  textArea.setText(p1);
 			      }

 		          //String n2=s.get();


 		    }
 		        catch (Exception cre)
 		        {
 		          cre.printStackTrace();
         		}


 	}
 }
  public void keyPressed(KeyEvent e)
  {
 	  		String s;
 		    s=(String)textArea.getText();
 	  	   if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
 		   {
 		      			//String s;
 			           // s=(String)textArea.getText();
 			            //s=" ";
 			          int n2=textArea.getCaretPosition();
 			          char st=s.charAt(n2-1);
 			  		  String stack=st+"";
 			  		  sk.push(stack,n2);
 			  		  String p1=s.substring(0,n2-1);
 			  		  int len=s.length();
 			  		  if(len>n2)
 			  		  {
 			  		  	String p2=s.substring(n2,len);
 			  		  	String p3=p1.concat(p2);
 			  		  	textArea.setText(p3);
 			  	  	  }
 			  	  	  else
 			  	  	  {
 			  			  textArea.setText(p1);
 			  	      }
 					textArea.append(""+lsch);

     	}
     	if((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
     	{
 			int nc;
 			          String s22=sk.pop(sk);
 			          String o1=textArea.getText();
 			          int lenn=o1.length();
 			          System.out.println(lenn);
 			          int cc=sk.carr;
 			          String p11=o1.substring(0,cc-1);
 					  //p11.concat(s22);
 			          String p22=o1.substring(cc-1,lenn);
 					  //p11.concat(p22);
 					  textArea.setText(p11);

 			          textArea.append(s22);
 			          textArea.append(p22);


 		}

       }
       public void keyReleased(KeyEvent e) {
       }
       public void keyTyped(KeyEvent e) {
     }





   public static void main(String argv[]) {
     new UndoRedo();
  }












}







