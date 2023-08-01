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

/*

Завдання №3 - додай WebFilter для некоректних часових поясів
Користувач може передати в параметрі timezone некоректний часовий пояс. У такому випадку повертай веб-сторінку з контентом Invalid timezone
і HTTP кодом відповіді 400.

Для цього створи веб-фільтр з назвою TimezoneValidateFilter, який має перехвачувати запит /time, перевіряти наявність параметру timezone,
і валідувати його.

Для валідації часового поясу можна використати клас Timezone. Javadoc - https://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html

Завдання №4 - залий код проєкту на Github
Створи новий репозиторій на GitHub. Додай туди всі необхідні файли твого проєкту. Переконайсь, що в репозиторії немає зайвих файлів.

Результат виконання ДЗ - GitHub репозиторій з двома класами:

TimeServlet
TimezoneValidateFilter
Можуть бути також створені додаткові класи при потребі.

 */
