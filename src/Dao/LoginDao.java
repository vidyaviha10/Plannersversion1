/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2017. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Credentials;
import Model.Event;
import Utils.ConnectionHelper;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class LoginDao {

    public boolean login(final Credentials credentials) {

		System.out.println("inside login dao login "+credentials.getUserName()+" "+credentials.getPassword());
		List<Event> list = new ArrayList<Event>();
		Connection con = null;
		try {

			 con =  ConnectionHelper.getConnection();

		

			String sql= "Select * from Employees where username = ? and password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, credentials.getUserName());
			ps.setString(2, credentials.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next() )
			{
				return true;
			}
			else
			{
				return false;
			}

			
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			 ConnectionHelper.close(con);
		}

	
    }

}
