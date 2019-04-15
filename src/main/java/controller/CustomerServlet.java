package controller;

import model.Customer;
import service.CustomerService;
import service.CustomerServicelmpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServicelmpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                createCustomer(request, response);
                break;
            }
            case "edit": {
                updateCustomer(request,response);
                break;
            }
            case "delete": {
                deleteCustomer(request,response);
                break;
            }
            default:
                listCustomer(request, response);
                break;
        }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":{
                viewCustomer(request,response);
                break;
            }
            case "create": {
                showCreateForm(request, response);
                break;
            }
            case "edit": {
                showEditForm(request, response);
                break;
            }
            case "delete": {
                showDeleteForm(request,response);
                break;
            }
            default:
                listCustomer(request, response);
                break;
        }
    }

    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=customerService.fineById(id);
        RequestDispatcher requestDispatcher;

        if (customer==null){
            requestDispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("customer",customer);
            request.setAttribute("message","Customer information");
            requestDispatcher=request.getRequestDispatcher("customer/inforCustomer.jsp");
        }
        try{
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=customerService.fineById(id);

        RequestDispatcher requestDispatcher;
        if (customer==null){
            requestDispatcher=request.getRequestDispatcher("error-404.jsp");
        }
        else {
            this.customerService.remove(id);
        }
        try {
            response.sendRedirect("/customers");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=customerService.fineById(id);
        RequestDispatcher dispatcher;
        if (customer==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }
        else {
            request.setAttribute("customer",customer);
            dispatcher=request.getRequestDispatcher("customer/delete.jsp");
        }
        try{
            dispatcher.forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String newname=request.getParameter("name");
        String newmail=request.getParameter("email");
        String newaddress=request.getParameter("address");
        Customer customerNeedUpdate=customerService.fineById(id);

        RequestDispatcher dispatcher;
        if (customerNeedUpdate==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");

        }
        else {
            customerNeedUpdate.setName(newname);
            customerNeedUpdate.setEmail(newmail);
            customerNeedUpdate.setAddress(newaddress);

            this.customerService.save(customerNeedUpdate);

            request.setAttribute("customer",customerNeedUpdate);
            request.setAttribute("message","Customer information was updated");
            dispatcher=request.getRequestDispatcher("customer/edit.jsp");


        }
        try{
            dispatcher.forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customerNeedEdit=customerService.fineById(id);
        RequestDispatcher dispatcher;
        if (customerNeedEdit==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }
        else {
            request.setAttribute("customer",customerNeedEdit);
            dispatcher=request.getRequestDispatcher("customer/edit.jsp");
        }
        try {
            dispatcher.forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        String mail=request.getParameter("email");
        String address=request.getParameter("address");
        int id = (int)(Math.random() * 10000);

        Customer customer=new Customer(id,name,mail,address);
        this.customerService.save(customer);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("customer/create.jsp");
        request.setAttribute("message", "New customer was created");
        try{
            requestDispatcher.forward(request,response);
        }catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerList=this.customerService.findAll();
        request.setAttribute("customers",customerList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("customer/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
