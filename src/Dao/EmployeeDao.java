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



import Model.Employee;

import Utils.ConnectionHelper;

public class EmployeeDao {
	
	public List<Employee> findAll() {
		 List<Employee> list = new ArrayList<Employee>(); 
		 Connection con = null;
		try { 
		
		 con = ConnectionHelper.getConnection();

		Statement st = con.createStatement();
		ResultSet resultset;
		resultset = st.executeQuery("select * from Employees");
           
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

		
		System.out.println("list size: "+list.size());
          return list;        
    }
	
	
	public Employee create(final Employee employee) {
		System.out.println("employee.getEmployeeid(): "+employee.getId());
				int counter = 0;
				Connection con = null;

				try {

					 con = ConnectionHelper.getConnection();

					Statement st = con.createStatement();
					ResultSet resultset;
					resultset = st.executeQuery("select count(id) as count from Employees");
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
							"INSERT INTO Employees (name, email, username, password,id) VALUES (?, ?, ?, ?, ?)");
					ps.setString(1, employee.getName());
					ps.setString(2, employee.getEmail());
					ps.setString(3, employee.getUsername());
					ps.setString(4, employee.getPassword());
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
				return employee;

			}
	
	public boolean remove(final int id) { 
		Connection c = null; 
		try { 
			c = ConnectionHelper.getConnection();
			PreparedStatement ps =c.prepareStatement("DELETE FROM Employees WHERE id=?");
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
	
	public Employee findById(final int id) {

		String sql = "SELECT * FROM Employees WHERE id = ?";
		Employee employee = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employee = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(c);
		}

		return employee;

	}
	
	 public Employee update(final Employee employee) { 
		 Connection c = null; 
		 System.out.println("updating event id: "+employee.getId());
		 try {
			 c =ConnectionHelper.getConnection(); 
			 PreparedStatement ps = c.prepareStatement( "UPDATE Employees SET name = ?, email= ?, username= ?, password= ? WHERE id=?" ); 
			 ps.setString(1, employee.getName());
			 ps.setString(2, employee.getEmail());
			 ps.setString(3, employee.getUsername());
			 ps.setString(4, employee.getPassword());
			 ps.setInt(5, employee.getId());
			 
			 int cg = ps.executeUpdate();
			 System.out.println("cg: "+cg);
			 } catch (SQLException e) 
		 { e.printStackTrace(); 
		 } 
		 finally 
		 { ConnectionHelper.close(c); } 
		 return employee; 
		 }
	
	/* public List<Employee> findByName(final String name) {
		 
		
			 
			 
	        
	         List<Employee> list = new ArrayList<Employee>();
	         Connection c = null; 
	         String sql = "select * from Employees " + "WHERE UPPER(name) LIKE ? " + "ORDER BY name"; 
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

	    public Employee findById(final int id) {
	        
	         String sql = "SELECT * FROM Guests WHERE id = ?"; 
	         Employee employee = null;
	         Connection c = null;
	         try { 
	        	 c = ConnectionHelper.getConnection();
	         PreparedStatement ps = c.prepareStatement(sql);
	         ps.setInt(1, id);
	         ResultSet rs = ps.executeQuery();
	         if (rs.next()) 
	         { 
	        	 employee =
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
	         
	         return employee;
	      
	    }

	    public Employee save(final Employee employee) {
	        return employee.getId() > 0 ? update(employee) : create(employee);
	    }

	    public Employee create(
	        final Employee employee) {
	    	
	    	
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
	                           ps.setString(1,employee.getName()); 
	                           ps.setString(2,employee.getPhone());
	                           ps.setString(3,employee.getEmail()); 
	                           ps.setInt(4,employee.getWeight());
	                           ps.setString(5,employee.getDescription());
	                           ps.setInt(6,counter);
	                           ps.executeUpdate();
	                           ResultSet rs = ps.getGeneratedKeys(); 
	                           rs.next();
	                           // Update the id in the returned object. This is important as this // value must be returned to the client.
	                           int id = rs.getInt(1); employee.setId(id); 
	                           }
	                           catch (Exception e) { e.printStackTrace(); throw
	                           new RuntimeException(e); } finally {
	                           ConnectionHelper.close(c); } return employee;

	    }

	    public Employee update(final Employee employee) {
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(
	                "UPDATE Guests SET name=?, phone=?, email=?, weight=?, description=? WHERE id=?");
	            ps.setString(1, employee.getName());
	            ps.setString(2, employee.getPhone());
	            ps.setString(3, employee.getEmail());
	            ps.setInt(4, employee.getWeight());
	            ps.setString(5, employee.getDescription());
	            ps.setInt(6, employee.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            ConnectionHelper.close(c);
	        }
	        return employee;
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
	
	
	 protected Employee processRow(final ResultSet rs) throws SQLException { Employee
	     employee = new Employee(); 
	 employee.setId(rs.getInt("id"));
	     employee.setName(rs.getString("name"));
employee.setEmail(rs.getString("email"));
employee.setUsername(rs.getString("username"));
employee.setPassword(rs.getString("password"));
	     return employee; 
	     }
	    

}
