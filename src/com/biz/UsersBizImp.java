package com.biz;

import com.bean.Users;

public class UsersBizImp implements UsersBiz {
    @Override
    public boolean check(Users us) {
        if(us!=null){
            if(us.getUname()!=null&&us.getUname().trim().equals("admin")
                    &&us.getPasswd()!=null&&us.getPasswd().trim().equals("12345678")){
                    return true;
            }
        }
        return false;

    }
}
