package com.ifiport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Martin on 2017/08/09.
 */
@Controller
public class RecordController {
    @RequestMapping(path="/record", method= RequestMethod.GET)
    public String goHome(){
        return "record";
    }

}
