package com.example.demo.jpa;
import java.util.List;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.jpa.MemoEntity;
import reactor.core.publisher.Mono;

public interface MemoRepository extends CrudRepository<MemoEntity, Long> {
// public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
    // @Transactional(readOnly = true)
    // Mono<MemoEntity> findById(long id);
    @Transactional(readOnly = true)
    List<MemoEntity> findByMemoText(String memoText);
}