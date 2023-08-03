import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    private static final String DEFAULT_TIME_ZONE = "UTC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");

        String userTimeZone = req.getParameter("timezone");
        String formattedDateTime = getDateTimeFromUserQuery(userTimeZone);

        resp.getWriter().write(formattedDateTime);
    }

    private String getDateTimeFromUserQuery(String userTimeZone) {
        LocalDateTime currentDateTime = LocalDateTime.now(TimeUtils.getTimeZone(userTimeZone));
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + userTimeZone;
    }
}

/*
http://localhost:8080
http://localhost:8080/time
http://localhost:8080/Servlet/time
http://localhost:8080/Servlet/time?timezone=UTC+2
 */




