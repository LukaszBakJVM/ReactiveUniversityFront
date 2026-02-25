package org.example.reactiveuniversityfront.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.UserInfoService;
import org.example.reactiveuniversityfront.auth.dto.UserInfo;

@Route("")
public class IndexView extends VerticalLayout implements BeforeEnterObserver {
    private final UserInfoService userInfoService;

    public IndexView(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        UserInfo userInfo = userInfoService.getCurrentUser();

        if (userInfo == null) {

            beforeEnterEvent.forwardTo(CourseView.class);
        } else {

            switch (userInfo.role()) {
                case "Office":
                    beforeEnterEvent.forwardTo(AdminView.class);
                    break;
                case "Teacher":
                    beforeEnterEvent.forwardTo(TeacherView.class);
                    break;
                case "Student":
                    beforeEnterEvent.forwardTo(StudentView.class);
                    break;

            }
        }
    }

}



