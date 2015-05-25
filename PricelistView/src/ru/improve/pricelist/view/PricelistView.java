package ru.improve.pricelist.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.improve.pricelist.ejb.InternalBisnessProcessLocal;
import ru.improve.pricelist.jpa.Prod;

/**
 * Servlet implementation class PricelistView
 */
@WebServlet("/pricelist")
public class PricelistView extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB
	InternalBisnessProcessLocal internalBisnessProcess;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PricelistView() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;
		
//		response.setContentType("text/html");
//		response.setStatus(HttpServletResponse.SC_OK);
		
		String catName = request.getParameter("catName");
		String prodName = request.getParameter("prodName");
		String lowPrice = request.getParameter("lowPrice");
		String highPrice = request.getParameter("highPrice");
		
		List<Prod> filteredProds = new ArrayList<Prod>();

		if((lowPrice.length() != 0) && (highPrice.length() == 0)){
			Pattern pt = Pattern.compile("[0-9]{1,16}(\\.[0-9]{0,})?");
			Matcher m = pt.matcher(lowPrice);
			if (!m.matches()) {
				Message message = new Message();
				message.setNote("Не верный формат цены. Пример правильной цены: 1500.50");
				request.setAttribute("message", message);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;
			} else filteredProds = internalBisnessProcess.getProdAbavePrice(Double.valueOf(lowPrice));
		}
		else if((lowPrice.length() == 0) && (highPrice.length() != 0)){
			Pattern pt = Pattern.compile("[0-9]{1,16}(\\.[0-9]{0,})?");
			Matcher m = pt.matcher(highPrice);
			if (!m.matches()) {
				Message message = new Message();
				message.setNote("Не верный формат цены. Пример правильной цены: 1500.50");
				request.setAttribute("message", message);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;
			} else filteredProds = internalBisnessProcess.getProdBelowPrice(Double.valueOf(highPrice));
		}
		else if((lowPrice.length() != 0) && (highPrice.length() != 0)){
			Pattern pt = Pattern.compile("[0-9]{1,16}(\\.[0-9]{0,})?");
			Matcher m = pt.matcher(highPrice);
			if (!m.matches()) {
				Message message = new Message();
				message.setNote("Не верный формат цены. Пример правильной цены: 1500.50");
				request.setAttribute("message", message);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;
			} else filteredProds = internalBisnessProcess.getProdByPrice(Double.valueOf(lowPrice),
																		 Double.valueOf(highPrice));
		}
		else filteredProds = internalBisnessProcess.getProdAll();
		if(prodName.length() != 0){
			List<Prod> tempProds =  new ArrayList<Prod>();
			tempProds.addAll(filteredProds);
			filteredProds.clear();
			for(Prod p : tempProds){
				int i = p.getName().toUpperCase().indexOf(prodName.toUpperCase());
				if(i == 0) filteredProds.add(p);
			}
		}
		if(catName.length() != 0){
			List<Prod> tempProds =  new ArrayList<Prod>();
			tempProds.addAll(filteredProds);
			filteredProds.clear();
			for(Prod p : tempProds){
				int i = p.getCat().getName().toUpperCase().indexOf(catName.toUpperCase());
				if(i == 0) filteredProds.add(p);
			}
		}
		request.setAttribute("products", filteredProds);
		dispatcher = getServletContext().getRequestDispatcher("/pricelist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
