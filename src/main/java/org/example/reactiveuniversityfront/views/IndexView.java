package org.example.reactiveuniversityfront.views;

import com.vaadin.copilot.userinfo.UserInfo;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.example.reactiveuniversityfront.auth.UserInfoService;
import org.example.reactiveuniversityfront.auth.UserInfoServiceImpl;

@Route("")
public class IndexView extends VerticalLayout implements BeforeEnterObserver {
    private final UserInfoService userInfoService;

    public IndexView(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

       /* UserInfo userInfo = userInfoService.getCurrentUser();

        if (userInfo == null) {

          //  event.rerouteTo(LoginView.class);
        } else {

            switch (userInfo.getRole()) {
                case "ADMIN":
                    event.rerouteTo(AdminView.class);
                    break;
                case "USER":
                    event.rerouteTo(UserView.class);
                    break;
                default:
                    event.rerouteTo(DefaultView.class);
            }
        }
    }*/
    }

}

