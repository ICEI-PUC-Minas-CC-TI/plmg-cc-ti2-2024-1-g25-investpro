package ti1;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.sql.Connection;
import java.util.List;

import com.google.gson.Gson;

import model.Blog;
import model.User;
import services.DBFunctions;
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

class HelloWorldResponse {
    public String message = "Hello Worldss";
}

class BlogPostResponse {
    public Blog blog;
}

/**
 * Hello world!
 *
 */
public class App {

    private static final String dbname = "postgres";

    private static final String username = "postgres";

    private static final String password = "Senha@123";

    public static void main(String[] args) {

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        // Set up DB
        DBFunctions db = new DBFunctions();
        Connection conn = db.conect_to_db(dbname, username, password);

        // db.create_table(conn, "blog");
        db.create_user_table(conn);
        // db.insert_data(conn, "blog", "Mais linhas", "Landau", "Tudo sobre uma nova
        // linha");
        // db.read_all_data(conn, "blog");
        // db.update_data(conn, 2, "blog", "Linha atualizada", "Landau 2", "New
        // contnt");
        // db.search_by_id(conn, "blog", "3");
        // db.delete_data_by_author(conn, "blog", "Landau 2");

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
            get("/posts/", servePostsApi, jsonTransformer);
            get("/posts/:id/", servePostByIdApi, jsonTransformer);
            post("/posts/", createPost, jsonTransformer);

            // Autenticação
            post("/login/", getUser, jsonTransformer);
            post("/users/", createUser, jsonTransformer);
        });

    }

    private static Route servePostsApi = (request, response) -> {
        DBFunctions dbFunctions = new DBFunctions();
        Connection conn = dbFunctions.conect_to_db(dbname, username, password);

        String tableName = "blog";
        List<Blog> posts = dbFunctions.read_all_data(conn, tableName);

        response.type("application/json");
        return posts;
    };

    private static Route servePostByIdApi = (request, response) -> {
        DBFunctions dbFunctions = new DBFunctions();
        Connection conn = dbFunctions.conect_to_db(dbname, username, password);

        String tableName = "blog";
        String id = request.params(":id");
        Blog post = dbFunctions.search_by_id(conn, tableName, id);

        response.type("application/json");
        return post;
    };

    private static Route createPost = (req, res) -> {

        DBFunctions dbFunctions = new DBFunctions();
        Connection conn = dbFunctions.conect_to_db(dbname, username, password);
        String post = req.body();
        Blog blog = new Gson().fromJson(post, Blog.class);

        if (blog.title.isEmpty() || blog.author.isEmpty() || blog.content.isEmpty()) {
            res.status(400);
            return "Error: title, author and content are required.";
        }

        dbFunctions.insert_data(conn, "blog", blog.title, blog.author, blog.content);

        return "Post Created";
    };

    private static Route createUser = (request, response) -> {
        DBFunctions dbFunctions = new DBFunctions();
        Connection conn = dbFunctions.conect_to_db(dbname, username, password);

        String post = request.body();
        User user = new Gson().fromJson(post, User.class);
        if (user.getFirstname().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            response.status(400);
            return "Error: name, email and password are required.";
        }
        ;
        dbFunctions.create_user(conn, user.getFirstname(), user.getLastname(), user.getCpf(), user.getPassword(),
                user.getEmail(), user.isAcept());

        return "User Created";
    };

    private static Route getUser = (request, response) -> {
        DBFunctions dbFunctions = new DBFunctions();
        Connection conn = dbFunctions.conect_to_db(dbname, username, password);
        String post = request.body();
        User userInput = new Gson().fromJson(post, User.class);
        if (userInput.getEmail().isEmpty() || userInput.getPassword().isEmpty()) {
            response.status(400);
            return "Error: name, email and password are required.";
        }
        ;
        User user = dbFunctions.authenticate_user(conn, userInput.getEmail(), userInput.getPassword());

        response.type("application/json");

        System.out.println("Usuário autenticado");
        return user;
    };

}
