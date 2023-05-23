package mes.smartmes.repository;

import mes.smartmes.entity.Porder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PorderRepository extends JpaRepository<Porder, String> {

    Porder save(Porder porder);


    Optional<Porder> findById(String id);

    //발주번호 생성
    @Query(value = "SELECT MAX(RIGHT(p.porder_no,4)) FROM porder p WHERE (select date_format(porder_date, '%Y%m%d')) = (Select date_format(sysdate(), '%Y%m%d'))",nativeQuery = true)
    String findByPorderNo();

    //발주 내역 리스트
    List<Porder> findAll();

    //발주 제품 입고 예정 일자 추출 쿼리_1 : 시간 비교
    @Query(value= "SELECT date_format(time(p.porder_date),'%H%i%S') FROM porder p WHERE porder_no = :porderNo" ,nativeQuery = true)
    String selectTime(String porderNo);

    //발주 제품 입고 예정 일자 추출 쿼리_2 : 요일 비교
    @Query(value= "SELECT dayofweek(p.porder_date) FROM porder p WHERE porder_no = :porderNo" ,nativeQuery = true)
    Integer selectDay(String porderNo);

    //발주 등록 날짜
    @Query(value= "SELECT p.porder_date FROM porder p WHERE porder_no = :porderNo ", nativeQuery = true)
    LocalDateTime selectDate(String porderNo);

    //긴급입고여부
    @Query(value = "SELECT emergency_yn FROM porder WHERE porder_no = :porderNo", nativeQuery = true)
    String emergencyYn(String porderNo);

    @Query(value = "SELECT ingredient_id FROM porder p WHERE porder_no = :porderNo", nativeQuery = true)
    String selectIngreName(String porderNo);






}

