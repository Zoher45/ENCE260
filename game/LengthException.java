package game;

/**
* This class extends IllegalArgumentException to check for length error for a given boundary 
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class LengthException extends IllegalArgumentException{

    public LengthException() {}
    
    public LengthException(String message) {
    	
    	//returns to location from which it was called (parent)
        super(message);
        
    }
    
}