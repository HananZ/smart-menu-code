package org.smatmenu.DB;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.smartmenu.model.User;



public class UserDaoImp  extends BasicDAO<User, ObjectId> implements UserDao
{

	
	public UserDaoImp(Class<User> entityClass, Datastore ds) 
	{
		super(entityClass, ds);
	}
	public User getUserByCredentials (String UserName , String pw)
	{
		User userObj = null; 	
		try
		{			
			Query<User> query = createQuery().field("user_name").equal(UserName); 	
			if (query.asList().size()!= 0)
			{					
				//pw = hashPassWord(pw);
			List <User> users = query.asList();            
			if (users.get(0).getPassword().equals(pw) )			
				userObj = users.get(0);			
			}			
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}		
		return userObj; 
	}
	
	
	public String hashPassWord(String pw) throws NoSuchAlgorithmException
	{	
              
		      MessageDigest m = MessageDigest.getInstance("MD5");
		      m.reset();
		      m.update(pw.getBytes());
		      byte[] digest = m.digest();
		      BigInteger bigInt = new BigInteger(1,digest);
		      String hashtext = bigInt.toString(16);
		      return hashtext ;
	}
	
	public User getUserByToken (String token)
	{
		User userObj = null; 	
		try
		{			
			Query<User> query = createQuery().field("token").equal(token); 	
			if (query.asList().size()!= 0)
			{					
				
			List <User> users = query.asList(); 
			userObj = users.get(0);			
			}			
		}
		catch(Exception ex)
		{
			System.out.println("error while adding new Item");
		}		
		return userObj; 
	}
	
}
