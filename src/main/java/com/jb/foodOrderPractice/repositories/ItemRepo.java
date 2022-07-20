package com.jb.foodOrderPractice.repositories;

import com.jb.foodOrderPractice.beans.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {
    boolean existsByTitle(String title);
    Optional<Item> findByTitleAndCompanyId(String title, long companyId);
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Modifying
    @Query(value = "DELETE FROM food_order.customers_vs_items WHERE food_order.customers_vs_items.items_id = " +
            "(SELECT id FROM food_order.items WHERE company_id=?)",nativeQuery = true)
    void deleteItemPurchase(long companyId);
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Modifying
    void deleteAllByCompanyId(long companyId);
}
