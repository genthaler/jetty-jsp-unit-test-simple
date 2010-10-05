import junit.framework.TestCase;

import org.apache.jasper.servlet.JspServlet;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;

public class HelloWorldJspTest extends TestCase {
	ServletTester tester = new ServletTester();
	HttpTester request = new HttpTester();
	HttpTester response = new HttpTester();

	public void setUp() throws Exception {
		tester.setResourceBase("./src/main/webapp");
		tester.addServlet(JspServlet.class, "*.jsp");
		tester.start();

		request.setMethod("GET");
		request.setVersion("HTTP/1.0");
	}

	public void testIndex() throws Exception {
		request.setURI("/hello-world.jsp");
		response.parse(tester.getResponses(request.generate()));

		assertTrue(response.getMethod() == null);
		assertEquals(200, response.getStatus());
		assertEquals("<html><body>Hello World</body></html>", response
				.getContent());
	}
}
