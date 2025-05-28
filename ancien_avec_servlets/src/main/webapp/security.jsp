<% 
Object userId = session.getAttribute("idBD");
System.out.println(userId);
if (userId == null) {
	
    response.sendRedirect("index.jsp");
}
%>
