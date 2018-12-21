package com.gioov.example.api;

import com.gioov.common.web.http.SuccessEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@RestController
@RequestMapping("/api/example")
public class ExampleRestController {

    /**
     * 测试 API
     *
     * @return String
     */
    @RequestMapping("/test")
    public ResponseEntity<SuccessEntity> test() {
        return new ResponseEntity<>(new SuccessEntity(HttpStatus.OK), HttpStatus.OK);
    }

}
