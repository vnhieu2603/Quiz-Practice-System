/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.Dimension_TypeDAO;
import dal.ExamDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Dimension_Type;
import model.Exam;
import model.Subject;

/**
 *
 * @author dai
 */
public class EditQuizDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int examId = Integer.parseInt(request.getParameter("examId"));
        
        ExamDAO eDAO = new ExamDAO();
        Exam lExam = eDAO.getExamById(examId);
        request.setAttribute("lExam", lExam);
        
        SubjectDAO sDAO = new SubjectDAO();
        List<Subject> lSubject = new ArrayList<>();
        lSubject = sDAO.getSubjects();
        request.setAttribute("lSubject", lSubject);
        
        Dimension_TypeDAO dDAO = new Dimension_TypeDAO();
        List<Dimension_Type> lDimension = new ArrayList();
        lDimension = dDAO.getDimensionType();
        request.setAttribute("lDimension", lDimension);
        
        request.getRequestDispatcher("EditQuizDetails.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        String xName = request.getParameter("name");
        int xSubject = Integer.parseInt(request.getParameter("selectedSubject"));
        int xLevel = Integer.parseInt(request.getParameter("level"));
        int xDuration = Integer.parseInt(request.getParameter("duration"));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        String xxDuration = dateFormat.format(xDuration);
        int hour = xDuration / 60;
        int minute = xDuration - hour * 60;

        Double xPassrate = Double.parseDouble(request.getParameter("passrate"));
        boolean xType = Boolean.parseBoolean("mode");
        String xDescription = request.getParameter("description");
        int xQuestions = Integer.parseInt(request.getParameter("questions"));
        int xquesType = Integer.parseInt(request.getParameter("questionType"));

        ExamDAO eDAO = new ExamDAO();
        eDAO.update(xName, xSubject, xLevel, hour, minute, xPassrate, xQuestions, xDescription, xType, xquesType, examId);
        response.sendRedirect("cusHome");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
