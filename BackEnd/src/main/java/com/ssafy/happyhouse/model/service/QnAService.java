package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.QnA;

public interface QnAService {
	public List<QnA> QnAList(PageBean pagebean);
    public QnA QnADetail(String idx);
    public void QRegist(QnA dto);
    public void ARegist(QnA dto);
    public void QnAModify(QnA dto);
    public void QDelete(String idx);
    public void ADelete(String idx);
}
