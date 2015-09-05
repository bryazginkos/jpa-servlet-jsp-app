package ru.kosdev.improve;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.kosdev.improve.entity.Product;
import ru.kosdev.improve.service.SearchService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kos on 04.09.2015.
 */
@WebServlet("/search")
public class MainServlet extends HttpServlet {

    private final static String CATEGORY_PARAM = "cat";
    private final static String PRODUCT_PARAM = "prod";
    private final static String MIN_PARAM = "minprice";
    private final static String MAX_PARAM = "maxprice";
    private static final String PROD_LIST_ATTR = "productList";
    private static final String ERROR_ATTR = "error";

    @Autowired
    private SearchService searchService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter(CATEGORY_PARAM);
        String product = req.getParameter(PRODUCT_PARAM);
        String minPrice = req.getParameter(MIN_PARAM);
        String maxPrice = req.getParameter(MAX_PARAM);

        //поиск не делается в первый запуск (когда все null)
        if (!allNull(category, product, minPrice, maxPrice)) {
            try {
                List<Product> productList = searchService.find(category, product, minPrice, maxPrice);
                req.setAttribute(PROD_LIST_ATTR, productList);
            } catch (IllegalArgumentException e) {
                req.setAttribute(ERROR_ATTR, e.getMessage());
            } catch (Exception e) {
                req.setAttribute(ERROR_ATTR, "System error");
            }
        }
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    private boolean allNull(@NotNull Object... objects) {
        for (Object object : objects) {
            if (object != null) return false;
        }
        return true;
    }
}
