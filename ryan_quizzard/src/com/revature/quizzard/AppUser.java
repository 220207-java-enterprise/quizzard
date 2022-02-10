package com.revature.quizzard;


public class AppUser {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;

	public Appuser(String firstName, String lastName, String email, 
				   String username, String password) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	} //end of Appuser constructor

	public String getId() 
	{ 
		return id;
	} // end of id getter

	public void setId(String id) 
	{
		this.id = id;
	} //end of id setter

	public String getFirstName() 
	{
		return firstName;
	} //end of firstName getter

    public String getLastName() 
	{
        return lastName;
    }

    public void setLastName(String lastName) 
	{
        this.lastName = lastName;
    }

    public String getEmail() 
	{
        return email;
    }

    public void setEmail(String email) 
	{
        this.email = email;
    }

    public String getUsername() 
	{
        return username;
    }

    public void setUsername(String username) 
	{
        this.username = username;
    }

    public String getPassword() 
	{
        return password;
    }

    public void setPassword(String password) 
	{
        this.password = password;
    }

	public String toFileString()
	{
		return new StringBuilder(id).append(":").append(firstName)
									.append(":").append(lastName)
									.append(":").append(email)
									.append(":").append(username)
									.append(":").append(password)
									.toString();
	}


	@Override
	public boolean equals(Object o) 
	{
		if (this == o) 
		{
			return true;
		}

		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

//		AppUser appUser = (Appuser) o;







}
