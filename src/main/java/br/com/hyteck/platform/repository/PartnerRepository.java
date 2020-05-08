package br.com.hyteck.platform.repository;

import br.com.hyteck.platform.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
