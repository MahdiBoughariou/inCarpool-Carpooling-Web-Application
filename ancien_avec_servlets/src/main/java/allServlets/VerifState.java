package allServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class VerifState
 */
public class VerifState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifState() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Récupération du state d'aprés la session_user et l'id
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("idBD");
		String state = (String) session.getAttribute("stateBD");
        
        //envoyer l'id de l'utilisateur à la page user.jsp
        request.setAttribute("id",id);
        
        //cette partie est dédiée pour que le addoffer soit affiché seuelement pour les utilisateurs approuvés
        if (state.equals("approved")) {
            // L'utilisateur est qualifié, afficher le bouton "Add Offer"
            request.setAttribute("showAddOffer", true);
        }
        else {
            // L'utilisateur n'est pas qualifié, ne pas afficher le bouton "Add Offer"
            request.setAttribute("showAddOffer", false);
        }
        request.getRequestDispatcher("user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
