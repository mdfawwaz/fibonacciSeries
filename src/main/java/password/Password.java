package password;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Password extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");

        boolean isBanned = BannedPwd(password);

        if (isBanned) {
            response.getWriter().write("Password is banned.");
        } else {
            response.getWriter().write("Password is valid.");
        }
    }

    private boolean BannedPwd(String password) {
    	String path = "/home/fawwaz/eclipse-workspace/webDevelopment/src/main/java/banned-passwords.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(password)) {
                    return true; 
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; 
    }
}
