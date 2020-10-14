package pl.put.poznan.gameserver.accessdata;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.gameserver.accessdata.entity.User;
import pl.put.poznan.gameserver.accessdata.repository.UserRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserRepository repository;

    @PostMapping
    User saveUser(@RequestParam String nick, @RequestParam String password) {
        User user = new User();
        user.setNick(nick);
        user.setPassword(password);
        user.setScore(0);
        return repository.save(user);
    }

    @PostMapping("/score")
    User saveScore(@RequestParam String nick, @RequestParam String score) {
        User user = repository.findByNick(nick).orElseThrow();
        user.setScore(Integer.parseInt(score));
        return repository.save(user);
    }

    @GetMapping("/scores")
    List<User> getScore() {
        return repository.findAll();
    }

    @GetMapping("/score")
    User getScore(@RequestParam String nick, @RequestParam String password) {
        User user = repository.findByNick(nick).orElseThrow();
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
