package game;

/**
* This class extends IllegalArgumentException to throw error that input is not valid
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class InvalidInputException extends IllegalArgumentException{
	
    public InvalidInputException() {}
    
    public InvalidInputException(String message) {
    	
    	//returns to location from which it was called (parent)
        super(message);
        
    }
    
}