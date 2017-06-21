package jetty;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import infrastructure.PetParser;
import org.eclipse.jetty.http.HttpStatus;
import sample.Pet;
import sample.PetService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class PetServlet extends HttpServlet {

    private final PetService service;
    private final PetParser petParser;

    @Inject
    public PetServlet(PetService service, PetParser petParser) {

        this.service = service;
        this.petParser = petParser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] pathParts = req.getPathInfo().split("/");
        String id = pathParts[pathParts.length - 1];
        Pet pet = service.process(id);
        resp.setStatus(HttpStatus.OK_200);
        petParser.write(pet, resp.getOutputStream());
    }
}
