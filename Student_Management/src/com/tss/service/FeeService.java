package com.tss.service;

import java.sql.SQLException;
import java.util.List;
import com.tss.dao.FeesDao;
import com.tss.model.Fees;

public class FeeService {

	private FeesDao dao = new FeesDao();

	public double getTotalPaidFees() throws SQLException {
		return dao.getTotalPaidFees();
	}

	public double getTotalPendingFees() throws SQLException {
		return dao.getTotalPendingFees();
	}

	public List<Fees> getFeesByStudent(int studentId) throws SQLException {
		return dao.getFeesByStudent(studentId);
	}

	public List<Fees> getFeesByCourse(int courseId) throws SQLException {
		return dao.getFeesByCourse(courseId);
	}

	public boolean updateCourseFees(int courseId, double newAmount) throws SQLException {
		return dao.updateCourseFees(courseId, newAmount);
	}

	public double getTotalEarning() throws SQLException {
		return dao.getTotalEarning();
	}

	public List<Fees> getCourseFeesSummary(int courseId) throws SQLException {
		return FeesDao.getCourseFeesSummary(courseId);
	}
	
	public void deleteStudent(int id) {
		FeesDao.deleteStudent(id);
	}
	
	public void insertNewRecord(Fees fee)
	{
		FeesDao.insertNewRecord(fee);
	}


	public Fees getFeeByStudentAndCourse(int id, int courseId) {
		return FeesDao.getFeeByStudentAndCourse(id, courseId);

	}

	public boolean processFeePayment(int studentId, int courseId, double amountToPay, String paymentType) {
		return FeesDao.processFeePayment(studentId, courseId, amountToPay, paymentType);

	}

}
