package facebookapplic;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class GetTweets
 */
@WebServlet("/gettweets")
public class GetTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTweets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().print("In gettweets servlet");
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Tweet"); 
		String author = request.getParameter("author");
	System.out.println(author);
		        PreparedQuery pq = ds.prepare(q); 
		        ArrayList<String> arr = new ArrayList<String>();
		        
		        
		    for (Entity result : pq.asIterable()) {   
		    	if(author.equals(result.getProperty("author"))) {
		    		
		    	String date = (String) result.getProperty("DateCreated");
		    	arr.add(date);
		       String tweet = (String) result.getProperty("message");  
		       arr.add(tweet);
		    	}
		    }
		       //String lastName = (String) result.getProperty("lastName");   
		       //Long height = (Long) result.getProperty("Name");   
		       
		
		    
		   //response.getWriter().println(arr.toString());
		    response.getWriter().write(arr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

