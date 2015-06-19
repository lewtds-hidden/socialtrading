package vn.com.vndirect.socialtrading.restapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vndirect.socialtrading.dao.FollowerDao;
import vn.com.vndirect.socialtrading.model.Follower;
import vn.com.vndirect.socialtrading.service.FollowerService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class FollowerController {
    @Autowired
    FollowerDao followerDao;
    @Autowired
    FollowerService followerService;


    @RequestMapping(value = "/api/v1/follower/{id}", method = RequestMethod.GET)
    public Follower getFollower(@PathVariable String id) {
        return followerDao.getSingle(id);
    }

    @RequestMapping(value = "/api/v1/followers", method = RequestMethod.GET)
    public List<Follower> getAllFollower() {
        return followerDao.findAll();
    }

    @RequestMapping(value = "/api/v1/follower/{id}/following", method = RequestMethod.POST)
    public void followTrader(@PathVariable String id, String traderAccount, BigDecimal amount) {
        // return followerService.followTrader(id, traderAccount, amount);
    }

}