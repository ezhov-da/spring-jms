package ru.ezhov.springjms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJpaRepository extends JpaRepository<TestEntity, Long> {
}
