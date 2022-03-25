import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AddDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AddDataServlet.class.getName());
	Connection con = null;

	@Override
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String spageid = request.getParameter("page");
		int pageid = Integer.parseInt(spageid);
		int total = 10;
		if (pageid == 1) {
		} else {
			pageid = pageid - 1;
			pageid = pageid * total + 1;
		}

		try {
			String sql = "select * from storeproduct limit "+(pageid-1)+","+total;
			PreparedStatement set = con.prepareStatement(sql);
			out.print("<h1>Product Table</h1>");
			out.print("<table border='1' cellpadding='4' width='60%'>");
			out.print("<tr><th>Id</th><th>ProductName</th><th>ProductPrice</th>");
			ResultSet rs = set.executeQuery();
			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td></tr>");
			}
			String count = "SELECT COUNT(*) FROM storeproduct";
			PreparedStatement countId = con.prepareStatement(count);
			ResultSet r = countId.executeQuery();
			r.next();
			int countID = r.getInt(1);
			
			int totalPage = (int) (Math.ceil(countID/total)+1);

			for(int i=1;i<=totalPage;i++)
			{
				out.print("<a href='AddDataServlet?page="+i+" "+"'>"+i+" "+"</a> ");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
	}
}