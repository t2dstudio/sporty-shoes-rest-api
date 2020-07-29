package com.sporty.shoes.repositry;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

//	SELECT DATE(ColumnName) FROM tablename
//  @Query("SELECT p FROM Purchase p WHERE DATE(p.createdAt) = :createdAt")
	
//	@Query("select p from Purchase where findByStartDateBetween")
//	@Query("from Purchase p where p.createdAt between :createdAtStart and :createdAtEnd")
//	@Query(value = "select purchase_id, product_id, user_id, created_at, updated_at from purchases p where p.created_at between ?1 and ?2", nativeQuery = true)


	
//	@Query(value = "select 'p.purchase_id', 'p.created_at', 'p.updated_at', 'p.product_id', 'p.user_id'  from purchases as p "
//			+ "join users u on u.user_id = p.user_id "
//			+ "join products pr on pr.product_id = p.product_id "
//			+ "where p.created_at between ?1 and ?2", nativeQuery = true)
//	public Page<Purchase> getPurchaseReportByCreatedDate(String createdAtStart, String createdAtEnd, Pageable pageable);

	@Query("from Purchase p where p.createdAt between :createdAtStart and :createdAtEnd")
	public Page<Purchase> findAllByCreatedAtBetween(Date createdAtStart, Date createdAtEnd, Pageable pageable);

//	Page<Purchase> findAllByCreatedAtBetween(Date createdAtStart, Date createdAtEnd, Pageable pageable);
	@Query("FROM Purchase p WHERE p.product.category = :category")
	public Page<Purchase> getPurchaseReportByCategory(@Param("category") String category, Pageable pageable);

	@Query("select p from Purchase p")
	Page<Purchase> findAllByPage(Pageable pageable);

	
	
	
//    @Query("select p from Purchase p join p.users u join p.products pr where p.purchase_id = ?1 and u.user_id = ?2 and pr.product_id = ?3")
//    Page<Purchase> getPurschase(String purchaseId, String userid, String productid, Pageable pageable);  

//    @Query("select c from CustomerEntity c join c.items i join i.documents d where c.customerNumber = ?1 and i.itemNumber = ?2 and d.validDate = ?3")

}