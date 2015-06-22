package vn.com.vndirect.socialtrading.loaddata;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.socialtrading.dao.TraderDao;
import vn.com.vndirect.socialtrading.utility.InMemory;
import vn.com.vndirect.socialtrading.utility.MyDataSource;

@Component
public class TraderLoader {

	private InMemory memory;
	private MyDataSource myDataSource;
	@Autowired
	public TraderLoader(InMemory memory,MyDataSource dataSource) {
		this.memory = memory;
		this.myDataSource = dataSource;
	}

	@PostConstruct
	public void load() throws Exception {
		loadListTraderToMemory();
	}

	private void loadListTraderToMemory()	throws Exception {
		TraderDao traderDao = new TraderDao(myDataSource);
	     memory.put("TRADER", "",traderDao.findAll());
	     memory.put("TRADERID", "",traderDao.findAllTraderId());
	}
}
