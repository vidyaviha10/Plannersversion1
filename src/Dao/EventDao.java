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

import Model.Event;
import Utils.ConnectionHelper;

public class EventDao {

	public List<Event> findAll() {
		System.out.println("inside event dao find events");
		List<Event> list = new ArrayList<Event>();
		Connection con = null;
		try {

			 con =  ConnectionHelper.getConnection();

			Statement st = con.createStatement();
			ResultSet resultset;
			resultset = st.executeQuery("select * from Events");

			while (resultset.next()) {
				list.add(processRow(resultset));
			}
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(con);
		}

		try {

			Class.forName("org.sqlite.JDBC");
			con = ConnectionHelper.getConnection();

			

			for (Event event : list) {
				String sql = "select name from Customers where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, event.getCustomerid());
				ResultSet rs = ps.executeQuery();
				event.setCustomerName(rs.getString("name"));
			}
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(con);
		}

		System.out.println(list.size());
		return list;
	}

	public Event findById(final int id) {

		String sql = "SELECT * FROM Events WHERE id = ?";
		Event event = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				event = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(c);
		}

		return event;

	}

	public Event create(final Event event) {
System.out.println("event.getCustomerid(): "+event.getCustomerid());
		int counter = 0;
		Connection con = null;

		try {

			 con = ConnectionHelper.getConnection();

			Statement st = con.createStatement();
			ResultSet resultset;
			resultset = st.executeQuery("select count(id) as count from Events");
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
					"INSERT INTO Events (name, date, venue, noofguests, nooftables,tablesize,customerId,maxPeopleperTable,id) VALUES (?, ?, ?, ?, ?, ?,?,?,?)");
			ps.setString(1, event.getName());
			ps.setString(2, event.getDate());
			ps.setString(3, event.getVenue());
			ps.setInt(4, 0);
			ps.setInt(5, event.getTotalTables());
			ps.setInt(6, event.getTablesize());
			ps.setInt(7, event.getCustomerid());
			ps.setInt(8, event.getMaxperson());
			ps.setInt(9, counter);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			// ConnectionHelper.close(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(c);
		}
		event.setId(counter);
		return event;

	}
	
	public boolean remove(final int id) { 
		Connection c = null; 
		try { 
			c = ConnectionHelper.getConnection();
			PreparedStatement ps =c.prepareStatement("DELETE FROM Events WHERE id=?");
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
	
	 public Event update(final Event event) { 
		 Connection c = null; 
		 System.out.println("updating event id: "+event.getId());
		 try {
			 c =ConnectionHelper.getConnection(); 
			 PreparedStatement ps = c.prepareStatement( "UPDATE Events SET name = ?, date= ?, venue= ?, noofguests= ?, nooftables= ?,tablesize= ?,customerId= ?,maxPeopleperTable= ? WHERE id=?" ); 
			 ps.setString(1, event.getName());
			 ps.setString(2, event.getDate());
			 ps.setString(3, event.getVenue());
			 ps.setInt(4, 0);
			  ps.setInt(5, event.getTotalTables());
			 ps.setInt(6, event.getTablesize());
			 ps.setInt(7, event.getCustomerid());
			 ps.setInt(8, event.getMaxperson());
			 ps.setInt(9, event.getId());
			 
			 int cg = ps.executeUpdate();
			 System.out.println("cg: "+cg);
			 } catch (SQLException e) 
		 { e.printStackTrace(); 
		 } 
		 finally 
		 { ConnectionHelper.close(c); } 
		 return event; 
		 }

	/*
	 * public List<Event> findByName(final String name) {
	 * 
	 * 
	 * 
	 * 
	 * 
	 * List<Event> list = new ArrayList<Event>(); Connection c = null; String
	 * sql = "select * from Events " + "WHERE UPPER(name) LIKE ? " +
	 * "ORDER BY name"; try { c = ConnectionHelper.getConnection();
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setString(1, "%"+
	 * name.toUpperCase() + "%"); ResultSet rs = ps.executeQuery(); while
	 * (rs.next()) { list.add(processRow(rs)); } } catch (SQLException e) {
	 * e.printStackTrace(); throw new RuntimeException(e); } finally {
	 * 
	 * ConnectionHelper.close(c); } return list;
	 * 
	 * }
	 * 
	 * public Event findById(final int id) {
	 * 
	 * String sql = "SELECT * FROM Guests WHERE id = ?"; Event event = null;
	 * Connection c = null; try { c = ConnectionHelper.getConnection();
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setInt(1, id);
	 * ResultSet rs = ps.executeQuery(); if (rs.next()) { event =
	 * processRow(rs); } } catch (Exception e) { e.printStackTrace(); throw new
	 * RuntimeException(e); } finally { ConnectionHelper.close(c); }
	 * 
	 * return event;
	 * 
	 * }
	 * 
	 * public Event save(final Event event) { return event.getId() > 0 ?
	 * update(event) : create(event); }
	 * 
	 * 
	 * 
	
	 * 
	 * 
	 * 
	 */

	protected Event processRow(final ResultSet rs) throws SQLException {
		Event Event = new Event();
		Event.setId(rs.getInt("id"));
		Event.setName(rs.getString("name"));
		Event.setVenue(rs.getString("venue"));
		Event.setDate(rs.getString("date"));
		Event.setCustomerid(rs.getInt("customerId"));
		Event.setTotalTables(rs.getInt("nooftables"));
		Event.setTablesize(rs.getInt("tablesize"));
		Event.setMaxperson(rs.getInt("maxPeopleperTable"));
		return Event;
	}

}
