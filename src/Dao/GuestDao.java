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



import Model.Guest;
import Utils.ConnectionHelper;

public class GuestDao {
	
	public List<Guest> findAll() {
		 List<Guest> list = new ArrayList<Guest>(); 
		try { 
			System.out.println(new File("").getAbsolutePath());
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection(
				"jdbc:sqlite:planners.db");

		Statement st = con.createStatement();
		ResultSet resultset;
		resultset = st.executeQuery("select id,weight,name,phone,email,description from Guests");
           
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
          
          } 
          return list;        
    }
	
	public List<Guest> findAllSorted(String table) {
		 List<Guest> list = new ArrayList<Guest>(); 
		try { 
			System.out.println(new File("").getAbsolutePath());
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection(
				"jdbc:sqlite:planners.db");

		Statement st = con.createStatement();
		ResultSet resultset;
		resultset = st.executeQuery("select * from "+table+" order by name");
          
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
         
         } 
         return list;        
   }
	
	public List<Guest> findAll(String table) {
		 List<Guest> list = new ArrayList<Guest>(); 
		try { 
			System.out.println(new File("").getAbsolutePath());
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection(
				"jdbc:sqlite:planners.db");

		Statement st = con.createStatement();
		ResultSet resultset;
		resultset = st.executeQuery("select * from "+table);
          
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
         
         } 
         return list;        
   }
	
	 public List<Guest> findByName(final String name) {

	         List<Guest> list = new ArrayList<Guest>();
	         Connection c = null; 
	         String sql = "select * from Guests " + "WHERE UPPER(name) LIKE ? " + "ORDER BY name"; 
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
	 
	 public List<Guest> findByEvent(final int eventId) {

         List<Guest> list = new ArrayList<Guest>();
         Connection c = null; 
         String sql = "select * from Guests " + "WHERE eventId = ? " + "ORDER BY name"; 
         try {
        	 c = ConnectionHelper.getConnection();
         PreparedStatement ps = c.prepareStatement(sql); 
         ps.setInt(1, + eventId );
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

	    public Guest findById(final int id) {
	        
	         String sql = "SELECT * FROM Guests WHERE id = ?"; 
	         Guest guest = null;
	         Connection c = null;
	         try { 
	        	 c = ConnectionHelper.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql);
	         ps.setInt(1, id);
	         ResultSet rs = ps.executeQuery();
	         if (rs.next()) 
	         { 
	        	 guest =
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
	         
	         return guest;
	      
	    }

	/*    public Guest save(final Guest guest) {
	        return guest.getId() > 0 ? update(guest) : create(guest);
	    }*/

	    public Guest create(
	        final Guest guest,String table) {
	    	
	    	createTable(table);
	    	
	    	int counter = 0;
			 
			 try { 
					System.out.println(new File("").getAbsolutePath());
				Class.forName("org.sqlite.JDBC");
				Connection con = DriverManager.getConnection(
						"jdbc:sqlite:planners.db");

				
				Statement st = con.createStatement();
				ResultSet resultset;
				resultset = st.executeQuery("select count(id) as count from "+table);
				counter = resultset.getInt("count");
				counter = counter + 1;
				 con.close();
		 }
			 catch(Exception e)
			 {
				
				 throw new RuntimeException(e); 
			 }
	    	System.out.println("while creating id: "+counter);
	    	guest.setId(counter);
	                           Connection c = null; PreparedStatement ps = null;
	                           try { c = ConnectionHelper.getConnection(); ps =
	                           c.prepareStatement(
	                           "INSERT INTO "+table+" (name, phone, email, weight, description,id,eventid,toSit,nottoSit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	                           );
	                           ps.setString(1,guest.getName()); 
	                           ps.setString(2,guest.getPhone());
	                           ps.setString(3,guest.getEmail()); 
	                           ps.setDouble(4,guest.getWeight());
	                           ps.setString(5,guest.getDescription());
	                           ps.setInt(6,counter);
	                           ps.setInt(7,guest.getEventId());
	                           ps.setString(8,guest.getToSit());
	                           ps.setString(9,guest.getNottoSit());
	                           ps.executeUpdate();
	                           ResultSet rs = ps.getGeneratedKeys(); 
	                           rs.next();
	                           // Update the id in the returned object. This is important as this // value must be returned to the client.
	                          // int id = rs.getInt(1); guest.setId(id); 
	                           }
	                           catch (Exception e) { e.printStackTrace(); throw
	                           new RuntimeException(e); } finally {
	                           ConnectionHelper.close(c); } return guest;

	    }

	    public Guest update(final Guest guest) {
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(
	                "UPDATE Guests SET name=?, phone=?, email=?, weight=?, description=? WHERE id=?");
	            ps.setString(1, guest.getName());
	            ps.setString(2, guest.getPhone());
	            ps.setString(3, guest.getEmail());
	            ps.setDouble(4, guest.getWeight());
	            ps.setString(5, guest.getDescription());
	            ps.setInt(6, guest.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            ConnectionHelper.close(c);
	        }
	        return guest;
	    }
	    
	    public Guest updateGuest(final Guest guest,String table) {
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(
	                "UPDATE "+table+" SET toSit=?, nottoSit=? WHERE id=?");
	            ps.setString(1, guest.getToSit());
	            ps.setString(2, guest.getNottoSit());
	         
	            ps.setInt(3, guest.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            ConnectionHelper.close(c);
	        }
	        return guest;
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
	
	
	
	
	 protected Guest processRow(final ResultSet rs) throws SQLException { Guest
	     Guest = new Guest(); 
	 Guest.setId(rs.getInt("id"));
	     Guest.setName(rs.getString("name"));
	     Guest.setPhone(rs.getString("phone"));
	     Guest.setEmail(rs.getString("email"));
	     Guest.setWeight(rs.getDouble("weight"));
	     Guest.setDescription(rs.getString("description")); 
	     Guest.setToSit(rs.getString("toSit")); 
	     Guest.setNottoSit(rs.getString("nottoSit")); 
	     Guest.setEventId(rs.getInt("eventid")); 
	  
	     return Guest; 
	     }
	 
	 void createTable(String table)
	 {
		 
		 
		   String sql = "CREATE TABLE IF NOT EXISTS "+table+" ('name'	TEXT, 'weight'	INTEGER,'id'	INTEGER,'email'	TEXT,'phone'	TEXT,'description'	TEXT,'eventId'	INTEGER,'toSit'	TEXT,'nottoSit'	TEXT)";
		   Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	                Statement stmt = c.createStatement();
	                stmt.execute(sql);
	        }
	         catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	            ConnectionHelper.close(c);
	        }
	 }
	    

}
