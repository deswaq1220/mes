package mes.smartmes.repository;


import mes.smartmes.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process, String> {

    @Query("SELECT p.processName FROM Process p WHERE p.processNO = :processNo")
    String findByProcessNo(String processNo);
}