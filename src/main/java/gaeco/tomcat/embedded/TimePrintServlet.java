package gaeco.tomcat.embedded;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimePrintServlet extends HttpServlet {
	private static final long serialVersionUID = 12321412341947L;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Writer writer = res.getWriter();
        writer.append(String.format("Time : %s", new Date()));
		writer.flush();
	}
}
