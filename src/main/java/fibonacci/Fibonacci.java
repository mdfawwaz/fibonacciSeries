package fibonacci;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.util.Map;

public class Fibonacci extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    public List<Integer> FibonacciSeries(int n){

        List<Integer> FiboSeries = new ArrayList<>();

        int a = 0,b = 1, c;
        FiboSeries.add(a);
        FiboSeries.add(b);

        for(int i=2;i<n;i++) {
        	
            c = a + b;
            FiboSeries.add(c);
            a = b;
            b = c;
        }
        return FiboSeries;
    }


      @Override

      public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {

        try {

          Map <String, String[]> parameterMap = req.getParameterMap();
          parameterMap.entrySet().stream().forEach(entry -> {

            System.out.print("key: " + entry.getKey());
            System.out.print(" values: " + Arrays.toString(entry.getValue()) + "\n");

          });

          PrintWriter out = resp.getWriter();
          int num = Integer.parseInt(parameterMap.get("n")[0]);
          List<Integer> ans = new ArrayList<>();
          ans = FibonacciSeries(num);
          out.println(String.format("<h1>Fibonacci Series is: %s!</h1>", ans));

        } catch (IOException e) {
        	
        	e.printStackTrace();

        }

      }

      @Override
      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          PrintWriter out = response.getWriter();
          
          String formLimit = request.getParameter("formLimit");
          if (formLimit == null || formLimit.isEmpty()) {
              out.println("Please provide a value for the 'formLimit' parameter.");
              return;
          }
          
          // Parse the 'formLimit' parameter to an integer
          int limit = Integer.parseInt(formLimit);
          
          // Generate Fibonacci series and output
          List<Integer> fibonacciSeries = FibonacciSeries(limit);
          out.println("<h1>Fibonacci Series:</h1>");
          out.println("<ul>");
          for (int num : fibonacciSeries) {
              out.println("<li>" + num + "</li>");
          }
          out.println("</ul>");
      }
}
