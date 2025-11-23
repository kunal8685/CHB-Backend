package com.example.repository;
import com.example.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByUlbId(Long ulbId);
}
