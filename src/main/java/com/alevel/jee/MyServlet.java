package com.alevel.jee;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


@WebServlet(urlPatterns = "/")
public class MyServlet extends HttpServlet {

    private String responseTemplate;

    @Override
    public void init() throws ServletException {

        try {
            URI templateURI = getClass().getResource("/apiprivat/index.html").toURI();
            byte[] bytes = Files.readAllBytes(Paths.get(templateURI));
            responseTemplate = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new ServletException(e);
        }

        System.out.println("Initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print(responseTemplate);
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed");
    }
}