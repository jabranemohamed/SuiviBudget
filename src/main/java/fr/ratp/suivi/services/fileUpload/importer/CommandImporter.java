package fr.ratp.suivi.services.fileUpload.importer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommandImporter extends BaseImporter {
    @Override
    public Boolean importData(List beans) {
        return null;
    }
}
