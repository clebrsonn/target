package br.com.hyteck.platform.service.impl;

import br.com.hyteck.platform.entity.Partner;
import br.com.hyteck.platform.repository.PartnerRepository;
import br.com.hyteck.platform.service.IServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class PartnerService implements IServices<Partner> {

    private final PartnerRepository partnerRepository;


    public Page<Partner> findall(Pageable pageable) {

        return partnerRepository.findAll(pageable);
    }


    public Optional<Partner> findById(Long id) {
        return partnerRepository.findById(id);
    }

    public Partner create(Partner product) {
        return partnerRepository.save(product);
    }
    @Override
    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}
