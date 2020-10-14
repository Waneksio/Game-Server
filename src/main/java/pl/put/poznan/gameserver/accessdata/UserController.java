package pl.put.poznan.gameserver.accessdata;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.gameserver.accessdata.entity.User;
import pl.put.poznan.gameserver.accessdata.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserRepository repository;

    @PostMapping
    public User saveUser(@RequestParam String nick, @RequestParam String password) {
        User user = new User();
        user.setNick(nick);
        user.setPassword(password);
        user.setScore(0);
        user.setId(repository.findAll()
                             .size());

        return repository.save(user);
    }

    @PostMapping("/score")
    public User saveScore(@RequestParam String nick, @RequestParam String score) {
        User user = repository.findByNick(nick)
                              .orElseThrow();
        int intScore = Integer.parseInt(score);
        if (user.getScore() == 0 || user.getScore() > intScore) {
            user.setScore(intScore);
            return repository.save(user);
        }
        return user;
    }

    @GetMapping("/scores")
    public List<User> getScore() {
        return repository.findAll()
                         .stream()
                         .sorted(Comparator.comparingInt(User::getScore))
                         .limit(10)
                         .collect(Collectors.toList());
    }

    @GetMapping("/score")
    public User getScore(@RequestParam String nick, @RequestParam String password) {
        User user = repository.findByNick(nick)
                              .orElseThrow();
        if (user.getPassword()
                .equals(password)) {
            return user;
        }
        return null;
    }

}
