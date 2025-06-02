package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;
import ru.innopolis.entity.BusType;
import ru.innopolis.repository.BusTypeRepository;
import ru.innopolis.service.BusTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BusTypeServiceImpl implements BusTypeService {

    private final BusTypeRepository busTypeRepository;

    public BusTypeServiceImpl(BusTypeRepository busTypeRepository) {
        this.busTypeRepository = busTypeRepository;
    }


    @Override
    public BusTypeResponse createBusType(BusTypeRequest busTypeRequest) {
        BusType busType = busTypeRepository.save(
                new BusType(
                        null,
                        busTypeRequest.getNameType()
                )
        );

        return new BusTypeResponse(
                busType.getId(),
                busType.getNameType()
        );
    }

    @Override
    public Optional<BusTypeResponse> findByIdBusType(Long id) {
        try {
            BusType busType = busTypeRepository.findById(id).orElseThrow();

            return Optional.of(
                    new BusTypeResponse(
                            busType.getId(),
                            busType.getNameType()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BusTypeResponse> findAllBusTypes() {
        List<BusType> busTypeList = busTypeRepository.findAll();
        List<BusTypeResponse> busTypeResponseList = new ArrayList<>();
        busTypeList.forEach(busType -> busTypeResponseList.add(
                new BusTypeResponse(
                        busType.getId(),
                        busType.getNameType()
                )
        ));

        return busTypeResponseList;
    }

    @Override
    public Optional<BusTypeResponse> updateBusType(Long id, BusTypeRequest busTypeRequest) {
        try {
            busTypeRepository.findById(id).orElseThrow();

            BusType busType = busTypeRepository.save(
                    new BusType(
                            id,
                            busTypeRequest.getNameType()
                    )
            );

            return Optional.of(
                    new BusTypeResponse(
                            busType.getId(),
                            busType.getNameType()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Boolean deleteByIdBusType(Long id) {
        Integer result = busTypeRepository.deleteByIdAsSoft(id);

        return result == 1;
    }

    @Transactional
    @Override
    public Integer deleteAllBusTypes() {
        return busTypeRepository.deleteAllAsSoft();
    }
}
