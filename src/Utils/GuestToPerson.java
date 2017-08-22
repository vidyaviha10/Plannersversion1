package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.GuestDao;
import Model.Guest;
import Model.GuestReport;
import Model.TableReports;
import version2.SolveSeating;

public class GuestToPerson {
	

	 public static List<Guest> guests = new ArrayList();
static public void generateData(int eventid, int tablesize)
{

	GuestDao dao = new GuestDao();
	  String table = "event_guests"+eventid;
	  guests= dao.findAll(table);

	  SolveSeating.startsolving(tablesize);
		  /* guests.add(new Guest(1,"A",10000.0,"2-3","4"));
		   
		   guests.add(new Guest(2,"B",10000.0,"1-3","4"));
		   
		   guests.add(new Guest(3,"C",5000.0,"2-1","4"));
		   
		   guests.add(new Guest(4,"D",5000.0,"5","6"));
		   
		   guests.add(new Guest(5,"E",10000.0,"4","6"));
		   
		   guests.add(new Guest(6,"F",50.0,"10",""));
		   
		   guests.add(new Guest(7,"G",0.0,"",""));
		   
		   guests.add(new Guest(8,"H",4000.0,"","1"));
		   
		   guests.add(new Guest(9,"I",5000.0,"","2"));
		   
		   guests.add(new Guest(10,"J",5.0,"6","4"));*/
         
	
	  /*for(Guest guest: guests)
	  {
		  person p = new person();
		  p.setName(guest.getName());
		  p.setValue(1.0);
		  p.setWeight(guest.getWeight());
		  p.setId(guest.getId());
		  persons.add(p);
		  toSit.put(guest.getId(), value)
	  }*/
	  
	  
}

public static List<GuestReport> generateguestwisereport()
{
	
	//List<Guest> allGuests = new GuestDao().findAllSorted();
	List<GuestReport> reports = new ArrayList();
List<String> m = new ArrayList();
	try
	{
		BufferedReader fileReader = null;
		fileReader = new BufferedReader(new FileReader("Seating.txt"));
		String line = "";
int counter = 1;
		while ((line = fileReader.readLine()) != null) {
m.add(line);

String temp[]  = line.split(",");
for(String s:temp)
{
	GuestReport report = new GuestReport();
	report.setName(s);
	report.setTableno(counter);
	String seatedwith = "";
	for(String ss:temp)
	{
		if(!ss.equals(s))
		{
			if(seatedwith.equals(""))
			{
				seatedwith = ss;
			}
			else
			{
				seatedwith = seatedwith+","+ss;
			}
		}
	}
	report.setSeatedWith(seatedwith);
	reports.add(report);
}
counter++;
		}
		fileReader.close();

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return reports;
	
	
/*	
	
	for(Guest g:allGuests)
	{
		
		report.setName(g.name);
		for(int i =0;i<m.size();i++)
		{
			String temp[] = m.get(i).split(",");
			String seatedWith = "";
			for(String h:temp)
			{
				if(h.equals(g.id+""))
				{
					
				}
				else
				{
					seatedWith = seatedWith +","+
				}
			}
		}
	}
	*/
}

public static List<TableReports> generatetablewisereport()
{
	
	//List<Guest> allGuests = new GuestDao().findAllSorted();
	List<TableReports> reports = new ArrayList();
	try
	{
		BufferedReader fileReader = null;
		fileReader = new BufferedReader(new FileReader("Seating.txt"));
		String line = "";
int counter = 1;
		while ((line = fileReader.readLine()) != null) {
			
	TableReports report = new TableReports();
			report.tableno = counter;
			report.guests = line;
			reports.add(report);
			counter++;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return reports;
}

public static void main(String a[])
{
	//new GuestToPerson().generateData(17);
}

}
