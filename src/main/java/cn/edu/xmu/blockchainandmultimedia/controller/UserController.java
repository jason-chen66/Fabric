package cn.edu.xmu.blockchainandmultimedia.controller;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.LoginUserVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.RegisterUserRetVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.RegisterUserVo;
import cn.edu.xmu.blockchainandmultimedia.service.UserService;
import cn.edu.xmu.blockchainandmultimedia.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param
     * @return
     */
    @PostMapping("/login")
    public ReturnObject userLogin(@RequestBody LoginUserVo body){
        return new ReturnObject(userService.login(body));
    }

    /**
     * 用户注册
     *
     * @param
     * @return
     */
    @PostMapping("/register")
    public ReturnObject userRegister(@RequestBody RegisterUserVo body){
        RegisterUserRetVo registerUserRetVo;
        try {
            registerUserRetVo = userService.register(body);
        }catch (Exception e){
            return new ReturnObject(e);
        }
        return new ReturnObject(registerUserRetVo);
    }


}
