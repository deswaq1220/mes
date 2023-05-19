package mes.smartmes.repository;

import mes.smartmes.entity.Routing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutingRepository extends JpaRepository<Routing, String> {

    Routing findByProductId(String productId);

}
