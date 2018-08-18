package cn.itcast.ssh.service.impl;

import java.util.List;

import cn.itcast.ssh.dao.ILeaveBillDao;
import cn.itcast.ssh.domain.LeaveBill;
import cn.itcast.ssh.service.ILeaveBillService;
import cn.itcast.ssh.utils.SessionContext;

public class LeaveBillServiceImpl implements ILeaveBillService {

	private ILeaveBillDao leaveBillDao;

	public void setLeaveBillDao(ILeaveBillDao leaveBillDao) {
		this.leaveBillDao = leaveBillDao;
	}

	/**查询自己的请假单的信息 */
	@Override
	public List<LeaveBill> findLeaveBillList() {
		// TODO Auto-generated method stub
		List<LeaveBill> list=leaveBillDao.findLeaveBillList();
		return list;
	}

	@Override
	public void saveLeaveBill(LeaveBill leaveBill) {
		// TODO Auto-generated method stub
		Long id=leaveBill.getId();
		//获取请假单ID
		if(id==null){
			//1.从Session中获取当前用户对象，将LeaveBill对象与Session中获取的用户对象进行关联
			leaveBill.setUser(SessionContext.get());
			leaveBillDao.saveLeaveBill(leaveBill);
		}else{//更新保存
			leaveBillDao.updateLeaveBill(leaveBill);
		}
		
	}

	/**使用请假单ID，查询请假单对象*/
	@Override
	public LeaveBill findLeaveBillById(Long id) {
		// TODO Auto-generated method stub
		LeaveBill leavebill=leaveBillDao.findLeaveBilById(id);
		return leavebill;
	}

	@Override
	public void deleteLeaveBillById(Long id) {
		// TODO Auto-generated method stub
		leaveBillDao.deleteLeaveBillById(id);
	}

}
