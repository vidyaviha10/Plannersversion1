package Dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import Model.Customer;
import Model.Event;
import Model.Customer;
import Utils.ConnectionHelper;

public class CustomerDao {
	
	public List<Customer> findAll() {
		 List<Customer> list = new ArrayList<Customer>(); 
		 Connection con = null;
		try { 
		
		 con = ConnectionHelper.getConnection();

		Statement st = con.createStatement();
		ResultSet resultset;
		resultset = st.executeQuery("select * from Customers");
           
          while (resultset.next()) 
          {
          list.add(processRow(resultset)); 
          } 
          con.close();
          } 
          catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException(e); 
          } finally {
        	  ConnectionHelper.close(con);
          } 
		
		for(Customer cus:list)
		{
			try
			{
			 con = ConnectionHelper.getConnection();
String sql = "select name from events where customerId = ?";

				PreparedStatement ps = con.prepareStatement(sql);
		         ps.setInt(1, cus.getId());
		         ResultSet rs = ps.executeQuery();
				
		           String events = "";
		          while (rs.next()) 
		          {
		        	  if(events.equals(""))
		        	  {
		        		  events = rs.getString("name");
		        	  }
		        	  else
		        	  {
		        		  events = events + " , "+rs.getString("name");
		        	  }
		        
		          } 
		          System.out.println("cus.getId(): "+cus.getId()+" events: "+events);
		      	cus.setEvents(events);
		          con.close();
		          } 
		          catch (Exception e) {
		          e.printStackTrace();
		          throw new RuntimeException(e); 
		          } finally {
		        	  ConnectionHelper.close(con);
		          }
		
		}
		
		System.out.println("list size: "+list.size());
          return list;        
    }
	
	
	public Customer create(final Customer customer) {
		System.out.println("customer.getCustomerid(): "+customer.getId());
				int counter = 0;
				Connection con = null;

				try {

					 con = ConnectionHelper.getConnection();

					Statement st = con.createStatement();
					ResultSet resultset;
					resultset = st.executeQuery("select count(id) as count from Customers");
					counter = resultset.getInt("count");
					counter = counter + 1;
					// ConnectionHelper.close(con);
				} catch (Exception e) {

					throw new RuntimeException(e);
				}
				finally
				{
					 ConnectionHelper.close(con);
				}

				Connection c = null;
				PreparedStatement ps = null;
				try {
					c = ConnectionHelper.getConnection();
					ps = c.prepareStatement(
							"INSERT INTO Customers (name, email, number, gender,id) VALUES (?, ?, ?, ?, ?)");
					ps.setString(1, customer.getName());
					ps.setString(2, customer.getEmail());
					ps.setString(3, customer.getNumber());
					ps.setString(4, customer.getGender());
					ps.setInt(5, counter);
					ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					// ConnectionHelper.close(c);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} finally {
					 ConnectionHelper.close(c);
				}
				return customer;

			}
	
	public boolean remove(final int id) { 
		Connection c = null; 
		try { 
			c = ConnectionHelper.getConnection();
			PreparedStatement ps =c.prepareStatement("DELETE FROM Customers WHERE id=?");
			ps.setInt(1, id);
			 int count = ps.executeUpdate(); 
			 return count == 1; 
			 }
		catch (Exception e)
			  { 
			e.printStackTrace(); 
			return false;
			  } 
		finally {
			  ConnectionHelper.close(c);
			  } 
		}
	
	public Customer findById(final int id) {

		String sql = "SELECT * FROM Customers WHERE id = ?";
		Customer customer = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(c);
		}

		return customer;

	}
	
	 public Customer update(final Customer customer) { 
		 Connection c = null; 
		 System.out.println("updating event id: "+customer.getId());
		 try {
			 c =ConnectionHelper.getConnection(); 
			 PreparedStatement ps = c.prepareStatement( "UPDATE Customers SET name = ?, email= ?, number= ?, gender= ? WHERE id=?" ); 
			 ps.setString(1, customer.getName());
			 ps.setString(2, customer.getEmail());
			 ps.setString(3, customer.getNumber());
			 ps.setString(4, customer.getGender());
			 ps.setInt(5, customer.getId());
			 
			 int cg = ps.executeUpdate();
			 System.out.println("cg: "+cg);
			 } catch (SQLException e) 
		 { e.printStackTrace(); 
		 } 
		 finally 
		 { ConnectionHelper.close(c); } 
		 return customer; 
		 }
	
	/* public List<Customer> findByName(final String name) {
		 
		
			 
			 
	        
	         List<Customer> list = new ArrayList<Customer>();
	         Connection c = null; 
	         String sql = "select * from Customers " + "WHERE UPPER(name) LIKE ? " + "ORDER BY name"; 
	         try {
	        	 c = ConnectionHelper.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql); 
	         ps.setString(1, "%"+ name.toUpperCase() + "%");
	         ResultSet rs = ps.executeQuery(); 
	         while (rs.next()) 
	         { 
	        	 list.add(processRow(rs));
	        	 } 
	         } 
	         catch (SQLException e) 
	         {
	         e.printStackTrace(); 
	         throw new RuntimeException(e); 
	         } finally {
	        	 
	         ConnectionHelper.close(c);
	         } 
	         return list;
  
	    }

	    public Customer findById(final int id) {
	        
	         String sql = "SELECT * FROM Guests WHERE id = ?"; 
	         Customer customer = null;
	         Connection c = null;
	         try { 
	        	 c = ConnectionHelper.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql);
	         ps.setInt(1, id);
	         ResultSet rs = ps.executeQuery();
	         if (rs.next()) 
	         { 
	        	 customer =
	         processRow(rs);
	        	 }
	         } 
	         catch (Exception e)
	         { 
	        	 e.printStackTrace(); 
	        	 throw
	         new RuntimeException(e);
	        	 }
	         finally {
	        	 ConnectionHelper.close(c);
	        	 }
	         
	         return customer;
	      
	    }

	    public Customer save(final Customer customer) {
	        return customer.getId() > 0 ? update(customer) : create(customer);
	    }

	    public Customer create(
	        final Customer customer) {
	    	
	    	
	    	int counter = 0;
			 
			 try { 
					System.out.println(new File("").getAbsolutePath());
				Class.forName("org.sqlite.JDBC");
				Connection con = DriverManager.getConnection(
						"jdbc:sqlite:planners.db");

				
				Statement st = con.createStatement();
				ResultSet resultset;
				resultset = st.executeQuery("select count(id) as count from Guests");
				counter = resultset.getInt("count");
				counter = counter + 1;
				 con.close();
		 }
			 catch(Exception e)
			 {
				
				 throw new RuntimeException(e); 
			 }
	    	
	    	
	                           Connection c = null; PreparedStatement ps = null;
	                           try { c = ConnectionHelper.getConnection(); ps =
	                           c.prepareStatement(
	                           "INSERT INTO Guests (name, phone, email, weight, description,id) VALUES (?, ?, ?, ?, ?, ?)"
	                           );
	                           ps.setString(1,customer.getName()); 
	                           ps.setString(2,customer.getPhone());
	                           ps.setString(3,customer.getEmail()); 
	                           ps.setInt(4,customer.getWeight());
	                           ps.setString(5,customer.getDescription());
	                           ps.setInt(6,counter);
	                           ps.executeUpdate();
	                           ResultSet rs = ps.getGeneratedKeys(); 
	                           rs.next();
	                           // Update the id in the returned object. This is important as this // value must be returned to the client.
	                           int id = rs.getInt(1); customer.setId(id); 
	                           }
	                           catch (Exception e) { e.printStackTrace(); throw
	                           new RuntimeException(e); } finally {
	                           ConnectionHelper.close(c); } return customer;

	    }

	    public Customer update(final Customer customer) {
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(
	                "UPDATE Guests SET name=?, phone=?, email=?, weight=?, description=? WHERE id=?");
	            ps.setString(1, customer.getName());
	            ps.setString(2, customer.getPhone());
	            ps.setString(3, customer.getEmail());
	            ps.setInt(4, customer.getWeight());
	            ps.setString(5, customer.getDescription());
	            ps.setInt(6, customer.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            ConnectionHelper.close(c);
	        }
	        return customer;
	    }

	    public boolean remove(final int id) {
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Guests WHERE id=?");
	            ps.setInt(1, id);
	            int count = ps.executeUpdate();
	            return count == 1;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            ConnectionHelper.close(c);
	        }
	    }
	
	*/
	
	
	 protected Customer processRow(final ResultSet rs) throws SQLException { Customer
	     customer = new Customer(); 
	 customer.setId(rs.getInt("id"));
	     customer.setName(rs.getString("name"));
customer.setEmail(rs.getString("email"));
customer.setNumber(rs.getString("number"));
customer.setGender(rs.getString("gender"));
	     return customer; 
	     }
	    

}
