<%@ page import="classes.City" import="classes.DataBase" %>
<%@ page contentType="application/json" %>
<%@ page language="java" %>
<%
    int departureId = Integer.parseInt(request.getParameter("departureId"));
    java.util.List<City> cities = DataBase.getAllCitiesExcept(departureId);
    String json = "[";
    for (int i = 0; i < cities.size(); i++) {
        City city = cities.get(i);
        json += "{\"id\": " + city.getId() + ", \"name\": \"" + city.getName() + "\"}";
        if (i < cities.size() - 1) {
            json += ",";
        }
    }
    json += "]";
    out.print(json);
%>
