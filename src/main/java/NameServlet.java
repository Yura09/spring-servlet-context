import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/name")
public class NameServlet extends HttpServlet {

    public static final String SPRING_APP_CONTEXT = "SPRING_APP_CONTEXT";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApplicationContext applicationContext = (ApplicationContext) request.getServletContext().getAttribute(SPRING_APP_CONTEXT);
        PrintWriter writer = response.getWriter();
        writer.println(applicationContext.getBean(NameProvider.class).getName());
        writer.flush();
        writer.close();
    }

    @Override
    public void init(ServletConfig servletConfig) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(NameProvider.class);
        servletConfig.getServletContext().setAttribute(SPRING_APP_CONTEXT, applicationContext);
    }
}
