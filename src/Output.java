//Importing what we need for creating a window
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//Class of Output whichextends the content of JFrame
class Output extends JFrame{

    //Create a socket that will then be connected to server
    public static Socket receiveingSocket = null;

    //Data Stream that will take in the data from a selected source
    public static DataInputStream in = null;

    //The server
    public static ServerSocket ss = null;

    //Will be changed to what key / string was sent from inputs
    public static String outputString = "";
    
    //String that will be the last given input
    public static String LastInput = "";

    //if client disconnected
    public static Boolean creationBool = true;
    


//Our main function
public static void main(String args[])
    {
        //Creates instance of Output, which is the window
        Output window = new Output();

       //Try to Connect
       do { 
           Connect(window, creationBool);
       } while (true);
       

    }

    //Attempt to connect to client
    public static void Connect(JFrame window, Boolean serverCreate)
    {
        //Try to create server
        try
        {
            if(creationBool)
            {
            //Create server at arbitray port
            ss = new ServerSocket(1027);
            creationBool = false;
            }

            //Wait for a client to connect
            receiveingSocket = ss.accept();

            //Confirming client has connected
            System.out.println("Client joined");

            //Will direct flow of inputs from the server's spcket
            in = new DataInputStream(new BufferedInputStream(receiveingSocket.getInputStream()));
        }
        //Error printing
        catch(IOException i)
        {
          System.out.println(i);
        }

        //NEED TO MULTITHREAD
        //Constatnly reads input from the inputs proccess
        ReadInput(window);
        //If this gets disconnected redo
        // receiveingSocket = ss.accept(); and associated code
    }

    //Constructor for Output
    public Output()
    {
        //Sets the title of the window
        setTitle("Output");
        //Lets the window be focusable
        setFocusable(true);
        //Closes program when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets sizze of window
        setSize(500,500);
        //sets window 10px off the top right corner
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
        setLocation(dim.width - getSize().width - 10, 10);
        //sets window visible
        setVisible(true);
    }
    
    //Make multithread later
    public static void ReadInput(JFrame window)
    {
        //While the last input hasn't been an arbitrary close string
        while(!outputString.equals("QUIT"))
        {
            //try to take in inputs from the socket
            try
                {
                    //Store what the input was
                    outputString = in.readUTF();
                    
                    
                    //Basic logic to prove inputs
                    switch(outputString)
                    {
                        //If input "w"
                    	case "w":
                            //To make sure we don't over take inputs, make sure we only take the input again when released
                    		if(LastInput.compareTo("w") !=0) 
                    		{
                    			window.getContentPane().setBackground(Color.BLUE);
                    			System.out.println(outputString);		
                    		}
                        break;
                        
                    	case "a":
                        //To make sure we don't over take inputs, make sure we only take the input again when released
                    		if(LastInput.compareTo("a") !=0) 
                    		{
                    			window.getContentPane().setBackground(Color.RED);
                    			System.out.println(outputString);		
                    		}
                        break;
                        
                    	case "s":
                        //To make sure we don't over take inputs, make sure we only take the input again when released
                    		if(LastInput.compareTo("s") !=0) 
                    		{
                    			window.getContentPane().setBackground(Color.YELLOW);
                    			System.out.println(outputString);		
                    		}
                        break;
                        
                    	case "d":
                        //To make sure we don't over take inputs, make sure we only take the input again when released
                    		if(LastInput.compareTo("d") !=0) 
                    		{
                    			window.getContentPane().setBackground(Color.GREEN);
                    			System.out.println(outputString);		
                    		}
                    	break;
                    	
                    	case "CLEAR":
                        //If a button has been released
                			window.getContentPane().setBackground(Color.WHITE);
                			System.out.println(outputString);
                        break;
                    }
                    
 /*                   
                    if(outputString.equals("w") && LastInput.compareTo("w") !=0)
                    {
                        window.getContentPane().setBackground(Color.BLUE);
                        System.out.println(outputString);
                    }
                    else
                    {
                        window.getContentPane().setBackground(Color.WHITE);
                    }
*/             
                 //Store what the last input was to make sure we dont over take
                  LastInput = outputString;
                }
                //If we couldn't read from sockets print error
                catch(IOException i)
                {
                    System.out.println(i);
                    System.out.println("Client Disconnected!");
                    outputString = "QUIT";
                    in = null;
                    receiveingSocket = null;
                    //Connect(window, false);
                   // creationBool = true;
                }
        }
        outputString = "";
        System.out.println("Client Disconnected!");
    }

}