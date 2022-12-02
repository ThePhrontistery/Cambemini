package es.capgemini.cca.canbemini.userKanbanPermission;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class UserKanbanPermissionIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH_KANBAN = "/api/kanban/";
    public static final String SERVICE_PATH_USER = "/api/users/";
    public static final String SERVICE_PATH_UKP = "/api/ukp/";
}
