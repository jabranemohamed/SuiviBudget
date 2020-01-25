package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.services.LocalUnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LocalUnitServiceImpl implements LocalUnitService {

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Override
    public List<LocalUnit> findAllLocalUnit() {
        return localUnitRepository.findAll();
    }
}
