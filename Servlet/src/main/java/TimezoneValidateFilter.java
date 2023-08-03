import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (!req.getParameterMap().containsKey("timezone")) {
            chain.doFilter(req, res);
            return;
        }

        String timezone = req.getParameter("timezone");
        if (TimeUtils.validateTimeZone(timezone)) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("Invalid timezone");
            res.getWriter().close();
        }
    }
}


 


