package br.com.noemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.noemi.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}
