package nopainnogain.auditservice.repository;

import nopainnogain.auditservice.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AuditRepository extends CrudRepository<Audit, UUID> {

    @Query("select c from Audit c")
    Page<Audit> findAllPage(Pageable pageable);

    @Query("select c from Audit c where c.qwe=:some and c.dtCreate>=:from and c.dtCreate<=:to")
    List<Audit> findAllUser(UUID some, LocalDateTime from, LocalDateTime to);

//    List<Audit> findAllAuditIsContainingQweMatchesSomeAndDtCreateBeforeFromAndDtCreateAfterTo(UUID some, LocalDateTime from, LocalDateTime to);
}