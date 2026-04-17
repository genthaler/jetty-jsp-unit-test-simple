import org.apache.jasper.runtime.JspFactoryImpl;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.ee8.jsp.JettyJspServlet;
import org.eclipse.jetty.ee8.nested.SessionHandler;
import org.eclipse.jetty.ee8.servlet.DefaultServlet;
import org.eclipse.jetty.ee8.servlet.ServletContextHandler;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpTester;
import org.eclipse.jetty.server.LocalConnector;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspFactory;
import java.nio.file.Files;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class HelloWorldJspTest {

    @Test
    public void test() throws Exception {

        Server server = new Server();

        LocalConnector localConnector = new LocalConnector(server);
        server.addConnector(localConnector);

        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.setResourceBase("src/main/webapp");
        context.addServlet(JettyJspServlet.class, "*.jsp");
        JspFactory.setDefaultFactory(new JspFactoryImpl());
        context.addServlet(DefaultServlet.class, "*.html");
        context.setWelcomeFiles(new String[]{"index.html", "index.jsp"});
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        context.setAttribute(ServletContext.TEMPDIR, Files.createTempDirectory("jetty-jsp-test").toFile());
        context.setSessionHandler(new SessionHandler());

        server.setHandler(context);
        server.start();

        HttpTester.Request request = HttpTester.newRequest();
        request.put(HttpHeader.HOST, "localhost");
        request.setMethod("GET");

        request.setURI("/hello-world.jsp");
        HttpTester.Response response =
                HttpTester.parseResponse(HttpTester.from(localConnector.getResponse(request.generate())));

        assertEquals(200, response.getStatus());
        assertEquals("<html><body>Hello World</body></html>", response
                .getContent());

        request.setURI("/index.html");
        response =
                HttpTester.parseResponse(HttpTester.from(localConnector.getResponse(request.generate())));

        assertEquals(200, response.getStatus());
        assertEquals("<html><body>Hello World</body></html>", response
                .getContent());
    }
}
