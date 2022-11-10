/*
*
* @author charles scruggs @studentid 110612856
* @class CIS 331 @section 1
* Assignment 6
* This work was created honorably and adheres to the JMU honor code.
*
*/
public class User
{
	//first name, last name, user ID, and password variables
	protected String first_name;
	protected String last_name;
	protected String user_id;
	protected String password;
	
	// these variables are used to track and limit the number of users
	public static int totUsers = 0;
	public static final int MAX_USERS = 4;
	protected static User[] users = new User[MAX_USERS];
	
	
	//default contructor initializes user info variables to default values
	User()
	{
		setFirstName("FIRST");
		setLastName("LAST");
		setUserID("Unknown");
		setPass("Unknown");
	}
	
	//overloaded constructor sets user info variables to parameters passed in
	//Mutator methods are used to check the parameters passed in before
	//Initializing user info to these values
	User(String fn, String ln, String uid, String p)
	{
		setFirstName(fn);
		setLastName(ln);
		setUserID(uid);
		setPass(p);
	}

	
	//a method called userInfo that returns a String which contains the information of a User instance
	//a boolean parameter which indicates whether the full data of the user will be included or not
	//If the parameter evaluates to true, then the method should concatenate,
	//with appropriate labels, the first and last names, user id, and password of the userID
	// If the parameter is false, then only the first and last names should be concatenated together 
	//the for the return string, with no labels
	
	public String userInfo(boolean full)
	{
		if (full)
		{
			return toString() + "\tFirst name: " + getFirstName() + " Last name: " + getLastName() + "\n User ID:" + 
					getUserID() + "\n Password: " + getPass() + "\n ";
		}
			
		else
		{
			return getFirstName() + " " + getLastName() + " ";
		}
	}
	
	// tests to see if that parameter’s value is equal to the user’s name.
	//the string should contain the first name, followed by a space, followed by the last name.
	//equals method should then compare this string parameter against the first and last name of the
	//User instance
	//then should return a boolean value depending on that test result
	//the method should be able to match strings even if they are in different cases
	public boolean equal(String testName)
	{
		String fullName = getFirstName() + " " + getLastName();
		
		if (testName.toLowerCase().equals(fullName.toLowerCase()))
		{
			return true;
		}
		
		return false;
	}
	
	//takes a single String password parameter and tests to see if that password is valid
	//length is at least 8 characters
	//includes at least one upper case character
	//includes at least one lower case character
	//includes at least one numeric digit
	//includes at least one special character
	//cannot contain a space
	public static boolean isValidPassword (String testPass)
	{
		boolean hasUp = false;
		boolean haslow = false;
		boolean hasNum = false;
		boolean hasSpec = false;
		
		char letter;
		
		if (testPass.length() < 8)
		{
			return false;
		}
		
		if (testPass.contains(" "))
		{
			return false;
		}
		
		for (int i = 0; i < testPass.length(); i++)
		{
			letter = testPass.charAt(i);
			
			if(Character.isUpperCase(letter))
			{
				hasUp = true;
			}
			else if(Character.isLowerCase(letter))
			{
				haslow = true;
			}
			
			else if(Character.isDigit(letter))
			{
				hasNum = true;
			}

			else if(String.valueOf(letter).matches("[^a-zA-Z0-9\\\\s]"))
			{
				hasSpec = true;
			}
		}
		
		if (hasUp && haslow && hasNum && hasSpec)
		{
			return true;
		}
		
		return false;
	}
	
	// loop through the array listing the names of the users. It should return a String that contains
	// the list of all the users in the array, on separate lines
	public static String listUsers()
	{
		String userLis = "";
		
		for (int i = 0; i < users.length; i++)
		{
			if (users[i] != null)
			{
				userLis += "\t" + users[i].toString() + " " + users[i].getFirstName() + " " + users[i].getLastName() + "(" + users[i].getUserID() + ")";
				
				//adds a newline only if the end of the list has not been reached yet
				if (i < users.length - 1)
				{
					userLis += "\n";
				}
			}
		}
		
		return userLis;
		
	}
	
	// finding a specific user in the array.
	// It should take as a parameter a string containing the first and last name of the desired user, separated by a space.
	// Then it should search the array, looking for a match. 
	// If it finds a match, the method should return the index value for the array element that contains the
	// matching user. 
	// If it cannot find a match, the method returns -1 as a value.
	// it should not matter if the case of the input name matches the case of the user’s name
	public static int findUser(String fullName)
	{
		
		for (int i = 0; i < users.length; i++)
		{
			
			if (users[i].equal(fullName))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	// a method creating a User object and adding it to the array of users
	// It will take as parameters the first name, last name, user id, and password. 
	// A fifth parameter should indicate whether or not the password needs to be hashed. 
	// This method will return a boolean value, which will indicate whether the attempt to add a
	// new user was successful or not.
	// The method should first check to see if the array of users is full. If it is, the method should
	// return false. Otherwise the method should create an instance of the User class using the overloaded
	// constructor.
	public static boolean addUser(String first, String last, String id, String pass, boolean needsHashed)
	{
		// if a null value is found, the full value is set to false, if no null value is found, the full value
		// remains true, and false is returned
		boolean full = true;
		for (User user: users)
		{
			if (user == null)
			{
				full = false;
			}
		}
		
		//the array is full
		if (full)
		{
			return false;
		}
		
		
		User user = new User(first, last, id, pass);
		
		//increments the total number of users, then sets the position in the user array for that user
		users[totUsers] = user;
		
		totUsers += 1;
		
		// If the password does not need to be hashed, replace the password’s value with the passed in parameter directly.
		if (!needsHashed)
		{
			user.password = pass;
		}
		
		//otherwise the pass variable is sent to the setPass method to be hashed and then set to the password member variable
		else
		{
			user.setPass(pass);
		}
		
		//a user has been added successfully so return true
		return true;
	}
	
	// A method for attempting a user login. 
	// it should take two parameters, a user id and a password

	// It should return a string value giving the result of the login attempt based on searching the users
	// array and validating the password.

	// There are four possible results. 
	// Either (a) the password is invalid,
	// (b) the user id does not exist in the array,
	// (c) the user is found but the password does not match,
	// or (d) the login is successful.
	// Each result produced different return string message.
	public static String userLogin(String id, String pass)
	{
		// check if pass is valid
		if (!isValidPassword(pass))
		{
			return "You entered an invalid password";
		}
		
		String itID = "";
		String itPass = "";
		
		//convert password to sha-256
		try
		{
			byte[] bytes = GFG.getSHA(pass);
			pass = GFG.toHexString(bytes);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		for (int i = 0; i < users.length; i++)
		{
			itID = users[i].getUserID();
			itPass = users[i].getPass();
			
			//if the ID is a match, compare the passwords, if its not a match contiue loop
			if (itID.equals(id))
			{
				// compare the passwords
				if (itPass.equals(pass))
				{
					return "Login successful, Welcome " + users[i].userInfo(false);
				}
				else 
				{
					return "User found, but passwords do not match";
				}
			}
		}
		
		//we have reached the end of the loop and no login match was found
		return "This user ID does not exist";
	}
	
	
	//Mutator and accessor methods below
	
	// accessor method for obtaining a particular user from the array of users
	// The return type of this method should be User (that is, it will return a reference to a user object).
	// It should take as a parameter an integer value. This value will serve as an index.
	// method should return the user in the array element of the users array that is indexed by the parameter value.
	// 
	public static User getUser(int index)
	{
		return users[index];
	}
	
	public String getFirstName() {
		return first_name;
	}
	

	public String getLastName() {
		return last_name;
	}
	
	public String getPass() {
		return password;
	}
	
	public String getUserID() {
		return user_id;
	}
	
	//ensures the name is stored in the correct case
	public void setFirstName(String firstName)
	{
		
		char[] arr = firstName.toCharArray();
		String result = "";
		
		char firstLetter = Character.toUpperCase(arr[0]);
		result += firstLetter;
		
		for (int i = 1; i < arr.length; i++)
		{
			result += Character.toLowerCase(arr[i]);
		}
		
		first_name = result;
	}
	
	//ensures the name is stored in the correct case
	public void setLastName(String lastName) {
		
		char[] arr = lastName.toCharArray();
		String result = "";
		
		char firstLetter = Character.toUpperCase(arr[0]);
		result += firstLetter;
		
		for (int i = 1; i < arr.length; i++)
		{
			result += Character.toLowerCase(arr[i]);
		}
		
		last_name = result;
	}
	
	//password must be validated using the isValidPassword method described above
	//If it does not pass the criteria, then it the password should be set to “Unknown”
	// Otherwise, use the cryptographic hashing methods to hash the password and store the hex
	//values in a string into the password variable
	public void setPass(String pass) 
	{
		if (isValidPassword(pass))
		{
			try
			{
				byte[] bytes = GFG.getSHA(pass);
				password = GFG.toHexString(bytes);
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		else
		{
			password = "Unknown";
		}
	}
	
	//The user id’s mutator should ensure that the id has no spaces, and that it begins with an alphabetic
	//letter, that it contains only alphabetic letters or numeric digits and contains no spaces. If the formal
	//parameter does not match these criteria, then the user ID should be set to “Unknown”.
	public void setUserID(String userID)
	{
		char letter = userID.toCharArray()[0];
		
		//checks if there is a symbol in the user id
		for (int i = 0; i < userID.length(); i++)
		{
			char nextletter = userID.toCharArray()[i];
			if (!Character.isLetterOrDigit(nextletter))
			{
				user_id = "Unknown";
				return;
			}
		}
		
		//checks for spaces and first letter alphabetic
		if (userID.contains(" ") || !Character.isAlphabetic(letter))
		{
			user_id = "Unknown";
		}
		
		else
		{
			user_id = userID;
		}
	}
	
	public String toString()
	{
		return "User";
	}
}
