package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;




import Dao.EventDao;
import Dao.GuestDao;
import Model.Event;
import Model.Guest;
import Model.GuestReport;
import Model.TableReports;
import Utils.GuestToPerson;

@Path("/events")
public class EventOperations {
	
	@Context
    private HttpServletRequest request;
	EventDao dao = new EventDao();

    @GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Event> findAll() {
		System.out.println("findAll events");
		this.request.getSession().removeAttribute("eventId");
		return dao.findAll();
	}
    
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Event findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return dao.findById(Integer.parseInt(id));
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Event create(Event event) {
		System.out.println("creating event ");
		Event e =  dao.create(event);
		this.request.getSession().setAttribute("eventId", event.getId());
		System.out.println("event.getId() "+event.getId());
		return e;
	}
	
	@DELETE @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id) {
		dao.remove(id);
	}
    
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Event update(Event event) {
		System.out.println("Updating event: " + event.getName());
		dao.update(event);
		return event;
	}
	
	@POST  
    @Path("/upload")  
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public void uploadFile(  
            @FormDataParam("file") InputStream uploadedInputStream,  
            @FormDataParam("file") FormDataContentDisposition fileDetail) {  
            String fileLocation = fileDetail.getFileName(); 
            
            if(fileLocation.equals("constraints.csv"))
            {
            	fileLocation = "event_constraints"+(Integer)this.request.getSession().getAttribute("eventId")+".csv ";
            }
                    System.out.println("saving file "+new File("").getAbsolutePath()); 
            try {  
                FileOutputStream out = new FileOutputStream(new File(fileLocation));  
                int read = 0;  
                byte[] bytes = new byte[1024];  
                out = new FileOutputStream(new File(fileLocation));  
                while ((read = uploadedInputStream.read(bytes)) != -1) {  
                    out.write(bytes, 0, read);  
                }  
                out.flush();  
                out.close();  
            } catch (Exception e) {e.printStackTrace();}  
            String output = "File successfully uploaded to : " + fileLocation;  
            //return Response.status(200).entity(output).build(); 
            if(fileLocation.contains("constraints"))
            {
            	
            }
            else
            {
            insertGuestsinDatabase(fileLocation);
            }
        }
	
	
void insertGuestsinDatabase(String file)
{
	BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
GuestDao guestDao = new GuestDao();
    try {

        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
        	
            // use comma as separator
        	System.out.println("line: "+line);
            String[] tempguest = line.split(cvsSplitBy);
            System.out.println("size: "+tempguest.length);
            Guest guest = new Guest();
            guest.setName(tempguest[0]);
            guest.setEmail(tempguest[1]);
            guest.setPhone(tempguest[2]);
         guest.setEventId(((Integer)this.request.getSession().getAttribute("eventId")));
         System.out.println("guest.getEventId()): "+guest.getEventId());
            guest.setDescription(tempguest[3]);
            
            guest.setWeight(getWeight(tempguest[3]));
            
         
            
            
            Guest guest1 = guestDao.create(guest,"event_guests"+guest.getEventId());
            System.out.println("tempguest[0] " + tempguest[0] + " , tempguest[1]=" + tempguest[1] + "]");
            insertConstraints(guest.getId(),"event_guests"+guest.getEventId(),guest.getEventId());
        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


Double getWeight(String relationShip)
{
	if(relationShip.equalsIgnoreCase("father") || relationShip.equalsIgnoreCase("mother"))
	{
		return (double) 10000;
	}
	else if(relationShip.toLowerCase().contains("fatherin") || relationShip.toLowerCase().contains("father-in") ||relationShip.toLowerCase().contains("motherin") || relationShip.toLowerCase().contains("mother-in"))
	{
		return (double) 5000;
	}
	else if(relationShip.equalsIgnoreCase("brother") || relationShip.equalsIgnoreCase("sister"))
	{
		return (double) 4000;
	}
	else if(relationShip.toLowerCase().contains("brotherin") || relationShip.toLowerCase().contains("brother-in") ||relationShip.toLowerCase().contains("sisterin") || relationShip.toLowerCase().contains("sister-in"))
	{
		return (double) 1000;
	}
	else if(relationShip.toLowerCase().contains("cousin"))
	{
		return (double) 200;
	}
	else if(relationShip.toLowerCase().contains("bestfriend"))
	{
		return (double) 80;
	}
	else if(relationShip.toLowerCase().contains("friend"))
	{
		return (double) 50;
	}
	else if(relationShip.toLowerCase().contains("relative"))
	{
		return (double) 5;
	}
	else
	{
		return (double) 0;
	}
	
}

void insertConstraints(int id,String table,int eventId)
{
System.out.println("insertConstraints: " +id);
	BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
GuestDao guestDao = new GuestDao();
    try {

        br = new BufferedReader(new FileReader("event_constraints"+eventId+".csv"));
        while ((line = br.readLine()) != null) {
        	
            // use comma as separator
            String[] tempguest = line.split(cvsSplitBy);
            Guest guest = new Guest();
            guest.setId(Integer.parseInt(tempguest[0]));
            guest.setToSit(tempguest[1]);
            guest.setNottoSit(tempguest[2]);
         guest.setEventId(((Integer)this.request.getSession().getAttribute("eventId")));
         System.out.println("guest.getEventId()): "+guest.getEventId());
           
            
  
            
            
            guestDao.updateGuest(guest,table);
            System.out.println("tempguest[0] " + tempguest[0] + " , tempguest[1]=" + tempguest[1] + "]");

        }
        request.getSession().setAttribute("eventId", eventId);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

@GET @Path("generateSeating/{query}")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public LinkedHashMap generateSeatingArrangement(@PathParam("query") String query) {
	System.out.println("generateSeatingArrangement: " + query);
	Event event = dao.findById(Integer.parseInt(query));
	request.getSession().setAttribute("eventId", Integer.parseInt(query));
	GuestToPerson.generateData(Integer.parseInt(query),event.getMaxperson());
	return null;
}



@GET @Path("generateguestwisereport")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public List<GuestReport> generateSeatingArrangement() {
	System.out.println("generateSeatingArrangement: ");
	
	 List<GuestReport> reports = GuestToPerson.generateguestwisereport();
	return reports;
}




@GET @Path("generatetablewisereport")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public List<TableReports> generatetablewisereport() {
	System.out.println("generatetablewisereport: ");
	
	 List<TableReports> reports = GuestToPerson.generatetablewisereport();
	return reports;
}


@GET @Path("generatenamecards")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public List<Guest> generatenamecards() {
	System.out.println("generatenamecards: ");
	String eventId = ""+(Integer)this.request.getSession().getAttribute("eventId");
	System.out.println("eventId : "+eventId);
	List<Guest> allGuests = new GuestDao().findAllSorted("event_guests"+eventId);
	System.out.println("size: "+allGuests.size());
	return allGuests;
}
	
    /*@GET @Path("search/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Event> findByName(@PathParam("query") String query) {
		System.out.println("findByName: " + query);
		return dao.findByName(query);
	}

	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Event findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return dao.findById(Integer.parseInt(id));
	}




	
	@DELETE @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id) {
		dao.remove(id);
	}*/
}
