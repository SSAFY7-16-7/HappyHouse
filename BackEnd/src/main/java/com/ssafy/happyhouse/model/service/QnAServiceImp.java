package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.QnADAO;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.QnA;

@Service
public class QnAServiceImp implements QnAService {
	@Autowired
	QnADAO dao;

	@Override
	public List<QnA> QnAList(PageBean pagebean) {
		List<QnA> list = dao.QnAList(pagebean);
		
		return list;
	}

	@Override
	public QnA QnADetail(String idx) {
		QnA qna = dao.QnADetail(idx);
		
		return qna;
	}

	@Transactional
	@Override
	public void QRegist(QnA dto) {
		dao.QRegist(dto);
	}
	
	@Transactional
	@Override
	public void ARegist(QnA dto) {
		dao.ARegist(dto);
	}

	@Transactional
	@Override
	public void QnAModify(QnA dto) {
		dao.QnAModify(dto);
	}

	@Transactional
	@Override
	public void QDelete(String idx) {
		dao.QDelete(idx);
	}

	@Transactional
	@Override
	public void ADelete(String idx) {
		dao.ADelete(idx);
	}


}
