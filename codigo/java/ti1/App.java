package ti1;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.staticFiles;

import java.util.List;

import com.google.gson.Gson;

import dao.CalendarDAO;
import dao.InvestimentHistoryDAO;
import dao.UserDAO;
import model.CalendarEventModel;
import model.InvestmentHistoryModel;
import model.UserModel;

import services.JsonTransformer;
import spark.ResponseTransformer;
import spark.Route;
import ti1.calculator.Calculator;
import ti1.calendar.Calendar;
import ti1.financialRegistration.FinancialRegistrationController;
import ti1.index.IndexController;
import ti1.inflationChart.InflationChart;
import ti1.personalLogin.PersonalLogin;
import ti1.personalRegistration.PersonalRegistrationController;
import ti1.planningChart.PlanningChart;
import ti1.spendingChart.SpendingChart;
import ti1.utils.Filters;
import ti1.utils.Path;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        CalendarDAO calendarDAO = new CalendarDAO();
        InvestimentHistoryDAO investimentHistoryDAO = new InvestimentHistoryDAO();

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        // Set up tables
        userDAO.create_user_table();
        calendarDAO.create_calendar_table();
        investimentHistoryDAO.create_investiment_history_table();

        // Set up before-filters (called before each get/post)
        before("*", Filters.addTrailingSlashes);
        // before("*", Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX, IndexController.serveIndexPage);
        get(Path.Web.HOME, IndexController.serveIndexPage);
        get(Path.Web.FINANCIAL_REGISTRATION, FinancialRegistrationController.serveFinancialPage);
        get(Path.Web.PERSONAL_REGISTRATION, PersonalRegistrationController.serveRegistrationPage);
        get(Path.Web.CALCULATOR, Calculator.serveCalculatorPage);
        get(Path.Web.CALENDAR, Calendar.serveCalendarPage);
        get(Path.Web.INFLATION_CHART, InflationChart.serveInflationChartPage);
        get(Path.Web.PLANNING_CHART, PlanningChart.servePlanningPage);
        get(Path.Web.SPENDING_CHART, SpendingChart.serveSpendingChartPage);
        get(Path.Web.LOGIN, PersonalLogin.servePersonalLoginPage);
        get(Path.Web.PERSONAL_LOGIN, PersonalLogin.servePersonalLoginPage);
        // get("*", ViewUtil.notFound);

        // Set up after-filters (called after each get/post)
        after("*", Filters.addGzipHeader);

        // Set up JSON transformer
        ResponseTransformer jsonTransformer = new JsonTransformer();

        // Set up API routes
        before("/*", (q, a) -> System.out.print("Received api call"));
        path("/api/", () -> {

            // Autenticação
            post("/users/login/", getUserRoute, jsonTransformer);
            post("/users/registration/", createUserRoute, jsonTransformer);
            put("/users/update/", updateUserRoute, jsonTransformer);
            delete("/users/delete/", deleteUserRoute, jsonTransformer);
            get("/users/calendar/:id/", getUsersCalendar, jsonTransformer);
            get("/users/investments/:id/", getUsersInvestments, jsonTransformer);
            
            // Calendário
            post("/calendar/create/", createCalendarEventRoute, jsonTransformer);
            put("/calendar/update/", updateCalendarEventRoute, jsonTransformer);
            delete("/calendar/delete/", deleteCalendarEventRoute, jsonTransformer);
            get("/calendar/search/", searchAllCalendarEventRoute, jsonTransformer);
            get("/calendar/search/:id/", searchCalendarEventRoute, jsonTransformer);
            
            // Investimentos
            post("/investments/create/", createInvestmentRoute, jsonTransformer);
            put("/investments/update/", updateInvestmentRoute, jsonTransformer);
            delete("/investments/delete/", deleteInvestmentRoute, jsonTransformer);
            get("/investments/search/", searchAllInvestimentRoute, jsonTransformer);
            get("/investments/search/:id/", searchInvestmentRoute, jsonTransformer);

        
        });
    }

    public static Route createInvestmentRoute = (request, response) -> {
        InvestimentHistoryDAO investiment = new InvestimentHistoryDAO();
        String post = request.body();
        InvestmentHistoryModel investment = new Gson().fromJson(post, InvestmentHistoryModel.class);
        investiment.insert_investiment_history(investment);
        return "Investiment Created";
    };

    public static Route searchInvestmentRoute = (request, response) -> {
        InvestimentHistoryDAO investiment = new InvestimentHistoryDAO();
        String id = request.params("id");
        return investiment.get_investiment_history(id);
    };

    public static Route searchAllInvestimentRoute = (request, response) -> {
        InvestimentHistoryDAO investiment = new InvestimentHistoryDAO();
        return investiment.get_all_investiment_history();
    };

    public static Route getUsersInvestments = (request, response) -> {
        InvestimentHistoryDAO investiment = new InvestimentHistoryDAO();
        String id = request.params("id");
        return investiment.get_users_investiment_history(id);
    };

    public static Route updateInvestmentRoute = (request, response) -> {
        InvestimentHistoryDAO investiment = new InvestimentHistoryDAO();
        String post = request.body();
        InvestmentHistoryModel investment = new Gson().fromJson(post, InvestmentHistoryModel.class);
        investiment.update_investiment_history(investment);
        return "Investiment Updated";
    };

    public static Route deleteInvestmentRoute = (request, response) -> {
        InvestimentHistoryDAO investimentDAO = new InvestimentHistoryDAO();
        String post = request.body();
        UserModel investment = new Gson().fromJson(post, UserModel.class);
        investimentDAO.delete_investiment_history(investment.getId());
        
        return "Investiment Deleted";
    };

    public static Route createCalendarEventRoute = (request, response) -> {
        CalendarDAO calendar = new CalendarDAO();
        String post = request.body();
        CalendarEventModel calendarEvent = new Gson().fromJson(post, CalendarEventModel.class);
        calendar.insert_calendar_event(calendarEvent);
        return "Calendar Event Created";
    };

    public static Route updateCalendarEventRoute = (request, response) -> {
        CalendarDAO calendar = new CalendarDAO();
        String post = request.body();
        CalendarEventModel calendarEvent = new Gson().fromJson(post, CalendarEventModel.class);
        calendar.update_calendar_event(calendarEvent);
        return "Calendar Event Updated";
    };

    public static Route deleteCalendarEventRoute = (request, response) -> {
        CalendarDAO calendar = new CalendarDAO();
        String post = request.body();
        CalendarEventModel calendarEvent = new Gson().fromJson(post, CalendarEventModel.class);
        calendar.delete_calendar_event(calendarEvent.getId());
        return "Calendar Event Deleted";
    };

    public static Route searchCalendarEventRoute = (request, response) -> {
        CalendarDAO calendar = new CalendarDAO();
        String id = request.params("id");
        CalendarEventModel calendarEvent = calendar.get_calendar_event(id);
        return calendarEvent;
    };

    public static Route searchAllCalendarEventRoute = (request, response) -> {
        CalendarDAO calendar = new CalendarDAO();
        List<CalendarEventModel> calendarEvents = calendar.get_all_calendar_events();
        return calendarEvents;
    };

    public static Route getUsersCalendar = (request, response) -> {
        CalendarDAO calendar = new CalendarDAO();
        String id = request.params("id");
        return calendar.get_user_calendar_events(id);
    };

    private static Route deleteUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();
        String post = request.body();
        UserModel user = new Gson().fromJson(post, UserModel.class);
        userDAO.delete_user(user.getId());
        return "User Deleted";
    };

    private static Route updateUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();
        String post = request.body();
        UserModel user = new Gson().fromJson(post, UserModel.class);
        if (user.getFirstname().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            response.status(400);
            return "Error: name, email and password are required.";
        }

        userDAO.update_user(user.getId(), user.getFirstname(), user.getLastname(), user.getCpf(), user.getPassword(), user.getEmail(), user.isAccept());
        return "User Updated";
    };

    private static Route createUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();

        String post = request.body();
        UserModel user = new Gson().fromJson(post, UserModel.class);
        if (user.getFirstname().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            response.status(400);
            return "Error: name, email and password are required.";
        }
        ;
        userDAO.create_user(user.getFirstname(), user.getLastname(), user.getCpf(), user.getPassword(),
                user.getEmail(), user.isAccept());

        return "User Created";
    };

    private static Route getUserRoute = (request, response) -> {
        UserDAO userDAO = new UserDAO();
        String requestBody = request.body();
        UserModel userInput = new Gson().fromJson(requestBody, UserModel.class);

        if (userInput.getEmail().isEmpty() || userInput.getPassword().isEmpty()) {
            response.status(400);
            return "Error: email e senha são obrigarios.";
        }
        ;
        UserModel user = userDAO.authenticate_user(userInput.getEmail(), userInput.getPassword());
        response.type("application/json");
        System.out.println("Usuário autenticado");
        return user;
    };

}
