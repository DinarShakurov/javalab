package ru.shakurov.webapp_javalab.servlet;

import ru.shakurov.context.ApplicationContext;
import ru.shakurov.webapp_javalab.dto.GoodDto;
import ru.shakurov.webapp_javalab.dto.ListDto;
import ru.shakurov.webapp_javalab.dto.UserDto;
import ru.shakurov.webapp_javalab.model.Good;
import ru.shakurov.webapp_javalab.services.GoodService;
import ru.shakurov.webapp_javalab.utils.CheckAccess;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods")
public class GoodServlet extends HttpServlet {
    private GoodService goodService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        this.goodService = applicationContext.getComponent(GoodService.class, "GoodService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        ListDto<GoodDto> myListDto = goodService.getMyGoods(userDto.getId());
        ListDto<GoodDto> allListDto = goodService.getAllGoods();
        if (myListDto.getList().size() > 0) {
            req.setAttribute("myGoods", myListDto.getList());
        }
        if (allListDto.getList().size() > 0) {
            req.setAttribute("allGoods", allListDto.getList());
        }
        req.getRequestDispatcher("Goods.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("form_type");
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        switch (command) {
            case "buy":
                goodService.buyGood(userDto.getId(),
                        Long.parseLong(req.getParameter("good_id")));
                break;
            case "add":
                goodService.addGood(req.getParameter("good_name"),
                        Integer.parseInt(req.getParameter("good_price")));
                break;
        }
        resp.sendRedirect("/goods");

    }

}
