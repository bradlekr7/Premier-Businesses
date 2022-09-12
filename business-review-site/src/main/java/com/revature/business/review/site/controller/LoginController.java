package com.revature.business.review.site.controller;


import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        request.getRequestDispatcher("html_files/Login.html").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        HttpSession session = request.getSession();
//
//        if(!request.getParameter("positionType").equals("Select a Position")) {
//            PersonDao personDao = new PersonDaoImpl();
//            Person person = personDao.getByUsername(username);
//            if(person ==null ){
//                request.getRequestDispatcher("html_files/Login_Failed.html").forward(request, response);
//            }
//
//            if (request.getParameter("positionType").equals("Manager")) {
//                if (person.login(password) && person.getIsManager()) {
////                    Users.setManager(manager.getId(), false);
//                    session.setAttribute("user", person);
//                    response.sendRedirect("managerhome");
//                } else {
//                    response.sendRedirect("login");
//                }
//            } else if (request.getParameter("positionType").equals("Employee")) {
//                if (person.login(password)) {
////                    Users.setEmployee(employee.getId(), false);
//                    session.setAttribute("user", person);
//                    response.sendRedirect("employeehome");
//                } else {
//                    response.sendRedirect("login");
//                }
//            }
//        } else {
//
//            request.getRequestDispatcher("html_files/Login.html").forward(request, response);
        }
