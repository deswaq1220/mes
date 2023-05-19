package mes.smartmes.repository;

import mes.smartmes.entity.Routing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface RoutingRepository extends JpaRepository<Routing, String> {

   Routing findByProductId(String productId);
}
