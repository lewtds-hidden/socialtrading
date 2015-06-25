package vn.com.vndirect.socialtrading.restapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import vn.com.vndirect.socialtrading.dao.FollowerDao;
import vn.com.vndirect.socialtrading.model.Follower;
import vn.com.vndirect.socialtrading.model.Following;
import vn.com.vndirect.socialtrading.service.FollowerService;

import java.math.BigDecimal;
import java.util.List;

// FIXME Error handling
@RestController
public class FollowerController {
    FollowerDao followerDao;
    FollowerService followerService;

    @Autowired
    public FollowerController(FollowerDao followerDao, FollowerService followerService) {
        this.followerDao = followerDao;
        this.followerService = followerService;
    }

    @RequestMapping(value = "/api/v1/follower/{id}", method = RequestMethod.GET)
    public Follower getFollower(@PathVariable String id) {
        return followerDao.getSingle(id).get();
    }

    @RequestMapping(value = "/api/v1/follower/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Follower> updateFollower(@PathVariable String id,
                                                   @RequestBody Follower updatedFollower) {
        updatedFollower.setAccountNumber(id);
        followerDao.update(updatedFollower);
        return ResponseEntity.ok(updatedFollower);
    }

    @RequestMapping(value = "/api/v1/followers", method = RequestMethod.GET)
    public List<Follower> getAllFollowers() {
        return followerDao.findAll();
    }


    @RequestMapping(value = "/api/v1/follower/{id}/following", method = RequestMethod.GET)
    public List<Following> followingTraders(@PathVariable String id) {
        return followerService.followingTraders(id);
    }

    @RequestMapping(value = "/api/v1/follower/{id}/following", method = RequestMethod.POST)
    public void followTrader(@PathVariable String id, String traderAccount, BigDecimal amount) {
        followerService.followTrader(id, traderAccount, amount);
    }

    @MessageMapping("/hello")
    public void greeting() {
        System.out.println("Hello!");
    }
}
