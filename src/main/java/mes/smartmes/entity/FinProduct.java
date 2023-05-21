package mes.smartmes.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
@Getter @Setter
@Table(name = "finproduct")
public class FinProduct {
    @Id
    private String FinProductNo;



}
