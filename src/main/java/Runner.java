import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import infrastructure.PetHttpConfigModule;
import infrastructure.PetHttpModule;
import jetty.ApplicationModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import sample.PetModule;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Runner {

    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(new PetModule(),
            new PetHttpModule(),
            new PetHttpConfigModule(),
            new ApplicationModule()
        );

        Server server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/",
            ServletContextHandler.SESSIONS);
        servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        servletContextHandler.addServlet(DefaultServlet.class, "/");

        server.start();

        server.join();
    }
}
