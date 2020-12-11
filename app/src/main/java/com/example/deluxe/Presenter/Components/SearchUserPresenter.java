<<<<<<< HEAD:app/src/main/java/com/example/deluxe/Presenter/Components/SearchUserPresenter.java
package com.example.deluxe.Presenter.Components;
=======
package com.example.deluxe.Presenter;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/Presenter/SearchUserPresenter.java

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.UserInterface;
import com.example.deluxe.Interface.PresenterView.Components.SearchUserInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;

import java.util.ArrayList;

public class SearchUserPresenter implements SearchUserInterface.SearchUserPresenter {

    UserModel userModel;
    SearchUserInterface.SearchUserView searchUserView;

    public SearchUserPresenter(SearchUserInterface.SearchUserView searchUserView) {
        this.searchUserView = searchUserView;
        this.userModel = new UserModel();
    }

    @Override
    public void handleInput(String emailSearch) {
        final User user = new User(null, null, emailSearch);
        this.userModel.search(user, new UserInterface() {
            @Override
            public void dataIsLoaded(ArrayList<User> list) {

                if (!list.isEmpty())
                    for (User userInList : list) {
                        if (userInList.getEmail().equals(Auth.getInstance().user().getEmail())) {
                            list.remove(userInList);
                            break;
                        }

                    }

                searchUserView.setList(list);
            }
        });
    }

}
