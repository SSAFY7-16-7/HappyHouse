package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.QnA;

@Mapper
public interface QnADAO {
    public List<QnA> QnAList(PageBean pagebean);
    public int QnACount(PageBean pagebean);
    public QnA QnADetail(String idx);
    public void QRegist(QnA dto);
    public void ARegist(QnA dto);
    public void QnAModify(QnA dto);
    public void QDelete(String idx);
    public void ADelete(String idx);
}