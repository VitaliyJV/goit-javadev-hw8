
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getWriter()
                    .write(
                            DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd HH:mm:ss 'UTC")
                                    .format(
                                            LocalDateTime.now().atZone(ZoneId.of(req.getParameter("timezone"))
                                            )));
        } catch (Exception e) {
            resp.setStatus(400);
            try {
                resp.getWriter().write("Invalid timezone");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            resp.getWriter().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

/*
Завдання №1 - напиши сервлет, який віддає HTML сторінку з поточним часом по UTC

Напиши сервлет TimeServlet. Він має обробляти GET запит за адресою /time і віддавати HTML сторінку з поточним часом по
часовому поясу UTC.

Сторінка має виводити час (з точністю до секунд) та часовий пояс. Наприклад, 2022-01-05 12:05:01 UTC
Оскільки це GET запит, то його можна протестувати в браузері. Запусти програму, і переконайсь, що вона коректно працює і
відкривається у браузері. Ти маєш ввести адресу на кшталт http://localhost:8080/time в браузері, і отримати результат.
http://localhost:8080/Servlet/time


Завдання №2 - розшир сервлет, щоб він приймав часовий пояс
Розшир сервлет з попереднього завдання, щоб він приймав один query параметр timezone, і повертав час у переданому часовому поясі.

Наприклад, якщо відкрити в браузері URL виду http://localhost:8080/time?timezone=UTC+2, то отримаємо результат на кшталт - 2022-01-05 12:05:01 UTC+2.

Якщо не передавати параметр timezone, то має повертатись час по UTC.
*/