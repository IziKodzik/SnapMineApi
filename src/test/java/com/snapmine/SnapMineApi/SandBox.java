package com.snapmine.SnapMineApi;

import com.snapmine.SnapMineApi.model.Role;
import com.snapmine.SnapMineApi.model.SessionToken;

public class SandBox {



    public static void main(String[] args) {
        Role[] roles = {new Role(1,"faj","nie")
                ,new Role(2,"niew","zle")};
        SessionToken token = new SessionToken(roles);

    }

}
