package jetty;

import com.google.inject.servlet.ServletModule;

public class ApplicationModule extends ServletModule {
    @Override
    protected void configureServlets() {

        bind(PetServlet.class);

        serve("/pets/*").with(PetServlet.class);
    }
}
