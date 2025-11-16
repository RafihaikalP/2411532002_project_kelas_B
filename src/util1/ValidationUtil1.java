package util1;
import error1.ValidationException1;
import Model.User;

public class ValidationUtil1 {
	public static void validate(User user) throws ValidationException1, NullPointerException {
	    if(user.getUsername() == null) {
	        throw new NullPointerException("Username is null");
	    }
	    else if(user.getUsername().isBlank()) {
	        throw new ValidationException1("Username is Blank");
	    }
	    else if(user.getPassword() == null) {
	        throw new NullPointerException("Password is Null");
	    }
	    else if(user.getPassword().isBlank()) {
	        throw new ValidationException1("Password is blank");
	    }
	}

}