package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CommonUtil;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CommonUtil comm = new CommonUtil();
    	String Id = request.getParameter("id");
    	Integer oldQuantity = comm.StringToInt(request.getParameter("quantity"));
    	String action = comm.getString(request.getParameter("action"));
    	Integer BuyCount = comm.StringToInt(session.getAttribute("BuyCount"));
    	Integer newQuantity = oldQuantity;

    	ArrayList<Map<String,String>> oldBuylist;
    	if((session.getAttribute("Buylist"))==null){
    		oldBuylist = new ArrayList<Map<String,String>>();
    	} else {
    		oldBuylist=(ArrayList<Map<String,String>>)session.getAttribute("Buylist");
    	}
    	
       	ArrayList<Map<String,String>> newBuylist = new ArrayList<Map<String,String>>();
       	Map<String,String> newMap = null;
       	int hasMatch = 0;
       	for (Map<String, String> oldMap : oldBuylist) {
       		newMap = new HashMap<String,String>();
				if(Id.equals(oldMap.get("wineId"))){
					hasMatch ++;
					if(!action.equals("delItems")){
   					newMap.put("wineId", Id);
   		        	if(action.equals("addItems")){
	   					newQuantity = Integer.parseInt(oldMap.get("quantity")) + oldQuantity;
   		        	}else if(action.equals("minusItems") || action.equals("plusItems")){
	   					newQuantity = oldQuantity;
  		        	}
   		        	newMap.put("quantity", String.valueOf(newQuantity));
   	   	           	newBuylist.add(newMap);
		        	}
				}else{
					newMap = oldMap;
	   	           	newBuylist.add(newMap);
				}
			}
       	if(hasMatch == 0){
       		newMap = new HashMap<String,String>();
       		newMap.put("wineId", Id);
       		newMap.put("quantity", String.valueOf(newQuantity));
           	newBuylist.add(newMap);
       	}
	        
        session.setAttribute("Buylist", newBuylist);
//        System.out.println(newBuylist);

    	if(action.equals("addItems")){
   	        BuyCount += oldQuantity;
    	}else if(action.equals("minusItems")){
   	        BuyCount -= 1;
    	}else if(action.equals("plusItems")){
   	        BuyCount += 1;
    	}else if(action.equals("delItems")){
   	        BuyCount -= oldQuantity;
    	}
    	session.setAttribute("BuyCount", BuyCount);
    	response.getWriter().print(BuyCount);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
