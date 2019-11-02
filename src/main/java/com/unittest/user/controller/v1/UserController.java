package com.unittest.user.controller.v1;


import com.unittest.user.entity.User;
import com.unittest.user.kafka.event.v1.MessageReceivedEvent;
import com.unittest.user.kafka.producer.HelloWorldEventSender;
import com.unittest.user.model.v1.HelloWorldModel;
import com.unittest.user.service.v1.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController("UserControllerV1")
@CrossOrigin
@RequestMapping(value = "/v1/users")
public class UserController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HelloWorldEventSender helloWorldEventSender;

    /**
     * Get all the users
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Get User Info based on the Id
     *
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> userOpt = userService.getById();
        LOGGER.info("information message");
        helloWorldEventSender.send("messageReceivedEvent", getMessageReceivedEvent());
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private MessageReceivedEvent getMessageReceivedEvent() {
        MessageReceivedEvent mre = new MessageReceivedEvent();
        HelloWorldModel hwm = new HelloWorldModel();
        hwm.setId(1L);
        hwm.setGuid(UUID.randomUUID().toString());
        mre.setHelloWorldModel(hwm);
        return mre;
    }
}
