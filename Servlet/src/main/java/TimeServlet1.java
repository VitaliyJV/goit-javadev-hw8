import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timezone = URLEncoder.encode(req.getParameter("timezone"), StandardCharsets.UTF_8);
        try {
            ZoneId zoneId = ZoneId.of(timezone);
            LocalDateTime now = LocalDateTime.now(ZoneId.of(timezone));
            OffsetDateTime offsetDateTime = now.atOffset(zoneId.getRules().getOffset(now));
            resp.getWriter()
                    .write(
                            DateTimeFormatter.ISO_OFFSET_DATE_TIME
                                    .format(offsetDateTime)
                    );
        } catch (Exception e) {
            log(e.getMessage(),e);
            resp.setStatus(400);
            resp.getWriter().write("Invalid timezone");
        }
        resp.getWriter().close();
    }
}

/*
http://localhost:8080

http://localhost:8080/Servlet/time

http://localhost:8080/Servlet/time?timezone=UTC+2

 */
