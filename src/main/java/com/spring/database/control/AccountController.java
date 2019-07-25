package com.spring.database.control;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.spring.database.dao.AccountMapper;
import com.spring.database.entity.Account;
//import com.spring.database.service.AccountService;

@Controller // restController - 自动加responsebody
@RequestMapping("/db")
public class AccountController {

	// 测试myBatis
	@Autowired
	AccountMapper map;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Account getAccountById(@PathVariable(value = "id") Long id) {
		System.out.println("*********** In getAccountById ****************");

		Account record = map.selectByPrimaryKey(id);
		record.setId(id);
		record.setVersion(1L);
		record.setAccountNo("100");
		map.updateByPrimaryKey(record);
		return record;
	}

	// int insert(Account record)
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@ResponseBody
	public Account insert() {
		Account account = map.selectByPrimaryKey(1L);
		account.setId(2L);
		account.setVersion(2L);
		account.setAccountNo("200");
		map.insert(account);
		return account;
	}

	// int deleteByPrimaryKey(Long id)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Account delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account account = map.selectByPrimaryKey(2L);
		map.deleteByPrimaryKey(account.getId());
		return account;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> toMap(String data) {
		Map<String, String> maps = (Map<String, String>) JSONUtils.parse(data);
		for (Object map : maps.entrySet()) {
			System.out.println(((Map.Entry) map).getKey() + "     " + ((Map.Entry) map).getValue());
		}
		return maps;
	}

	// int insertSelective(Account record)
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Account check(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String idInfo = request.getParameter("idInfo");
//		Map<String, String> idKey = toMap(idInfo);	
//		System.out.println("The key of id is: " + idKey.get("id"));
//		Long trueId = Long.valueOf(idKey.get("id"));
//		System.out.println("The key of id is: " + trueId);
		Map<String, Long> idKey = new HashMap<String, Long>();
		idKey.put("id", 1L);
		return map.selectByPrimaryKey(idKey.get("id"));
//		return 1;
	}

	// @Autowired
	// StringRedisTemplate redis;
	//
	// @Autowired
	// AccountService accountService;
	//
	// @Autowired
	// AccountMapper am;
	//
	// @RequestMapping(value = "/show")
	// public String showPage() {
	//
	// return "/gameTop.html";
	// }
	//
	// @RequestMapping(value = "/redis", method = RequestMethod.GET)
	// @ResponseBody
	// public String testRedis(){
	// /****Redis Value****/
	//// //向redis里存入数据, 设置缓存时间
	//// redis.opsForValue().set("name", "500", 60, TimeUnit.SECONDS);
	//// //减少value
	//// redis.boundValueOps("name").increment(-1);
	//// //根据key获取
	//// redis.opsForValue().get("name");
	//// //增加value
	//// redis.boundValueOps("name").increment(1);
	// //删除
	//// redis.opsForValue().set("test", "999");
	//// redis.delete("test");
	// //存入数据，设置缓存时间
	//// redis.opsForValue().set("test", new Date().getTime() + "", 2,
	// TimeUnit.MINUTES);
	//
	//
	// /****Redis List****/
	//// //由右边添加
	//// redis.opsForList().rightPush("list", "001");
	//// redis.opsForList().rightPush("list", "002");
	//// redis.opsForList().rightPush("list", "003");
	//// //由左边添加
	//// redis.opsForList().leftPush("list", "004");
	//// redis.opsForList().leftPush("list", "005");
	//// //删除第一个005
	//// redis.opsForList().remove("list", 1, "005");
	//// //删除所有002
	//// redis.opsForList().remove("list", 0, "002");
	//// //查询所有元素
	//// System.out.println(redis.opsForList().range("list", 0, -1));
	//// //查询前三个元素
	//// System.out.println(redis.opsForList().range("list", 0, 3));
	//
	//
	// /*****Redis Set****/
	//// //向指定key中存放set
	//// redis.opsForSet().add("stu", "a", "b", "c");
	//// //查看set中是否存在指定数据
	//// boolean exist = redis.opsForSet().isMember("stu", "a");
	//// //根据key获取set合集
	//// System.out.println(redis.opsForSet().members("stu"));
	//
	//
	// /****Redis Hash****/
	//// //hash中增加
	//// redis.boundHashOps("pv:hash:2019-07-19").increment("zequn", 1L);
	// //key值相同，后面的覆盖前面的
	//// redis.opsForHash().put("bank:hash:001", "账单", "500");
	//// redis.opsForHash().put("bank:hash:001", "账单", "1000");
	//// redis.opsForHash().put("bank:hash:002", "账单", "500");
	//// redis.opsForHash().put("bank:hash:002", "余额", "500");
	//// redis.opsForHash().put("bank:hash:002", "应付", "300");
	//// //增加500
	//// redis.boundHashOps("bank:hash:001").increment("账单", 500L);
	//// //根据key删除
	//// redis.opsForHash().delete("bank:hash:001", "500");
	//// //获取map对象
	//// System.out.println(redis.opsForHash().entries("bank:hash:001"));
	//// //获得key的集合
	//// System.out.println(redis.opsForHash().keys("bank:hash:002"));
	//// //获得value的集合
	//// System.out.println(redis.opsForHash().values("bank:hash:001"));
	//// //查询大小
	//// System.out.println(redis.opsForHash().size("bank:hash:002"));
	//
	// return "查询redis";
	// }
	//
	//
	// @RequestMapping(value = "/list", method = RequestMethod.GET)
	// @ResponseBody
	// public List<Account> getAccounts() {
	// // 增加1
	// //redis.boundHashOps("pv:hash:2019-07-19").increment("zequn", 1L);
	//
	// return accountService.findAccountList();
	// }
	//
	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public Account getAccountById(@PathVariable("id") int id) {
	// return accountService.findAccount(id);
	// }
	//
	//// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//// public Account getAccountById(@RequestParam(value = "id") int id){
	//// return am.findAccount(id);
	//// }
	//
	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// public String updateAccount(@PathVariable("id") int id,
	// @RequestParam(value = "name", required = true) String name,
	// @RequestParam(value = "money", required = true) double money) {
	// int t = accountService.update(name, money, id);
	// if (t == 1) {
	// return "success";
	// } else {
	// return "fail";
	// }
	//
	// }
	//
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// public String delete(@PathVariable(value = "id") int id) {
	// int t = accountService.delete(id);
	// if (t == 1) {
	// return "success";
	// } else {
	// return "fail";
	// }
	//
	// }
	//
	// @RequestMapping(value = "", method = RequestMethod.GET)
	// public String postAccount(@RequestParam(value = "name") String name,
	// @RequestParam(value = "money") double money) {
	//
	// int t = accountService.add(name, money);
	// if (t == 1) {
	// return "success";
	// } else {
	// return "fail";
	// }
	//
	// }

}
