package entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author sheol on 11/6/17 at 12:18 PM
 * @project SpringRestDemo
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
