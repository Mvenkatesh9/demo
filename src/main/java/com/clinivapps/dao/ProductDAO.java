package com.clinivapps.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.clinivapps.entity.ProductsEntity;

@Repository
public class ProductDAO extends ClinIVDAO<ProductsEntity>{
	
	public List<ProductsEntity> findAll(){
		List<ProductsEntity> entities = new ArrayList<ProductsEntity>();
		try {
			entities = entityManager.createQuery("select d from ProductsEntity d",ProductsEntity.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<ProductsEntity> findByCompanyId(Integer companyId){
		List<ProductsEntity> entities = new ArrayList<ProductsEntity>();
		try {
			entities = entityManager.createQuery("select d from ProductsEntity d where d.companyId=:companyId",ProductsEntity.class).setParameter("companyId", companyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	public List<ProductsEntity> findByStudyId(Integer studyId){
		List<ProductsEntity> entities = new ArrayList<ProductsEntity>();
		try {
			entities = entityManager.createQuery("select d from ProductsEntity d where d.studyId=:studyId",ProductsEntity.class).setParameter("studyId", studyId).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public ProductsEntity findByDrugId(Integer productId){
		ProductsEntity entities = new ProductsEntity();
		try {
			entities = entityManager.createQuery("select d from ProductsEntity d where d.productId=:productId",ProductsEntity.class)
					.setParameter("productId", productId).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}
