package why.post2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("verify")
public class VerifyPass extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        checkPasswordStrange(request, response);
    }
    private void checkPasswordStrange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        boolean oneSmallChar = false;
        boolean oneBigChar = false;
        boolean oneNumber = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                oneSmallChar = true;
            }
            if (Character.isUpperCase(password.charAt(i))) {
                oneBigChar = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                oneNumber = true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.print("<h2>Twoje hasło to: " + password + "</h2>" );
        writer.print("<div>");
        if (password.length() < 5){
            writer.println("hasło musi mieć minimum 5 znaków.<br>");
        }
        if (!oneSmallChar){
            writer.println("hasło musi mieć jedna małą literę.<br>");
        }
        if (!oneBigChar){
            writer.println("hasło musi mieć jedna wielką literę.<br>");
        }
        if (!oneNumber){
            writer.println("hasło musi mieć jedna cyfrę.<br>");
        }
        if (password.length() > 5 && oneSmallChar && oneBigChar && oneNumber){
            writer.println("hasło idealne!<br>");
        }
        writer.print("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }

}
