package com.example.backendprojectmodule_productservice.Controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @RequestMapping("/hello/{name}/{times}")
    public String hello(@PathVariable("name") String name, @PathVariable("times") int times) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < times; i++){
            sb.append("Hello, " + name + "!");
            sb.append("<br>");
        }
        return sb.toString();
    }
}
