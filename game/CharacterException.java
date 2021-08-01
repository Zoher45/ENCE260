package game;

/**
* This class extends IllegalArgumentException to throw error that characters given are not as required
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class CharacterException extends IllegalArgumentException{
	
    public CharacterException() {}
    
    public CharacterException(String message) {
    	
    	//returns to location from which it was called (parent)
        super(message);
        
    }
}
