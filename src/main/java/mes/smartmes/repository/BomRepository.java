package mes.smartmes.repository;


import mes.smartmes.entity.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BomRepository extends JpaRepository<Bom,String> {
    List findByProductId(String productId);



}
