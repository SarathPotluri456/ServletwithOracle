

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseServlet
 */
@WebServlet("/DatabaseServlet")
public class DatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Register Driver Completed");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			System.out.println("Connection Established");
			String id1=req.getParameter("id");
			int id=Integer.parseInt(id1);
			String name=req.getParameter("name");
			String course=req.getParameter("course");
			PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			if(course.equals("java"))
			{
				ps.setString(3, course);
			}
			else if(course.equals(".net"))
			{
				ps.setString(3, course);
			}
			else
			{
				ps.setString(3, course);
			}
			int i=ps.executeUpdate();
			if(i>0)
			{
			pw.println("Record inserted Successfully");
			}
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select *from register");
			pw.println("<table>");
			pw.println("<tr><td><h2>SNO</h2></td><td><h2>SNAME</h2></td><td><h2>COURSE</h2></td></tr>");
			while(rs.next())
			{
			
			pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
			
			}
			pw.println("</table>");
			pw.println("<a href='http://localhost:8090/DataBaseWithServlet/'>Back</a>");
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
